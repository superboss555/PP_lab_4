package Laba.Exception;

// Необъявляемое исключение
public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}
