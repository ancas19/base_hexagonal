package co.com.ancas.postgres.adapter;

import co.com.ancas.domain.model.TaskModel;
import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.postgres.mappers.TaskMapper;
import co.com.ancas.postgres.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskRepositoryAdapter implements TaskRepositoryPort {
    private final TaskRepository taskRepository;

    public TaskRepositoryAdapter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskModel create(TaskModel taskModel) {
        return TaskMapper.mapToModel(this.taskRepository.save(TaskMapper.mapToEntity(taskModel)));
    }
}
