package co.com.ancas.domain.utils.mocks;

import co.com.ancas.domain.models.TaskModel;

import java.time.LocalDateTime;
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
}
