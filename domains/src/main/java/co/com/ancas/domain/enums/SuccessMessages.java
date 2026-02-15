package co.com.ancas.domain.enums;

public enum SuccessMessages {

    TASK_CREATED("Task created successfully"),
    TASK_UPDATED("Task updated successfully"),
    TASK_DELETED("Task deleted successfully");

    private final String message;

    SuccessMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
