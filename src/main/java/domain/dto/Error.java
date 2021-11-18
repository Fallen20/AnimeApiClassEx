package domain.dto;

public class Error {
    public String message;

    public static Error message(String message) {
        Error error = new Error();
        error.message = message;
        return error;
    }

}
