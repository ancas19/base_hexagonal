package co.com.ancas.use_cases.adapters;

import co.com.ancas.domain.exceptions.NotFoundException;
import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.use_cases.ports.CreateTaskPort;
import co.com.ancas.use_cases.ports.FindTaskPort;

import java.util.List;

import static co.com.ancas.domain.enums.ErrorMessages.ERROR_TASKS_NOT_FOUND;

public class TaskPortAdapter implements CreateTaskPort, FindTaskPort {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskPortAdapter(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public TaskModel create(TaskModel taskModel) {
        return this.taskRepositoryPort.create(taskModel);
    }

    @Override
    public List<TaskModel> findAll() {
        List<TaskModel> taskModelsFound = this.taskRepositoryPort.findAll();
        if (taskModelsFound.isEmpty()) {
            throw new NotFoundException(ERROR_TASKS_NOT_FOUND.getMessage());
        }
        return taskModelsFound;
    }
}
