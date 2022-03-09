package com.bahadir.bookshopAPI.handler;

import com.bahadir.bookshopAPI.exception.BookApiException;
import com.bahadir.bookshopAPI.exception.BookNotFoundException;
import com.bahadir.bookshopAPI.exception.ErrorMessages;
import com.bahadir.bookshopAPI.handler.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@ControllerAdvice
public class BookControllerErrorHandler {

    @ExceptionHandler(BookApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(final BookApiException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder().message(ex.getReason()).build());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder().message(Optional.of( ErrorMessages.BOOK_NOT_FOUND )).build()
                );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder().message(Optional.of( ErrorMessages.INPUT_ERROR + ex.getMessage())).build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder().message(
                                Optional.of( ErrorMessages.INPUT_BOOK_ERROR + ex.getMessage())).build()
                );
    }
}