package net.lycee.web.enquete.api.controller;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        AnswerId.register(binder);
        QuestionId.register(binder);
        SpaceId.register(binder);
        UserId.register(binder);
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream().map(error -> {
            if (error instanceof FieldError fieldError) {
                return String.format("%s: %s", fieldError.getField(), error.getDefaultMessage());
            }
            return error.toString();
        }).collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = e.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }


}
