package Internship;

/**
 * Mój własny wyjątek wyrzucany w przypadku podania null jako węzła w niektórych metodach.
 */
public class EmptyTreeException extends Throwable {
    public EmptyTreeException(String errorMessage) {
        super(errorMessage);
    }
}
