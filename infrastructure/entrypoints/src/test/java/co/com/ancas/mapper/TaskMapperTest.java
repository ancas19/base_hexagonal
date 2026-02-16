package co.com.ancas.mapper;

import co.com.ancas.domain.models.TaskModel;
import co.com.ancas.domain.utils.mocks.DataMocks;
import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.utils.mocks.EntrypointDataMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskMapper Unit Tests")
class TaskMapperTest {

    private TaskRequest taskRequest;
    private TaskModel taskModel;

    @BeforeEach
    void setUp() {
        taskRequest = EntrypointDataMocks.taskRequestInformation();
        taskModel = DataMocks.taskModelInformation();
    }

    @Test
    @DisplayName("Should map TaskRequest to TaskModel")
    void shouldMapRequestToModel() {
        // Act
        TaskModel result = TaskMapper.mapperToModel(taskRequest);

        // Assert
        assertNotNull(result);
        assertEquals(taskRequest.id(), result.getId());
        assertEquals(taskRequest.name(), result.getName());
        assertEquals(taskRequest.description(), result.getDescription());
        assertEquals(taskRequest.status(), result.getStatus());
    }

    @Test
    @DisplayName("Should return null when TaskRequest is null")
    void shouldReturnNullWhenRequestIsNull() {
        // Act
        TaskModel result = TaskMapper.mapperToModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Should map TaskModel to TaskResponse")
    void shouldMapModelToResponse() {
        // Act
        TaskResponse result = TaskMapper.mapToResponse(taskModel);

        // Assert
        assertNotNull(result);
        assertEquals(taskModel.getId(), result.id());
        assertEquals(taskModel.getName(), result.name());
        assertEquals(taskModel.getDescription(), result.description());
        assertEquals(taskModel.getStatus(), result.status());
        assertEquals(taskModel.getCreatedAt(), result.createdAt());
    }

    @Test
    @DisplayName("Should return null when TaskModel is null")
    void shouldReturnNullWhenModelIsNull() {
        // Act
        TaskResponse result = TaskMapper.mapToResponse(null);

        // Assert
        assertNull(result);
    }
}

