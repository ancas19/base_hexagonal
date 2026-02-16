package co.com.ancas.postgres.mappers;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.postgres.entities.TaskEntity;

import java.time.LocalDateTime;

public class TaskMapper {
    private TaskMapper() {
    }

    public static TaskEntity mapToEntity(TaskModel taskModel){
        return TaskEntity.builder()
                .id(taskModel.getId())
                .name(taskModel.getName())
                .description(taskModel.getDescription())
                .status(taskModel.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TaskModel mapToModel(TaskEntity taskEntity){
        return TaskModel.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .status(taskEntity.getStatus())
                .createdAt(taskEntity.getCreatedAt())
                .build();
    }
}
