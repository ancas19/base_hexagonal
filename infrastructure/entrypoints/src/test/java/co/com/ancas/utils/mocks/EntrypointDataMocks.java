package co.com.ancas.utils.mocks;

import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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

    public static List<TaskResponse> taskResponseList() {
        return Arrays.asList(
                new TaskResponse(
                        UUID.randomUUID(),
                        "Task 1",
                        "Description 1",
                        "PENDING",
                        LocalDateTime.now()
                ),
                new TaskResponse(
                        UUID.randomUUID(),
                        "Task 2",
                        "Description 2",
                        "IN_PROGRESS",
                        LocalDateTime.now()
                )
        );
    }
}

