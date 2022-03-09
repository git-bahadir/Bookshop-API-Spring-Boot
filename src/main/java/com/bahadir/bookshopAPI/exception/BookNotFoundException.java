package com.bahadir.bookshopAPI.exception;

import static com.bahadir.bookshopAPI.exception.ErrorMessages.BOOK_NOT_FOUND;


public class BookNotFoundException extends ServiceException {

    public BookNotFoundException() {
        super(BOOK_NOT_FOUND);
    }
}
