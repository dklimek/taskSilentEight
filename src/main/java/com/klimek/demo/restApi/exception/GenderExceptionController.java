package com.klimek.demo.restApi.exception;


import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GenderExceptionController {

    @ExceptionHandler(TokenNotfoundException.class)
    public ResponseEntity<Object> TokenNotfoundException(TokenNotfoundException e) {
        JSONObject response = new JSONObject();
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VariantBadRequestException.class)
    public ResponseEntity<String> VariantBadRequestException(VariantBadRequestException e) {
        JSONObject response = new JSONObject();
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }


}
