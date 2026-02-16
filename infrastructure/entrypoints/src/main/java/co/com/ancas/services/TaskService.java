package co.com.ancas.services;

import co.com.ancas.mapper.TaskMapper;
import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.use_cases.adapters.TaskPortAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h1> Task Service </h1>
 * <p> This service is responsible for handling the creation of a new task </p>
 */
@Service
public class TaskService {

    private final TaskPortAdapter taskPortAdapter;

    public TaskService(TaskPortAdapter taskPortAdapter) {
        this.taskPortAdapter = taskPortAdapter;
    }

    /**
     * <h1> Create Task </h1>
     * <p> This method is responsible for creating a new task </p>
     */
    @Transactional(value = "postgresTransactionManager", rollbackFor = Exception.class)
    public TaskResponse createTask(TaskRequest taskRequest) {
        return TaskMapper.mapToResponse(this.taskPortAdapter.create(TaskMapper.mapperToModel(taskRequest)));
    }

    /**
     * <h1> Get All Tasks </h1>
     * <p> This method is responsible for getting all tasks </p>
     */
    @Transactional(value = "postgresTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<TaskResponse> getAllTasks() {
        return this.taskPortAdapter.findAll().stream().map(TaskMapper::mapToResponse).toList();
    }
}
