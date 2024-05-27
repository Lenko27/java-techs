package Lenko27.exceptions;

public class NullEntityException extends RuntimeException{
    public NullEntityException() {
        super("Error: attempt to operate with null entity");
    }
    public NullEntityException(String message) {
        super(message);
    }
}
