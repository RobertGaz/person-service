package com.assignment.personservice.person.controller;

import com.assignment.personservice.person.exception.PersonNotFoundException;
import com.assignment.personservice.shared.model.ErrorResponseVO;
import com.assignment.personservice.shared.model.ErrorVO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);


    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handlePersonNotFoundException(PersonNotFoundException e) {
        UUID errorId = UUID.randomUUID();
        LOGGER.error("PersonNotFoundException occurred: {}. Assigned UUID: {}", e.getMessage(), errorId);
        LOGGER.error(ExceptionUtils.getStackTrace(e));

        ErrorVO error = new ErrorVO(errorId, "client", "NOT_FOUND", e.getMessage());
        ErrorResponseVO errorResponse = new ErrorResponseVO(Collections.singletonList(error));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponseVO> handleGenericException(Throwable e) {
        UUID errorId = UUID.randomUUID();
        LOGGER.error("Exception occurred: {}. Assigned UUID: {}", e.getMessage(), errorId);
        LOGGER.error(ExceptionUtils.getStackTrace(e));

        ErrorVO error = new ErrorVO(errorId, "server", "INTERNAL_SERVER_ERROR", e.getMessage());
        ErrorResponseVO errorResponse = new ErrorResponseVO(Collections.singletonList(error));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseVO> handleValidationException(MethodArgumentNotValidException e) {
        UUID errorId = UUID.randomUUID();
        LOGGER.error("Validation Exception occurred: {}. Assigned UUID: {}", e.getMessage(), errorId);
        LOGGER.error(ExceptionUtils.getStackTrace(e));

        List<ErrorVO> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            errors.add(new ErrorVO(errorId, fieldName + " field", "BAD_REQUEST", errorMessage));
        }

        ErrorResponseVO errorResponse = new ErrorResponseVO(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
