import java.lang.Exception;


public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super();
    }

    public EmptyQueueException(String errorMsg) {
        super(errorMsg);
    }
}
