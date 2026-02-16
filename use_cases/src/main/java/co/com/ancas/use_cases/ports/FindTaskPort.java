package co.com.ancas.use_cases.ports;

import co.com.ancas.domain.models.TaskModel;

import java.util.List;

public interface FindTaskPort {
    List<TaskModel> findAll();
}
