package co.com.ancas.use_cases.adapters;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.use_cases.ports.CreateTaskPort;

public class TaskPortAdapter implements CreateTaskPort {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskPortAdapter(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public TaskModel create(TaskModel taskModel) {
        return this.taskRepositoryPort.create(taskModel);
    }
}
