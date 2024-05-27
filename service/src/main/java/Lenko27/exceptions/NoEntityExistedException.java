package Lenko27.exceptions;

public class NoEntityExistedException extends RuntimeException{
    public NoEntityExistedException() {
        super("Error: no entity existed");
    }
    public NoEntityExistedException(String message) {
        super(message);
    }
}
