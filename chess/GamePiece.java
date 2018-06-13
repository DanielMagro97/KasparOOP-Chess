package chess;

public abstract class GamePiece {

    private Player player;
    private Location location;  // this attribute was not used by the program
    private PieceType pieceType;
    private int moves = 0;

    protected abstract boolean canMove(GamePiece[][] board, Location source, Location destination);
    protected abstract boolean canEat(GamePiece[][] board, Location source, Location destination);
    protected abstract boolean clearPath(GamePiece[][] board, Location source, Location destination);

    public GamePiece(Player player, Location location, PieceType pieceType){
        this.player = player;
        this.location = location;
        this.pieceType = pieceType;
    }

    // Player Setter
    public void setPlayer(Player player){
        this.player = player;
    }
    // Player Getter
    public Player getPlayer(){
        return player;
    }

    // Location Setter
    public void setLocation(Location location){
        this.location = location;
    }
    // Location Getter
    public Location getLocation(){
        return location;
    }

    // PieceType Getter
    public PieceType getPieceType(){
        return pieceType;
    }

    // moves incrementer - final so that it is not overridden
    public final void incrementMoves(){
        moves++;
    }
    // moves Getter
    public int getMoves() {
        return moves;
    }
}
