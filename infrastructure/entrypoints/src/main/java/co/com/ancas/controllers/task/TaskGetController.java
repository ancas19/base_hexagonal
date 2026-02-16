package co.com.ancas.controllers.task;

import co.com.ancas.responses.TaskResponse;
import co.com.ancas.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  <h1> Task Get Controller </h1>
 *  <p> This controller is responsible for handling the retrieval of tasks </p>
 */
@RestController
@RequestMapping("tasks")
@Tag(name="Controller Get task", description="Controller for get operations of tasks")
public class TaskGetController {

    private final TaskService taskService;

    public TaskGetController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(
            summary = "Get all tasks",
            description = "Retrieve all tasks from the database"
    )
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
    }
}

