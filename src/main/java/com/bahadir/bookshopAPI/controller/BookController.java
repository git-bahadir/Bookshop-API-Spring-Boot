package com.bahadir.bookshopAPI.controller;

import com.bahadir.bookshopAPI.model.BookInput;
import com.bahadir.bookshopAPI.model.BookResponse;
import com.bahadir.bookshopAPI.model.util.bookValids;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import javax.validation.constraints.Size;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.bahadir.bookshopAPI.service.BookService;

import static com.bahadir.bookshopAPI.util.BookSerDes.bookInputToBook;
import static com.bahadir.bookshopAPI.util.BookSerDes.bookToBookResponse;

@RestController
@Validated
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        final var books = bookService.getBooks();

        return ResponseEntity.ok(
                books.stream()
                        .map(b -> {
                            return bookToBookResponse(b);
                        })
                        .collect(Collectors.toList()));
    }

    @GetMapping(value= "/find", params = "author")
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(
            @RequestParam @Valid
            @Size( min = 1, max = bookValids.AUTHOR_MAX_LENGTH) String author) {
        final var books = bookService.getBooksByAuthor(author);

        return ResponseEntity.ok(
                books.stream()
                .map(b -> bookToBookResponse(b))
                .collect(Collectors.toList()));

    }


    @GetMapping(value= "/find", params = "isbn")
            public ResponseEntity<BookResponse> getBooksByIsbn(
                    @RequestParam @Valid @Size(
                            min = bookValids.ISBN_DIGIT_COUNT,
                            max = bookValids.ISBN_DIGIT_COUNT)
                    String isbn) {
        final var book = bookService.getBookByISBN(isbn);

        return ResponseEntity.ok(bookToBookResponse(book));
    }


    @GetMapping(value= "/find", params = "id")
    public ResponseEntity<BookResponse> getBookById(
            @RequestParam UUID id) {
        final var book = bookService.getBookById(id);

        return ResponseEntity.ok(bookToBookResponse(book));
    }


    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookInput book) {
        final var createdBook = bookService.addBook(bookInputToBook(book));

        return ResponseEntity.status(HttpStatus.CREATED).body(bookToBookResponse(createdBook));
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<BookResponse> updateBookById(
            @PathVariable(value = "id") @Valid UUID bookId,
                    @RequestBody BookInput book) {
        final var updatedBook = bookService.updateBookbyId(bookInputToBook(book), bookId);

        return ResponseEntity.ok(bookToBookResponse(updatedBook));
    }


    @PutMapping(value= "/{id}/update", params = "stock")
    public ResponseEntity<BookResponse> updateBookStockById(
            @PathVariable(value = "id") UUID bookId,
            @RequestParam Integer stock) {
        final var updatedBook = bookService.updateBookStock(bookId, stock);

        return ResponseEntity.ok(bookToBookResponse(updatedBook));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteBookById(
            @PathVariable(value = "id") UUID bookId) {
        bookService.deleteBookById(bookId);

        return ResponseEntity.noContent().build();
    }

}

