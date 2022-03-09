package com.bahadir.bookshopAPI.model;

import com.bahadir.bookshopAPI.model.enums.Currency;
import com.bahadir.bookshopAPI.model.enums.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.Instant;
import java.util.*;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Book {

    @NonNull
    private final UUID id;

    @NonNull
    private final String title;

    @NonNull
    private final String author;

    @NonNull
    private final String isbn;

    @NonNull
    private final Double price;

    @NonNull
    private final Currency currency;

    @NonNull
    private final String publisher;

    @NonNull
    private final Date datePublished;

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Optional<List<Tag>> tags = Optional.empty();

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Optional<String> description = Optional.empty();

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Optional<String> imageUrl = Optional.empty();

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Integer stock = 1;

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Boolean isActive = true;

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Instant createdAt = Instant.now();

    @EqualsAndHashCode.Exclude
    @NonNull
    @Builder.Default
    private final Instant updatedAt = Instant.now();
}
