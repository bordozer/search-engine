package com.bordozer.searchengine.controller;

import com.bordozer.searchengine.dto.ErrorDto;
import com.bordozer.searchengine.dto.ImmutableErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({NoSuchElementException.class})
    public final ResponseEntity<ErrorDto> handleNoSuchElementException(final Exception ex) {
        return handleException(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({EntityExistsException.class})
    public final ResponseEntity<ErrorDto> handleEntityExistsException(final Exception ex) {
        return handleException(ex, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    public final ResponseEntity<ErrorDto> handleHttpMessageConversionException(final Exception ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDto> handleException(final Exception ex, final HttpStatus status) {
        final ImmutableErrorDto message = ErrorDto.builder().message(ex.getMessage()).build();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(message, headers, status);
    }
}
