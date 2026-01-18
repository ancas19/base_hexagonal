package co.com.ancas.controllers.health_check;

import co.com.ancas.responses.HealthStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/health-check")
@Tag(name = "Health Check", description = "Endpoints for application health monitoring")
public class HealthCheckGetController {

    @Operation(
            summary = "Get health status",
            description = "Returns the current health status and version of the application"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Application is healthy",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HealthStatusResponse.class)
                    )
            )
    })
    @GetMapping
    public Mono<HealthStatusResponse> getStatus() {
        return Mono.just(
                new HealthStatusResponse("OK", "1.0.0")
        );
    }
}
