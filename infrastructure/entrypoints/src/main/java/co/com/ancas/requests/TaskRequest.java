package co.com.ancas.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record TaskRequest(

        UUID id,

        @NotBlank(message = "name must not be blank")
        String name,

        @NotBlank(message = "description must not be blank")
        String description,

        @NotBlank(message = "status must not be blank")
        @Pattern(regexp = "PENDING|IN_PROGRESS|DONE", message = "status must be TODO, IN_PROGRESS or DONE")
        String status
) {
}
