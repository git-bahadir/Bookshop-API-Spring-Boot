package com.bahadir.bookshopAPI.model;

import com.bahadir.bookshopAPI.model.enums.Currency;
import com.bahadir.bookshopAPI.model.enums.Tag;
import com.bahadir.bookshopAPI.model.util.bookValids;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Builder(toBuilder = true)
public class BookInput {

    @NonNull
    @Size(min=bookValids.ISBN_DIGIT_COUNT, max=bookValids.ISBN_DIGIT_COUNT)
    private final String isbn;

    @NonNull
    @Size(min=1, max=bookValids.TITLE_MAX_LENGTH)
    private final String title;

    @NonNull
    @Size(min=1,max=bookValids.AUTHOR_MAX_LENGTH)
    private final String author;

    @NonNull
    private final Double price;

    @NonNull
    private final Currency currency;

    @NonNull
    @Size(min=1,max=bookValids.PUBLISHER_MAX_LENGTH)
    private final String publisher;

    @NonNull
    private final Date datePublished;

    @NonNull
    private final Integer stock;

    @NonNull
    private final Optional<List<Tag>> tags;

    @NonNull
    private final Optional<
            @Size(min=bookValids.DESCRIPTION_MIN_LENGTH, max=bookValids.DESCRIPTION_MAX_LENGTH)
                    String> description;

    private final Optional<
            @URL(regexp = "^(http|ftp).*")
                    String> imageUrl = Optional.empty();

    @NonNull
    private final Boolean isActive;
}
