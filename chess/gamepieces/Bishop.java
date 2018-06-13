package chess.gamepieces;

import chess.GamePiece;
import chess.Location;
import chess.PieceType;
import chess.Player;

public class Bishop extends GamePiece {

    public Bishop(Player player, Location location, PieceType pieceType) {
        super(player, location, pieceType);
    }


    // bishop canMove method adapted from https://stackoverflow.com/a/4305409/3298275
    @Override
    protected boolean canMove(GamePiece[][] board, Location source, Location destination) {

        // if the 'gradient' is an integer, i.e. every x movement is matched by a y movement, leaving the path diagonal
        if (Math.abs(destination.getX()-source.getX()) == Math.abs(source.getY()-destination.getY())
                // and if the path from the source to the destination is clear
                && clearPath(board, source, destination)) {
            return true;
        }

        // else
        return false;
    }

    // canEat method works such that it is checked whether the Bishop canMove up to one square before the destination
    @Override
    protected boolean canEat(GamePiece[][] board, Location source, Location destination) {
        // ternary statement which determines if the bishop is moving left or right
        int dirX = destination.getX() > source.getX() ? 1 : -1;
        // ternary statement which determines if the bishop is moving up or down
        int dirY = destination.getY() > source.getY() ? 1 : -1;

        // create a new destination, one which is one square before the destination, along the Bishop's path
        Location eatDestination = new Location(destination.getX()-dirX, destination.getY()-dirY);

        // return the result of the canMove from the Bishop's location to the newly created destination
        return canMove(board, source, eatDestination);
    }

    // bishop clearPath method adapted from https://stackoverflow.com/a/4305409/3298275
    @Override
    protected boolean clearPath(GamePiece[][] board, Location source, Location destination) {

        // ternary statement which determines if the bishop is moving left or right
        int dirX = destination.getX() > source.getX() ? 1 : -1;
        // ternary statement which determines if the bishop is moving up or down
        int dirY = destination.getY() > source.getY() ? 1 : -1;

        // loop through all the squares the Bishop must pass through on its way from the source to the destination
        // <= is used in order to also check for an empty destination
        for (int i=1; i <= Math.abs(destination.getX()-source.getX()); i++) {
            // if the square which is between the source and the destination is occupied by another piece
            if (board[source.getX()+i*dirX][source.getY()+i*dirY] != null) {
                // return that there is not a clear path
                return false;
            }
        }

        // if all squares between the source and destination are empty, return true, i.e. path is clear
        return true;
    }
}
