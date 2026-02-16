package co.com.ancas.utils.mocks;

import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public class EntrypointDataMocks {
    private EntrypointDataMocks() {
    }

    public static TaskRequest taskRequestInformation() {
        return new TaskRequest(
                UUID.randomUUID(),
                "Name",
                "Description",
                "PENDING"
        );
    }

    public static TaskResponse taskResponseInformation() {
        return new TaskResponse(
                UUID.randomUUID(),
                "Name",
                "Description",
                "PENDING",
                LocalDateTime.now()
        );
    }
}

