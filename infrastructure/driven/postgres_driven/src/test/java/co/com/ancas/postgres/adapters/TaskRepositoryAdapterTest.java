package co.com.ancas.postgres.adapters;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.utils.mocks.DataMocks;
import co.com.ancas.postgres.entities.TaskEntity;
import co.com.ancas.postgres.repositories.TaskRepository;
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
@DisplayName("TaskRepositoryAdapter Unit Tests")
class TaskRepositoryAdapterTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskRepositoryAdapter taskRepositoryAdapter;

    private TaskModel taskModel;
    private TaskEntity taskEntity;

    @BeforeEach
    void setUp() {
        taskModel = DataMocks.taskModelInformation();
        taskEntity = TaskEntity.builder()
                .id(taskModel.getId())
                .name(taskModel.getName())
                .description(taskModel.getDescription())
                .status(taskModel.getStatus())
                .createdAt(taskModel.getCreatedAt())
                .build();
    }

    @Test
    @DisplayName("Should create task and save to database")
    void shouldCreateTaskSuccessfully() {
        // Arrange
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

        // Act
        TaskModel result = taskRepositoryAdapter.create(taskModel);

        // Assert
        assertNotNull(result);
        assertEquals(taskModel.getId(), result.getId());
        assertEquals(taskModel.getName(), result.getName());
        assertEquals(taskModel.getDescription(), result.getDescription());
        assertEquals(taskModel.getStatus(), result.getStatus());
        assertNotNull(result.getCreatedAt());

        verify(taskRepository, times(1)).save(any(TaskEntity.class));
    }
}

