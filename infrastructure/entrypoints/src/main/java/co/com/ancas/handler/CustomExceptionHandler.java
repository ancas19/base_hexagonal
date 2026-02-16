package co.com.ancas.handler;

import co.com.ancas.domain.exceptions.BadRequestException;
import co.com.ancas.domain.exceptions.NotFoundException;
import co.com.ancas.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static co.com.ancas.domain.enums.ErrorMessages.ERROR_GENERAL_MESSAGE;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        logger.error("Exception occurred: {}", ex.getMessage(),ex);
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        request.getDescription(false),
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException ex, WebRequest request) {
        logger.error("Exception occurred: {}", ex.getMessage(),ex);
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND,
                        request.getDescription(false),
                        LocalDateTime.now()
                ),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        logger.error("Exception occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(
                        ERROR_GENERAL_MESSAGE.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        request.getDescription(false),
                        LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
