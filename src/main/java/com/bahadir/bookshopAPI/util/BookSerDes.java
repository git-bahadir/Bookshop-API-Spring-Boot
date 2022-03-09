package com.bahadir.bookshopAPI.util;

import com.bahadir.bookshopAPI.model.Book;
import com.bahadir.bookshopAPI.model.BookInput;
import com.bahadir.bookshopAPI.model.BookResponse;
import com.bahadir.bookshopAPI.repository.model.BookEntity;
import com.bahadir.bookshopAPI.model.enums.Tag;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class BookSerDes {

    public BookSerDes(){}

    public static Book bookInputToBook(final BookInput bookInput){
        return Book.builder()
                .id(UUID.randomUUID())
                .isbn(bookInput.getIsbn())
                .title(bookInput.getTitle())
                .author(bookInput.getAuthor())
                .price(bookInput.getPrice())
                .currency(bookInput.getCurrency())
                .publisher(bookInput.getPublisher())
                .datePublished(bookInput.getDatePublished())
                .tags(bookInput.getTags())
                .description(bookInput.getDescription())
                .imageUrl(bookInput.getImageUrl())
                .isActive(bookInput.getIsActive())
                .stock(bookInput.getStock())
                .updatedAt(Instant.now())
                .currency(bookInput.getCurrency())
                .createdAt(Instant.now())
                .build();
    }

    public static BookEntity bookToBookEntity(final Book book){
        return BookEntity.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .currency(book.getCurrency())
                .publisher(book.getPublisher())
                .datePublished(book.getDatePublished())
                .tags(book.getTags().orElse(new ArrayList<Tag>()))
                .description(book.getDescription().orElse(""))
                .imageUrl(book.getImageUrl().orElse(""))
                .isActive(book.getIsActive())
                .stock(book.getStock())
                .updatedAt(book.getUpdatedAt())
                .createdAt(book.getCreatedAt())
                .build();
    }


    public static Book bookEntityToBook(final BookEntity bookEntity){
        return Book.builder()
                .id(bookEntity.getId())
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .price(bookEntity.getPrice())
                .currency(bookEntity.getCurrency())
                .publisher(bookEntity.getPublisher())
                .datePublished(bookEntity.getDatePublished())
                .tags(Optional.of(bookEntity.getTags()))
                .description(Optional.of(bookEntity.getDescription()))
                .imageUrl(Optional.of(bookEntity.getImageUrl()))
                .isActive(bookEntity.getIsActive())
                .stock(bookEntity.getStock())
                .updatedAt(bookEntity.getUpdatedAt())
                .createdAt(bookEntity.getCreatedAt())
                .build();
    }

    public static BookResponse bookToBookResponse(final Book book){
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .currency(book.getCurrency())
                .publisher(book.getPublisher())
                .datePublished(book.getDatePublished())
                .tags(book.getTags().orElse(new ArrayList<Tag>()))
                .description(book.getDescription().orElse(""))
                .imageUrl(book.getImageUrl().orElse(""))
                .isActive(book.getIsActive())
                .stock(book.getStock())
                .updatedAt(book.getUpdatedAt())
                .createdAt(book.getCreatedAt())
                .build();
    }

}
