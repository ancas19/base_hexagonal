package co.com.ancas.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Health status response containing application state information")
public record HealthStatusResponse(
        @Schema(description = "Current health status of the application", example = "OK")
        String status,
        @Schema(description = "Current version of the application", example = "1.0.0")
        String version
) {
}
