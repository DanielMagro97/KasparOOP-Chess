package chess.gamepieces;

import chess.GamePiece;
import chess.Location;
import chess.PieceType;
import chess.Player;

public class Rook extends GamePiece {

    public Rook(Player player, Location location, PieceType pieceType) {
        super(player, location, pieceType);
    }


    @Override
    protected boolean canMove(GamePiece[][] board, Location source, Location destination) {

        // if either x or y stays constant (i.e. the Rook only moves on one axis)
        if ( (source.getX()==destination.getX() || source.getY()==destination.getY())
                // and the path between source and destination is clear (clearPath)
                && clearPath(board, source, destination)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean canEat(GamePiece[][] board, Location source, Location destination) {

        int dirX = 0;
        int dirY = 0;

        if (destination.getX() == source.getX()){
            dirX = 0;
        } else if (destination.getX() > source.getX()) {
            dirX = 1;
        } else if (destination.getX() < source.getX()) {
            dirX = -1;
        }

        if (destination.getY() == source.getY()){
            dirY = 0;
        } else if (destination.getY() > source.getY()) {
            dirY = 1;
        } else if (destination.getY() < source.getY()) {
            dirY = -1;
        }

        // create a new destination, one which is one square before the destination, along the Bishop's path
        Location eatDestination = new Location(destination.getX()-dirX, destination.getY()-dirY);

        // return the result of the canMove from the Bishop's location to the newly created destination
        return canMove(board, source, eatDestination);
    }

    @Override
    protected boolean clearPath(GamePiece[][] board, Location source, Location destination) {

        int dirX = 0;
        int dirY = 0;

        // determining whether the Rook is moving left or right
        if (destination.getX() == source.getX()){
            dirX = 0;
        } else if (destination.getX() > source.getX()) {
            dirX = 1;
        } else if (destination.getX() < source.getX()) {
            dirX = -1;
        }

        // determining whether the Rook is moving up or down
        if (destination.getY() == source.getY()){
            dirY = 0;
        } else if (destination.getY() > source.getY()) {
            dirY = 1;
        } else if (destination.getY() < source.getY()) {
            dirY = -1;
        }

        // if the piece is moving vertically (i.e. X stays constant)
        if (dirX == 0) {
            // loop through all the squares the Rook must pass through on its way from the source to the destination
            // <= is used in order to also check for an empty destination
            for (int i = 1; i <= Math.abs(destination.getY() - source.getY()); i++) {
                // if a square along the path is occupied by another game piece
                if (board[source.getX()][source.getY() + i*dirY] != null) {
                    // then return false
                    return false;
                }
            }
        // else, if the piece is moving horizontally (i.e. Y stays constant)
        } else if (dirY == 0){
            for (int i = 1; i <= Math.abs(destination.getX()-source.getX()); i++) {
                if (board[source.getX() + i*dirX][source.getY()] != null){
                    return false;
                }
            }
        } else {
            return false;
        }

        // if no pieces are found along the Rook's path, return true
        return true;
    }
}
