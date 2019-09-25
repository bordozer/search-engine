package com.bordozer.searchengine.controller;

import com.bordozer.searchengine.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({NoSuchElementException.class})
    public final ResponseEntity<ErrorDto> handleNoSuchElementException(final Exception ex) {
        return new ResponseEntity<>(ErrorDto.builder().message(ex.getMessage()).build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({EntityExistsException.class})
    public final ResponseEntity<ErrorDto> handleEntityExistsException(final Exception ex) {
        return new ResponseEntity<>(ErrorDto.builder().message(ex.getMessage()).build(), HttpStatus.EXPECTATION_FAILED);
    }
}
