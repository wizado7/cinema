package dataaccess.crudoperation.exception;

public class InitializeConnectionException extends RuntimeException {

    public InitializeConnectionException() {
    }

    public InitializeConnectionException(String message) {
        super(message);
    }

    public InitializeConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializeConnectionException(Throwable cause) {
        super(cause);
    }

    public InitializeConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
