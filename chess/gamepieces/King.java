package chess.gamepieces;

import chess.GamePiece;
import chess.Location;
import chess.PieceType;
import chess.Player;

public class King extends GamePiece {

    public King(Player player, Location location, PieceType pieceType) {
        super(player, location, pieceType);
    }


    @Override
    protected boolean canMove(GamePiece[][] board, Location source, Location destination) {
        // an array stating all the possible x,y movements the King is allowed to make
        final int[][] possibleMoves = { {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1} };

        // loop through all the King's possible moves
        for (int i = 0; i < possibleMoves.length; i++) {
            // if the source x co ordinate + x movement == the destination x co ordinate
            if (source.getX() + possibleMoves[i][0] == destination.getX()
                    // and if the source y co ordinate + y movement == the destination y co ordinate
                    && source.getY() + possibleMoves[i][1] == destination.getY()
                    // and the destination square is not already occupied
                    && clearPath(board, source, destination)){
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean canEat(GamePiece[][] board, Location source, Location destination) {
        // an array stating all the possible x,y movements the King is allowed to make
        final int[][] possibleMoves = { {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1} };

        // loop through all the King's possible moves
        for (int i = 0; i < possibleMoves.length; i++) {
            // if the source x co ordinate + x movement == the destination x co ordinate
            if (source.getX() + possibleMoves[i][0] == destination.getX()
                    // and if the source y co ordinate + y movement == the destination y co ordinate
                    && source.getY() + possibleMoves[i][1] == destination.getY()) {
                return true;
            }
        }
        return false;
    }

    // clearPath for King will always return true, as long as the destination is empty,
    // since its moves are always of 1 adjacent square
    @Override
    protected boolean clearPath(GamePiece[][] board, Location source, Location destination) {
        return (board[destination.getX()][destination.getY()] == null);
    }
}
