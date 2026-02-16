package co.com.ancas.postgres.mappers;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.utils.mocks.DataMocks;
import co.com.ancas.postgres.entities.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskMapper (Postgres) Unit Tests")
class TaskMapperTest {

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
    @DisplayName("Should map TaskModel to TaskEntity")
    void shouldMapModelToEntity() {
        // Act
        TaskEntity result = TaskMapper.mapToEntity(taskModel);

        // Assert
        assertNotNull(result);
        assertEquals(taskModel.getId(), result.getId());
        assertEquals(taskModel.getName(), result.getName());
        assertEquals(taskModel.getDescription(), result.getDescription());
        assertEquals(taskModel.getStatus(), result.getStatus());
        assertNotNull(result.getCreatedAt());
    }

    @Test
    @DisplayName("Should map TaskEntity to TaskModel")
    void shouldMapEntityToModel() {
        // Act
        TaskModel result = TaskMapper.mapToModel(taskEntity);

        // Assert
        assertNotNull(result);
        assertEquals(taskEntity.getId(), result.getId());
        assertEquals(taskEntity.getName(), result.getName());
        assertEquals(taskEntity.getDescription(), result.getDescription());
        assertEquals(taskEntity.getStatus(), result.getStatus());
        assertEquals(taskEntity.getCreatedAt(), result.getCreatedAt());
    }
}

