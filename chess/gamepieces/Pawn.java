package chess.gamepieces;

import chess.GamePiece;
import chess.Location;
import chess.PieceType;
import chess.Player;

public class Pawn extends GamePiece {

    public Pawn(Player player, Location location, PieceType pieceType) {
        super(player, location, pieceType);
    }


    @Override
    protected boolean canMove(GamePiece[][] board, Location source, Location destination) {
        // since the Pawn can only move forward, its X co ordinate must remain the same
        if (source.getX() == destination.getX()) {
            // make sure the path between the Pawn and its destination are empty
            if (clearPath(board, source, destination)) {
                // If the Pawn is White, it can only move forward
                if (getPlayer() == Player.W) {
                    // the destination can be one square forward of where it currently is
                    if (source.getY() + 1 == destination.getY()) {
                        return true;
                    // or 2 squares forward if it is the first move of the Pawn
                    } else if (source.getY() + 2 == destination.getY() && getMoves() == 0) {
                        return true;
                    }
                // If the Pawn is Black, it can only move 'backwards'
                } else if (getPlayer() == Player.B){
                    if (source.getY() -1 == destination.getY()) {
                        return true;
                    } else if (source.getY() - 2 == destination.getY() && getMoves() == 0) {
                        return true;
                    }
                }
            }
        }

        // if either of the above conditions was not met, return false (cannot move)
        return false;
    }

    @Override
    protected boolean canEat(GamePiece[][] board, Location source, Location destination) {
        // when eating, the X co ordinate of the Pawn can only increase or decrease by 1 (since it moves diagonally)
        if (source.getX()+1 == destination.getX() || source.getX()-1 == destination.getX()){
            // if a White Pawn will be eating, it can only move 1 square forward
            if (getPlayer() == Player.W) {
                if (source.getY() + 1 == destination.getY()) {
                    return true;
                }
            // if a Black pawn will be eating, it can only move 1 square 'backwards'
            } else if (getPlayer() == Player.B){
                if (source.getY()-1 == destination.getY()){
                    return true;
                }
            }
        }

        // if either of the above conditions was not met, return false (cannot eat)
        return false;
    }

    @Override
    protected boolean clearPath(GamePiece[][] board, Location source, Location destination) {
        int y = source.getY();
        do {
            // checking for out of bounds
            if (y < 0 || y > 7){
                return false;
            }

            // since white moves forward, increment y
            if (getPlayer() == Player.W) {
                y++;
            // since black moves backwards, decrement y
            } else if (getPlayer() == Player.B) {
                y--;
            }

            // if one square along the way is not empty, return false for clearPath
            if (board[destination.getX()][y] != null) {
                return false;
            }
        // do this check until all squares between source and destination have been checked
        } while (y != destination.getY());
        return true;
    }
}
