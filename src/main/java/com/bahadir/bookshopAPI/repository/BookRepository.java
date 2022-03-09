package com.bahadir.bookshopAPI.repository;

import com.bahadir.bookshopAPI.model.Book;
import com.bahadir.bookshopAPI.repository.model.BookEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface BookRepository extends CrudRepository<BookEntity, UUID> {
    List<BookEntity> getBookByAuthor(String author);

    List<BookEntity> findAll();

    BookEntity save(BookEntity bookEntity);

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByAuthor(String author);

    void deleteById(UUID id);
}