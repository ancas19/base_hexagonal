package co.com.ancas.mapper;


import co.com.ancas.domain.model.TaskModel;
import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskMapper {
    private TaskMapper(){

    }

    public static TaskModel mapperToModel(TaskRequest taskRequest){
        if (taskRequest == null) return null;
        return TaskModel.builder()
                .id(taskRequest.id())
                .name(taskRequest.name())
                .description(taskRequest.description())
                .status(taskRequest.status())
                .build();
    }

    public static TaskResponse mapToResponse(TaskModel taskModel){
        if (taskModel == null) return null;
        return new TaskResponse(
                taskModel.getId(),
                taskModel.getName(),
                taskModel.getDescription(),
                taskModel.getStatus(),
                taskModel.getCreatedAt()
        );
    }

}
