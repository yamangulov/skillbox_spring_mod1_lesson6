package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(value = "author") String author,
                         @RequestParam(value = "title") String title,
                         @RequestParam(value = "minSize") Integer minSize,
                         @RequestParam(value = "maxSize") Integer maxSize,
                         Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.filterByParams(author, title, minSize, maxSize));
        return "book_shelf";
    }

    @GetMapping("/undoFilter")
    public String undoFilter() {
        return "redirect:/books/shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        bookService.removeBookById(bookIdToRemove);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@RequestParam(value = "bookAuthorToRemove") String author) {
        bookService.removeBookByAuthor(author);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByTitle")
    public String removeBookByTitle(@RequestParam(value = "bookTitleToRemove") String title) {
        bookService.removeBookByTitle(title);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeBySize")
    public String removeBookBySize(@RequestParam(value = "bookSizeToRemove") Integer size) {
        bookService.removeBookBySize(size);
        return "redirect:/books/shelf";
    }
}
