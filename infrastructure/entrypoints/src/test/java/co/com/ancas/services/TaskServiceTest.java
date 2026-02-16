package co.com.ancas.services;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.utils.mocks.DataMocks;
import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.use_cases.adapters.TaskPortAdapter;
import co.com.ancas.utils.mocks.EntrypointDataMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskService Unit Tests")
class TaskServiceTest {

    @Mock
    private TaskPortAdapter taskPortAdapter;

    @InjectMocks
    private TaskService taskService;

    private TaskRequest taskRequest;
    private TaskModel taskModel;
    private List<TaskModel> taskModelList;

    @BeforeEach
    void setUp() {
        taskRequest = EntrypointDataMocks.taskRequestInformation();
        taskModel = DataMocks.taskModelInformation();
        taskModelList = DataMocks.taskModelList();
    }

    @Test
    @DisplayName("Should create task and return response")
    void shouldCreateTaskSuccessfully() {
        // Arrange
        when(taskPortAdapter.create(any(TaskModel.class))).thenReturn(taskModel);

        // Act
        TaskResponse result = taskService.createTask(taskRequest);

        // Assert
        assertNotNull(result);
        assertEquals(taskModel.getId(), result.id());
        assertEquals(taskModel.getName(), result.name());
        assertEquals(taskModel.getDescription(), result.description());
        assertEquals(taskModel.getStatus(), result.status());
        assertNotNull(result.createdAt());

        verify(taskPortAdapter, times(1)).create(any(TaskModel.class));
    }

    @Test
    @DisplayName("Should get all tasks and return list of responses")
    void shouldGetAllTasksSuccessfully() {
        // Arrange
        when(taskPortAdapter.findAll()).thenReturn(taskModelList);

        // Act
        List<TaskResponse> result = taskService.getAllTasks();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).name());
        assertEquals("Task 2", result.get(1).name());
        assertEquals("PENDING", result.get(0).status());
        assertEquals("IN_PROGRESS", result.get(1).status());

        verify(taskPortAdapter, times(1)).findAll();
    }
}

