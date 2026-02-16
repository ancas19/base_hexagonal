package co.com.ancas.domain.enums;

public enum ErrorMessages {

    ERROR_GENERAL_MESSAGE("An unexpected error occurred, please try again later"),;

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
