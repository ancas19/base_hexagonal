package co.com.ancas.use_cases.adapters;

import co.com.ancas.domain.model.TaskModel;
import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.use_cases.ports.CreateTask;

public class TaskAdapter implements CreateTask {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskAdapter(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public TaskModel create(TaskModel taskModel) {
        return this.taskRepositoryPort.create(taskModel);
    }
}
