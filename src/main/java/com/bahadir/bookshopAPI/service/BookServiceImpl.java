package com.bahadir.bookshopAPI.service;

import com.bahadir.bookshopAPI.exception.BookApiException;
import com.bahadir.bookshopAPI.exception.BookNotFoundException;
import com.bahadir.bookshopAPI.exception.ErrorMessages;
import com.bahadir.bookshopAPI.model.Book;
import com.bahadir.bookshopAPI.model.enums.Tag;
import com.bahadir.bookshopAPI.repository.BookRepository;
import com.bahadir.bookshopAPI.util.BookSerDes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.bahadir.bookshopAPI.util.BookSerDes.bookToBookEntity;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookSerDes::bookEntityToBook)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(UUID id) {
        return bookRepository.findById(id)
                .map(BookSerDes::bookEntityToBook)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        bookRepository.findByIsbn(book.getIsbn())
                .ifPresent(s -> {
                    throw new BookApiException(ErrorMessages.BOOK_EXISTS);
                });

        return BookSerDes.bookEntityToBook(
                bookRepository.save(bookToBookEntity(book)));
    }

    @Override
    @Transactional
    public Book updateBookbyId(Book book, UUID id) {
        return bookRepository.findById(id)
                .map(bookEntity -> {
                    bookEntity.setTitle(book.getTitle());
                    bookEntity.setAuthor(book.getAuthor());
                    bookEntity.setDescription(book.getDescription().orElse(""));
                    bookEntity.setIsbn(book.getIsbn());
                    bookEntity.setCurrency(book.getCurrency());
                    bookEntity.setPrice(book.getPrice());
                    bookEntity.setPublisher(book.getPublisher());
                    bookEntity.setDatePublished(book.getDatePublished());
                    bookEntity.setPublisher(book.getPublisher());
                    bookEntity.setStock(book.getStock());
                    bookEntity.setTags(book.getTags().orElse(new ArrayList<Tag>()));
                    bookEntity.setImageUrl(book.getImageUrl().orElse(""));
                    bookEntity.setUpdatedAt(Instant.now());
                    bookEntity.setIsActive(book.getIsActive());
                    return bookRepository.save(bookEntity);
                })
                .map(BookSerDes::bookEntityToBook)
                .orElseThrow(BookNotFoundException::new);
    }


    // getBookByISBN
    @Override
    public Book getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(BookSerDes::bookEntityToBook)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    @Transactional
    public String deleteBookById(UUID id) {
        return bookRepository.findById(id)
            .map(bookEntity -> {
                bookRepository.deleteById(id);
                return "deleted";
            })
           .orElseThrow(BookNotFoundException::new);
    }

    @Override
    @Transactional
    public Book updateBookStock(UUID id, Integer stock) {
        return bookRepository.findById(id)
                .map(bookEntity -> {
                    bookEntity.setStock(stock);
                    bookEntity.setUpdatedAt(Instant.now());
                    return bookRepository.save(bookEntity);
                })
                .map(BookSerDes::bookEntityToBook)
                .orElseThrow(BookNotFoundException::new);
    }


    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author)
                .stream()
                .map(BookSerDes::bookEntityToBook)
                .collect(Collectors.toList());

    }
}