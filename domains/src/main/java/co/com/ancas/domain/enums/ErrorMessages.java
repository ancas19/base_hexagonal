package co.com.ancas.domain.enums;

public enum ErrorMessages {

    ERROR_GENERAL_MESSAGE("An unexpected error occurred, please try again later"),
    ERROR_TASKS_NOT_FOUND("No tasks found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
