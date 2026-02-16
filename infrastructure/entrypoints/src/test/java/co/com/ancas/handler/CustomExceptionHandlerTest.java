package co.com.ancas.handler;

import co.com.ancas.domain.exceptions.BadRequestException;
import co.com.ancas.domain.exceptions.NotFoundException;
import co.com.ancas.responses.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomExceptionHandler Unit Tests")
class CustomExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    void setUp() {
        when(webRequest.getDescription(false)).thenReturn("uri=/test/path");
    }

    @Test
    @DisplayName("Should handle BadRequestException and return 400 BAD_REQUEST")
    void shouldHandleBadRequestException() {
        // Arrange
        String errorMessage = "Invalid request data";
        BadRequestException exception = new BadRequestException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleBadRequestException(exception, webRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().message());
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().code());
        assertEquals("uri=/test/path", response.getBody().path());
        assertNotNull(response.getBody().timestamp());

        verify(webRequest, times(1)).getDescription(false);
    }

    @Test
    @DisplayName("Should handle NotFoundException and return 404 NOT_FOUND")
    void shouldHandleNotFoundException() {
        // Arrange
        String errorMessage = "Resource not found";
        NotFoundException exception = new NotFoundException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleException(exception, webRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().message());
        assertEquals(HttpStatus.NOT_FOUND, response.getBody().code());
        assertEquals("uri=/test/path", response.getBody().path());
        assertNotNull(response.getBody().timestamp());

        verify(webRequest, times(1)).getDescription(false);
    }

    @Test
    @DisplayName("Should handle generic Exception and return 500 INTERNAL_SERVER_ERROR")
    void shouldHandleGenericException() {
        // Arrange
        String errorMessage = "Something went wrong";
        Exception exception = new RuntimeException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleException(exception, webRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An unexpected error occurred, please try again later", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getBody().code());
        assertEquals("uri=/test/path", response.getBody().path());
        assertNotNull(response.getBody().timestamp());

        verify(webRequest, times(1)).getDescription(false);
    }
}

