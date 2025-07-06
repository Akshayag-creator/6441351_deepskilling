package com.example.service;

import com.example.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;
    private NotificationService notificationService;

    // Constructor Injection
    public BookService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName) {
        System.out.println("Adding book: " + bookName);
        bookRepository.saveBook(bookName);
        notificationService.sendNotification("Book '" + bookName + "' was added.");
    }
}
