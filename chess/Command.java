package chess;

public class Command{
    private Player player;
    private CommandType commandType;
    private Location source;
    private Location destination;

    public Command(Player player, CommandType commandType, Location source, Location destination){
        this.player = player;
        this.commandType = commandType;
        this.source = source;
        this.destination = destination;
    }

    public Player getPlayer() {
        return player;
    }

    CommandType getCommandType() {
        return commandType;
    }

    Location getSource() {
        return source;
    }

    Location getDestination() {
        return destination;
    }


    // toString override, which returns a Command in a readable manner, as entered by the user (eg.  W,MOVE,b2,b4)
    @Override
    public String toString() {
        return player + "," + commandType + "," + source + "," + destination;
    }

}