package co.com.ancas.use_cases.ports;

import co.com.ancas.domain.models.TaskModel;

public interface CreateTaskPort {
    TaskModel create(TaskModel taskModel);
}
