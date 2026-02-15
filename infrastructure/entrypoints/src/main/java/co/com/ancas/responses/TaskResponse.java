package co.com.ancas.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        String name,
        String description,
        String status,
        LocalDateTime createdAt
) {
}
