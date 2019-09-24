package com.bordozer.searchengine.controller;

import com.bordozer.searchengine.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({ NoSuchElementException.class})
    public final ResponseEntity<ErrorDto> handleException(final Exception ex) {
        return new ResponseEntity<>(ErrorDto.builder().message(ex.getMessage()).build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
