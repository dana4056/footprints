package footprints.delivery.exception;

public class AuthenticationNumberMismatchException extends RuntimeException {
    public AuthenticationNumberMismatchException(String message){
        super(message);
    }
}
