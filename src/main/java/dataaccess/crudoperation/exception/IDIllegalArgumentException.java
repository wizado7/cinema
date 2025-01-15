package dataaccess.crudoperation.exception;

public class IDIllegalArgumentException extends IllegalArgumentException {

    public IDIllegalArgumentException() {
    }

    public IDIllegalArgumentException(String s) {
        super(s);
    }

    public IDIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IDIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
