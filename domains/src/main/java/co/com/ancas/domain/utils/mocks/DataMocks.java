package co.com.ancas.domain.utils.mocks;

import co.com.ancas.domain.models.TaskModel;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DataMocks {
    private DataMocks() {
    }

    public static TaskModel taskModelInformation(){
        return TaskModel.builder()
                .id(UUID.randomUUID())
                .name("Name")
                .description("Description")
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<TaskModel> taskModelList(){
        return Arrays.asList(
                TaskModel.builder()
                        .id(UUID.randomUUID())
                        .name("Task 1")
                        .description("Description 1")
                        .status("PENDING")
                        .createdAt(LocalDateTime.now())
                        .build(),
                TaskModel.builder()
                        .id(UUID.randomUUID())
                        .name("Task 2")
                        .description("Description 2")
                        .status("IN_PROGRESS")
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
