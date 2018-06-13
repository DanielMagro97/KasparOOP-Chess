package chess;

public class InvalidCommandException extends Exception {

    private static int invalidCommands = 0;

    public InvalidCommandException() {
        super("The Command is Invalid!");
        invalidCommands++;
    }

    public static int getInvalidCommands() {
        return invalidCommands;
    }
}
