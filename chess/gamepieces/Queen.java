package chess.gamepieces;

import chess.GamePiece;
import chess.Location;
import chess.PieceType;
import chess.Player;

public class Queen extends GamePiece {

    public Queen(Player player, Location location, PieceType pieceType) {
        super(player, location, pieceType);
    }


    // Since the Queen moves like either the Bishop or the Rook, A Bishop and a Rook are instantiated.
    // This method returns true if either the Bishop or the Rook can make the Move (with their respective canMove methods)
    @Override
    protected boolean canMove(GamePiece[][] board, Location source, Location destination) {
        Bishop bishop = new Bishop(board[source.getX()][source.getY()].getPlayer(), source, PieceType.BISHOP);
        Rook rook = new Rook(board[source.getX()][source.getY()].getPlayer(), source, PieceType.ROOK);

        return  (bishop.canMove(board,source,destination) || rook.canMove(board, source, destination));
    }

    // Since the Queen moves like either the Bishop or the Rook, A Bishop and a Rook are instantiated.
    // This method returns true if either the Bishop or the Rook can make the Eat (with their respective canEat methods)
    @Override
    protected boolean canEat(GamePiece[][] board, Location source, Location destination) {
        Bishop bishop = new Bishop(board[source.getX()][source.getY()].getPlayer(), source, PieceType.BISHOP);
        Rook rook = new Rook(board[source.getX()][source.getY()].getPlayer(), source, PieceType.ROOK);

        return (bishop.canEat(board,source,destination) || rook.canEat(board, source, destination));
    }

    @Override
    protected boolean clearPath(GamePiece[][] board, Location source, Location destination) {
        return false;
    }
}
