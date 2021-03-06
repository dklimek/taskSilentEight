package com.klimek.demo.restApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong variant send. Available variant: token, string.")
public class VariantBadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}
