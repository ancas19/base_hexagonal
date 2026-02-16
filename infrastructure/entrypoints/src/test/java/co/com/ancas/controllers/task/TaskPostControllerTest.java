package co.com.ancas.controllers.task;

import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.services.TaskService;
import co.com.ancas.utils.mocks.EntrypointDataMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(TaskPostController.class)
@DisplayName("TaskPostController Integration Tests")
class TaskPostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TaskService taskService;
    private RestTestClient restTestClient;
    private TaskRequest taskRequest;
    private TaskResponse taskResponse;

    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindTo(mockMvc).build();
        taskRequest = EntrypointDataMocks.taskRequestInformation();
        taskResponse = EntrypointDataMocks.taskResponseInformation();
    }

    @Test
    @DisplayName("Should create task and return 201 CREATED with proper JSON response")
    void shouldCreateTaskSuccessfully(){
        // Arrange
        when(taskService.createTask(any(TaskRequest.class))).thenReturn(taskResponse);
        // Act & Assert
        taskResponse = restTestClient.post()
                .uri("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskRequest)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<TaskResponse>() {
                })
                .returnResult()
                .getResponseBody();
        assertNotNull(taskResponse);
        assertNotNull(taskResponse.id());
        verify(taskService, times(1)).createTask(any(TaskRequest.class));
    }
}

