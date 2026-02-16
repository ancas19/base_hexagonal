package co.com.ancas.services;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.utils.mocks.DataMocks;
import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.use_cases.ports.CreateTaskPort;
import co.com.ancas.utils.mocks.EntrypointDataMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskService Unit Tests")
class TaskServiceTest {

    @Mock
    private CreateTaskPort createTaskPort;

    @InjectMocks
    private TaskService taskService;

    private TaskRequest taskRequest;
    private TaskModel taskModel;

    @BeforeEach
    void setUp() {
        taskRequest = EntrypointDataMocks.taskRequestInformation();
        taskModel = DataMocks.taskModelInformation();
    }

    @Test
    @DisplayName("Should create task and return response")
    void shouldCreateTaskSuccessfully() {
        // Arrange
        when(createTaskPort.create(any(TaskModel.class))).thenReturn(taskModel);

        // Act
        TaskResponse result = taskService.createTask(taskRequest);

        // Assert
        assertNotNull(result);
        assertEquals(taskModel.getId(), result.id());
        assertEquals(taskModel.getName(), result.name());
        assertEquals(taskModel.getDescription(), result.description());
        assertEquals(taskModel.getStatus(), result.status());
        assertNotNull(result.createdAt());

        verify(createTaskPort, times(1)).create(any(TaskModel.class));
    }
}

