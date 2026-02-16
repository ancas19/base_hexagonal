package co.com.ancas.use_cases.adapters;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.domain.utils.mocks.DataMocks;
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
@DisplayName("TaskPortAdapter Unit Tests")
class TaskPortAdapterTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;
    @InjectMocks
    private TaskPortAdapter taskPortAdapter;
    private TaskModel taskModel;

    @BeforeEach
    void setUp() {
        taskModel = DataMocks.taskModelInformation();
    }

    @Test
    @DisplayName("Should create task successfully using DataMocks")
    void shouldCreateTaskSuccessfully() {
        // Arrange
        when(taskRepositoryPort.create(any(TaskModel.class))).thenReturn(taskModel);
        // Act
        TaskModel result = taskPortAdapter.create(taskModel);

        // Assert
        assertNotNull(result);
        assertEquals(taskModel.getId(), result.getId());
        assertEquals(taskModel.getName(), result.getName());
        assertEquals(taskModel.getDescription(), result.getDescription());
        assertEquals(taskModel.getStatus(), result.getStatus());
        assertEquals(taskModel.getCreatedAt(), result.getCreatedAt());

        verify(taskRepositoryPort, times(1)).create(taskModel);
    }


}

