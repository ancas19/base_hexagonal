package co.com.ancas.use_cases.ports;

import co.com.ancas.domain.model.TaskModel;

public interface CreateTask {
    TaskModel create(TaskModel taskModel);
}
