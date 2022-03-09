package com.bahadir.bookshopAPI.handler.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
@Builder(toBuilder = true)
public class ErrorResponse {

    @NonNull
    @Builder.Default
    private final Optional<String> message = Optional.empty();
}
