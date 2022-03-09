package com.bahadir.bookshopAPI.exception;

import lombok.Getter;

import java.util.Optional;

import static com.bahadir.bookshopAPI.exception.ErrorMessages.SERVER_KNOWN_FAILURE;
import static com.bahadir.bookshopAPI.exception.ErrorMessages.SERVER_UNKNOWN_FAILURE;

public class BookApiException extends ServiceException {

    @Getter
    private Optional<String> reason;

    public BookApiException(final Exception exception) {
        super(String.format(SERVER_KNOWN_FAILURE, exception.getMessage()));

        reason = Optional.of(String.format(SERVER_KNOWN_FAILURE, exception.getMessage()));
    }

    public BookApiException(final String message) {
        super(message);
        reason = Optional.of(message);
    }

    public BookApiException() {
        super(SERVER_UNKNOWN_FAILURE);
        reason = Optional.of(SERVER_UNKNOWN_FAILURE);
    }

}
