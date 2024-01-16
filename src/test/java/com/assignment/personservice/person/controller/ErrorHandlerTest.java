package com.assignment.personservice.person.controller;

import com.assignment.personservice.person.controller.ErrorHandler;
import com.assignment.personservice.person.exception.PersonNotFoundException;
import com.assignment.personservice.shared.model.ErrorResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    private static final Long TEST_PERSON_ID = 1L;


    @InjectMocks
    private ErrorHandler errorHandler;

    @Test
    void testHandlePersonNotFoundException() {
        PersonNotFoundException exception = new PersonNotFoundException(TEST_PERSON_ID);

        ResponseEntity<ErrorResponseVO> response = errorHandler.handlePersonNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void testHandleGenericException() {
        Throwable exception = new RuntimeException("Internal server error");

        ResponseEntity<ErrorResponseVO> response = errorHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(1, response.getBody().getErrors().size());
    }

}