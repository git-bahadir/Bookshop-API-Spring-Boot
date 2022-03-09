package com.bahadir.bookshopAPI.exception;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String BOOK_NOT_FOUND = "Book not found.";
    public static final String SERVER_UNKNOWN_FAILURE = "Failed to communicate with server. Please try again later.";
    public static final String SERVER_KNOWN_FAILURE = "Failed to communicate with server because, %s. Please try again later.";
    public static final String BOOK_EXISTS = "Book already exists.";
    public static final String INPUT_ERROR = "Validation Error on input: ";
    public static final String INPUT_BOOK_ERROR = "Validation Error on Book object input: ";

}
