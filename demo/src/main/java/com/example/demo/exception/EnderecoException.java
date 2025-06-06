package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class EnderecoException extends RuntimeException {
    public EnderecoException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
    }
}
