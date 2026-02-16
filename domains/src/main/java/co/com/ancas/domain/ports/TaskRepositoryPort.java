package co.com.ancas.domain.ports;

import co.com.ancas.domain.models.TaskModel;

import java.util.List;

public interface TaskRepositoryPort {
    TaskModel create(TaskModel taskModel);
    List<TaskModel> findAll();
}
