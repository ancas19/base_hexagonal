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

import java.util.Arrays;
import java.util.List;

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
    private List<TaskModel> taskModelList;
    private List<TaskEntity> taskEntityList;

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

        taskModelList = DataMocks.taskModelList();
        taskEntityList = Arrays.asList(
                TaskEntity.builder()
                        .id(taskModelList.get(0).getId())
                        .name(taskModelList.get(0).getName())
                        .description(taskModelList.get(0).getDescription())
                        .status(taskModelList.get(0).getStatus())
                        .createdAt(taskModelList.get(0).getCreatedAt())
                        .build(),
                TaskEntity.builder()
                        .id(taskModelList.get(1).getId())
                        .name(taskModelList.get(1).getName())
                        .description(taskModelList.get(1).getDescription())
                        .status(taskModelList.get(1).getStatus())
                        .createdAt(taskModelList.get(1).getCreatedAt())
                        .build()
        );
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

    @Test
    @DisplayName("Should find all tasks from database")
    void shouldFindAllTasksSuccessfully() {
        // Arrange
        when(taskRepository.findAll()).thenReturn(taskEntityList);

        // Act
        List<TaskModel> result = taskRepositoryAdapter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getName());
        assertEquals("Task 2", result.get(1).getName());
        assertEquals("PENDING", result.get(0).getStatus());
        assertEquals("IN_PROGRESS", result.get(1).getStatus());

        verify(taskRepository, times(1)).findAll();
    }
}

