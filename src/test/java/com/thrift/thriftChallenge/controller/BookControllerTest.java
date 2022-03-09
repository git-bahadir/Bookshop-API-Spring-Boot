package com.bahadir.bookshopAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bahadir.bookshopAPI.controller.BookController;
import com.bahadir.bookshopAPI.exception.BookNotFoundException;
import com.bahadir.bookshopAPI.model.Book;
import com.bahadir.bookshopAPI.model.BookInput;
import com.bahadir.bookshopAPI.model.enums.Currency;
import com.bahadir.bookshopAPI.service.BookService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService mockBookService;

    @Autowired
    private ObjectMapper objectMapper;


    Book BOOK_MOCK_1 = Book.builder()
                    .id(UUID.randomUUID())
                    .isbn("1234567891111")
                    .title("Test Book")
                    .author("Test Author")
                    .price(new Random().nextDouble())
                    .currency(Currency.EUR)
                    .publisher("Test Publisher")
                    .datePublished(new Date())
                    .tags(Optional.of(new ArrayList<>()))
                    .description(Optional.of("Test Description"))
                    .imageUrl(Optional.of("https://image.com"))
                    .isActive(true)
                    .stock(new Random().nextInt())
                    .updatedAt(Instant.now())
                    .createdAt(Instant.now())
                    .build();

    Book BOOK_MOCK_2 = Book.builder()
            .id(UUID.randomUUID())
            .isbn("1234567891115")
            .title("Test Book 2")
            .author("Test Author")
            .price(new Random().nextDouble())
            .currency(Currency.EUR)
            .publisher("Test Publisher")
            .datePublished(new Date())
            .tags(Optional.of(new ArrayList<>()))
            .description(Optional.of("Test Description"))
            .imageUrl(Optional.of("https://image.com"))
            .isActive(true)
            .stock(new Random().nextInt())
            .updatedAt(Instant.now())
            .createdAt(Instant.now())
            .build();

    BookInput BOOK_INPUT_MOCK = BookInput.builder()
            .isbn("1234567891115")
            .title("Test Book 2")
            .author("Test Author")
            .price(new Random().nextDouble())
            .currency(Currency.EUR)
            .publisher("Test Publisher")
            .datePublished(new Date())
            .tags(Optional.of(new ArrayList<>()))
            .description(Optional.of("Test Description"))
            .isActive(true)
            .stock(new Random().nextInt())
            .build();

    @Test
    void getBooks_returnsEmpty() throws Exception {
        List<Book> books = new ArrayList<>();

        when(mockBookService.getBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

    }

    @Test
    void getBook_returnsBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(BOOK_MOCK_1);
        books.add(BOOK_MOCK_2);

        when(mockBookService.getBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(BOOK_MOCK_1.getTitle()))
                .andExpect(jsonPath("$[1].author").value(BOOK_MOCK_2.getAuthor()));

    }

/*
    @Test
    void createBook_returnsBook() throws Exception {
        when(mockBookService.addBook(BOOK_MOCK_1)).thenReturn(BOOK_MOCK_1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(BOOK_MOCK_1)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(BOOK_MOCK_1.getTitle()))
                .andExpect(jsonPath("$.author").value(BOOK_MOCK_1.getAuthor()));

    }
    */

    @Test
    void getBookByISBN_returnsBook() throws Exception {
        when(mockBookService.getBookByISBN(BOOK_MOCK_1.getIsbn())).thenReturn(BOOK_MOCK_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/books/find?isbn=" + BOOK_MOCK_1.getIsbn())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(BOOK_MOCK_1.getTitle()))
                .andExpect(jsonPath("$.author").value(BOOK_MOCK_1.getAuthor()));

    }

    @Test
    void getBookByISBN_returnsNotFound() throws Exception {
        when(mockBookService.getBookByISBN(BOOK_MOCK_1.getIsbn())).thenThrow(new BookNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/books/find?isbn=" + BOOK_MOCK_1.getIsbn())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
/*
    @Test
    void updateBook_returnsBook() throws Exception {
        mockBookService.addBook(BOOK_MOCK_1);

        when(mockBookService.updateBookbyId(BOOK_MOCK_2, BOOK_MOCK_1.getId())).thenReturn(BOOK_MOCK_2);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/books/" + BOOK_MOCK_1.getId() + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(BOOK_MOCK_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(BOOK_MOCK_2.getTitle()))
                .andExpect(jsonPath("$.author").value(BOOK_MOCK_2.getAuthor()));

    }
*/
    @Test
    void deleteBook_returnsBook() throws Exception {
        mockBookService.addBook(BOOK_MOCK_1);

        when(mockBookService.deleteBookById(BOOK_MOCK_1.getId())).thenReturn("deleted");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/books/" + BOOK_MOCK_1.getId() + "/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
