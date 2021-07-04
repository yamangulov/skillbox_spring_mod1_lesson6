package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private List<Book> repo = new ArrayList<>();
    private List<Book> oldRepo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void filterByParams(String author, String title, Integer minSize, Integer maxSize) {

        List<Book> filteredRepo = new ArrayList<>();

        for (Book book : retrieveAll()) {
            if (author.isEmpty()) {
                if (title.isEmpty()) {
                    filteredRepo = getInitFilteredBooks(minSize, maxSize, filteredRepo, book);
                } else {
                    if (title.equals(book.getTitle())) {
                        getFilteredBooks(minSize, maxSize, filteredRepo, book);
                    }
                }
            } else {
                if (title.isEmpty()) {
                    if (author.equals(book.getAuthor())) {
                        getFilteredBooks(minSize, maxSize, filteredRepo, book);
                    }
                } else {
                    if (title.equals(book.getTitle())
                            && author.equals(book.getAuthor())) {
                        getFilteredBooks(minSize, maxSize, filteredRepo, book);
                    }
                }
            }
        }

        oldRepo = new ArrayList<>(repo);
        repo =  new ArrayList<>(filteredRepo);

    }

    private List<Book> getInitFilteredBooks(Integer minSize, Integer maxSize, List<Book> filteredRepo, Book book) {
        if (minSize == null && maxSize == null) {
            filteredRepo = retrieveAll();
        } else if (maxSize != null && minSize == null) {
            if (book.getSize() <= maxSize) {
                filteredRepo.add(book);
            }
        } else if (maxSize == null){
            if (book.getSize() >= minSize) {
                filteredRepo.add(book);
            }
        } else {
            if (book.getSize() <= maxSize && book.getSize() >= minSize) {
                filteredRepo.add(book);
            }
        }
        return filteredRepo;
    }

    private void getFilteredBooks(Integer minSize, Integer maxSize, List<Book> filteredRepo, Book book) {
        if (minSize == null && maxSize == null) {
            filteredRepo.add(book);
        } else if (maxSize != null && minSize == null) {
            if (book.getSize() <= maxSize) {
                filteredRepo.add(book);
            }
        } else if (maxSize == null) {
            if (book.getSize() >= minSize) {
                filteredRepo.add(book);
            }
        } else {
            if (book.getSize() <= maxSize && book.getSize() >= minSize) {
                filteredRepo.add(book);
            }
        }
    }

    @Override
    public void undoFilter() {
        repo = new ArrayList<>(oldRepo);
    }

    @Override
    public void store(Book book) {
        if (!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || book.getSize() != null) {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
            //при добавлениях и удалениях книг нужно обновлять резервную копию репо, чтобы при
            //случайном нажатии на "Reset filter" без выполненной фильтрации не сбросить список книг
            oldRepo = new ArrayList<>(repo);
        } else {
            logger.info("there was an attempt to save book without author, title and size");
        }
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retrieveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                boolean remove = repo.remove(book);
                oldRepo = new ArrayList<>(repo);
                return remove;
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String author) {
        List<Book> booksForRemoving = new ArrayList<>();
        for (Book book : retrieveAll()) {
            if (book.getAuthor().contains(author)) {
                booksForRemoving.add(book);
            }
        }
        return removeItemsByList(booksForRemoving);
    }

    @Override
    public boolean removeItemByTitle(String title) {
        List<Book> booksForRemoving = new ArrayList<>();
        for (Book book : retrieveAll()) {
            if (book.getTitle().contains(title)) {
                booksForRemoving.add(book);
            }
        }
        return removeItemsByList(booksForRemoving);
    }

    @Override
    public boolean removeItemBySize(Integer size) {
        List<Book> booksForRemoving = new ArrayList<>();
        for (Book book : retrieveAll()) {
            if (book.getSize().equals(size)) {
                booksForRemoving.add(book);
            }
        }
        return removeItemsByList(booksForRemoving);
    }

    private boolean removeItemsByList(List<Book> booksForRemoving) {
        if (!booksForRemoving.isEmpty()) {
            for (Book book : booksForRemoving) {
                repo.remove(book);
            }
            oldRepo = new ArrayList<>(repo);
            return true;
        }
        return false;
    }
}
