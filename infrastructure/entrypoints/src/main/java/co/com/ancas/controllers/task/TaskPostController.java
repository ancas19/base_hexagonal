package co.com.ancas.controllers.task;

import co.com.ancas.requests.TaskRequest;
import co.com.ancas.responses.TaskResponse;
import co.com.ancas.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  <h1> Task Post Controller </h1>
 *  <p> This controller is responsible for handling the creation of a new task </p>
 */
@RestController
@RequestMapping("tasks")
@Tag(name="Controller Post task", description="Controller for post operations of tasks")
public class TaskPostController {

    private final TaskService taskService;

    public TaskPostController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new task",
            description = "Create a new task"
    )
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody @Valid TaskRequest taskRequest
    ) {
        return new ResponseEntity<>(this.taskService.createTask(taskRequest),HttpStatus.CREATED);
    }
}
