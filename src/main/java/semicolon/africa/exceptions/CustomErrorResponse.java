package semicolon.africa.exceptions;



public class CustomErrorResponse {
    private final int statusCode;
    private final String message;
    private final String description;

    public CustomErrorResponse(int statusCode, String message, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }

    // Getters and setters
    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
