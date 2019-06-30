package lajusticia.alejandro.zoo.animal.infrastructure.console.exception;

public class WrongCommandException extends Exception {

    private final static String MESSAGE = "Wrong command: %s, the commands availables are: %s";

    public WrongCommandException(String wrongCommand, String commands) {
        super(String.format(MESSAGE, wrongCommand, commands));
    }

}
