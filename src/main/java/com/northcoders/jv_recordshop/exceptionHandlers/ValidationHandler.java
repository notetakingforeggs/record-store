package com.northcoders.jv_recordshop.exceptionHandlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// controller advice annotation allows exception handling methods to apply to all controllers
@ControllerAdvice

public class ValidationHandler extends ResponseEntityExceptionHandler {
    // without the override we just get genereic http codes based on the response status given back to usres
    @Override
        // caught when an @valid, or @validated annotation is violated, the http headers and code status is generated automatically by spring. the request is the current request object?
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode
        status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        // get binding result gets info about any errors that occurred during the bidnig of requst params to the java obj
        // this includes field errors (which are specific to particular fields of the object)
        // along with object errors, which are global errors.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            // get default message must take the message that is input next to the annotations
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        System.out.println("getting caught in exception handler for dto");
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    // handle general exceptions - an exception handler for all things that extend class exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request){
        Map<String, String> error = new HashMap<>();
        error.put("error", "internal server error");
        error.put("message", ex.getMessage());
        System.out.println("getting caught in general handler");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handle non numbers being passed into number fields
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(Exception ex, WebRequest request){
        Map<String, String> error = new HashMap<>();
        error.put("error", "number format error - are you sure you are inputting numbers?");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
