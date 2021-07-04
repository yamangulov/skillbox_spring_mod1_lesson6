package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if (!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || book.getSize() != null) {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
        } else {
            logger.info("there was an attempt to save book without author, title and size");
        }
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retrieveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
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
            return true;
        }
        return false;
    }
}
