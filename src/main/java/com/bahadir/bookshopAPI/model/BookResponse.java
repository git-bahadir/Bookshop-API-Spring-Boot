package com.bahadir.bookshopAPI.model;

import com.bahadir.bookshopAPI.model.enums.Currency;
import com.bahadir.bookshopAPI.model.enums.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class BookResponse {
    @NonNull
    private final UUID id;

    @NonNull
    private final String isbn;

    @NonNull
    private final String title;

    @NonNull
    private final String author;

    @NonNull
    private final Double price;

    @NonNull
    private final Currency currency;

    @NonNull
    private final String publisher;

    @NonNull
    private final Date datePublished;

    @NonNull
    private final List<Tag> tags;

    @NonNull
    private final String description;

    @NonNull
    private final String imageUrl;

    @NonNull
    private final Instant createdAt;

    @NonNull
    private final Instant updatedAt;

    @NonNull
    private final Integer stock;

    @NonNull
    private final Boolean isActive;

}
