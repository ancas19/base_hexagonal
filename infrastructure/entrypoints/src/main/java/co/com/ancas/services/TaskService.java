package co.com.ancas.services;

import co.com.ancas.mapper.TaskMapper;
import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.use_cases.adapters.TaskPortAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskPortAdapter taskPortAdapter;

    public TaskService(TaskPortAdapter taskPortAdapter) {
        this.taskPortAdapter = taskPortAdapter;
    }


    @Transactional(value = "postgresTransactionManager", rollbackFor = Exception.class)
    public TaskResponse createTask(TaskRequest taskRequest) {
        return TaskMapper.mapToResponse(this.taskPortAdapter.create(TaskMapper.mapperToModel(taskRequest)));
    }
}
