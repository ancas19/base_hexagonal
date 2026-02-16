package co.com.ancas.responses;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        HttpStatus code,
        String path,
        LocalDateTime timestamp
) {
}
