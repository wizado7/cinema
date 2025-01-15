package dataaccess.crudoperation.exception;

public class TableIllegalArgumentException extends IllegalArgumentException {

    public TableIllegalArgumentException() {
    }

    public TableIllegalArgumentException(String s) {
        super(s);
    }

    public TableIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
