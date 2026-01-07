package customexception;

public class InvalidNIDException extends Exception {
    public InvalidNIDException(String message) {
        super(message);
    }
}