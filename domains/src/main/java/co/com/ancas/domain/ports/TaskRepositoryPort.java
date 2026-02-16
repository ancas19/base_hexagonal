package co.com.ancas.domain.ports;

import co.com.ancas.domain.models.TaskModel;

public interface TaskRepositoryPort {
    TaskModel create(TaskModel taskModel);
}
