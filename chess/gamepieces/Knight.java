package chess.gamepieces;

import chess.GamePiece;
import chess.Location;
import chess.PieceType;
import chess.Player;

public class Knight extends GamePiece {

    public Knight(Player player, Location location, PieceType pieceType) {
        super(player, location, pieceType);
    }


    @Override
    protected boolean canMove(GamePiece[][] board, Location source, Location destination) {
        // find how much the Knight is moving on the x-axis
        int xMovement = Math.abs(source.getX()-destination.getX());
        // find how much the Knight is moving on the y-axis
        int yMovement = Math.abs(source.getY()-destination.getY());

        // if the knight is moving (1 left or right and 2 up or down) or (2 left or right and 1 up or down)
        if ( ( (xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement == 1) )
                // and if the destination square is empty
                && clearPath(board, source, destination)){
            return true;
        }

        return false;
    }

    @Override
    protected boolean canEat(GamePiece[][] board, Location source, Location destination) {
        // find how much the Knight is moving on the x-axis
        int xMovement = Math.abs(source.getX()-destination.getX());
        // find how much the Knight is moving on the y-axis
        int yMovement = Math.abs(source.getY()-destination.getY());

        // if the knight is moving (1 left or right and 2 up or down) or (2 left or right and 1 up or down)
        if ((xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement == 1)) {
            return true;
        }

        return false;
    }

    // clearPath for Knight will always return true, as long as the destination is empty,
    // since it can jump over other game pieces
    @Override
    protected boolean clearPath(GamePiece[][] board, Location source, Location destination) {
        return (board[destination.getX()][destination.getY()] == null);
    }
}
