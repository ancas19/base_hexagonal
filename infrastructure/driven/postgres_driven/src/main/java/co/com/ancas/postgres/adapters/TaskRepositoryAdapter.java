package co.com.ancas.postgres.adapters;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.postgres.mappers.TaskMapper;
import co.com.ancas.postgres.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<TaskModel> findAll() {
        return this.taskRepository.findAll()
                .stream()
                .map(TaskMapper::mapToModel)
                .toList();
    }
}
