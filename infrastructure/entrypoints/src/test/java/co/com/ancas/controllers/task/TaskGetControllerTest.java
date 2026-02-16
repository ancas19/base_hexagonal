package co.com.ancas.controllers.task;

import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.services.TaskService;
import co.com.ancas.utils.mocks.EntrypointDataMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(TaskGetController.class)
@DisplayName("TaskPostController Integration Tests")
class TaskGetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TaskService taskService;
    private RestTestClient restTestClient;
    private List<TaskResponse> taskResponseList;


    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindTo(mockMvc).build();
        taskResponseList = EntrypointDataMocks.taskResponseList();
    }



    @Test
    @DisplayName("Should get all tasks and return 200 OK")
    void shouldGetAllTasksSuccessfully() {
        // Arrange
        when(taskService.getAllTasks()).thenReturn(taskResponseList);
        // Act & Assert
        taskResponseList = restTestClient.get()
                .uri("/tasks")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<List<TaskResponse>>() {
                })
                .returnResult()
                .getResponseBody();
        assertNotNull(taskResponseList);
        assertEquals(2, taskResponseList.size());
        verify(taskService, times(1)).getAllTasks();
    }
}

