package com.bahadir.bookshopAPI.service;

import com.bahadir.bookshopAPI.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> getBooks();

    Book getBookById(UUID id);

    Book getBookByISBN(String isbn);

    List<Book> getBooksByAuthor(String author);

    Book addBook(Book book);

    Book updateBookbyId(Book book, UUID id);

    Book updateBookStock(UUID id, Integer stock);

    String deleteBookById(UUID id);
}
