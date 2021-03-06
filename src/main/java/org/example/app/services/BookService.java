package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public boolean removeBookByAuthor(String author) {
        return bookRepo.removeItemByAuthor(author);
    }

    public boolean removeBookByTitle(String title) {
        return bookRepo.removeItemByTitle(title);
    }

    public boolean removeBookBySize(Integer size) {
        return bookRepo.removeItemBySize(size);
    }

    public List<Book> filterByParams(String author, String title, Integer minSize, Integer maxSize) {
        return bookRepo.filterByParams(author, title, minSize, maxSize);
    }
}
