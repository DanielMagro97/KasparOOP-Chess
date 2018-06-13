package chess;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    // Method which processes the list of commands and applies them to the Game Board after checking that they are legal
    static int processCommands(List<Command> commands, GamePiece[][] board){
        int appliedCommands = 0;

        for (Command command : commands) {
            try {
                // if the command is of type MOVE
                if (isMoveCommand(command)) {
                    // if the source square is not empty (i.e. there is a Game Piece on that square)
                    if (!isEmptySquare(command.getSource(), board)
                            // it is also checked that the destination square is empty by each GamePiece's clearPath method
                            // and that game piece is owned by the Player issuing the command
                            && isOfPlayer(board[command.getSource().getX()][command.getSource().getY()], command.getPlayer())
                            // and the piece on the source square can legally move to its destination (checked using the piece's canMove method)
                            && board[command.getSource().getX()][command.getSource().getY()].canMove(board, command.getSource(), command.getDestination())) {
                        // if all that^ then commit the move

                        // move the piece to the destination from the source
                        board[command.getDestination().getX()][command.getDestination().getY()]
                                = board[command.getSource().getX()][command.getSource().getY()];
                        // mark the source as now empty
                        board[command.getSource().getX()][command.getSource().getY()] = null;
                        // increment the piece's move counter
                        board[command.getDestination().getX()][command.getDestination().getY()].incrementMoves();

                        // incrementing the number of applied commands
                        // subject-observer design patter could have been implemented here (update()) to count the number of moves
                        appliedCommands++;
                    } else {
                        throw new InvalidCommandException();
                    }

                    // if the command is of type EAT
                } else if (isEatCommand(command)) {
                    // if the source square is not empty (i.e. there is a Game Piece on that square)
                    if (!isEmptySquare(command.getSource(), board)
                            // and if the destination square is not empty (i.e. there is a Game Piece on that square)
                            && !isEmptySquare(command.getDestination(), board)
                            // and if the piece is going to eat a piece of the other player
                            && !isOfPlayer(board[command.getDestination().getX()][command.getDestination().getY()], command.getPlayer())
                            // and that game piece is owned by the Player issuing the command
                            && isOfPlayer(board[command.getSource().getX()][command.getSource().getY()], command.getPlayer())
                            // and the piece on the source square can legally eat the piece at the destination (checked using the piece's canEat method)
                            && board[command.getSource().getX()][command.getSource().getY()].canEat(board, command.getSource(), command.getDestination())) {
                        // if all that^ then commit the eat

                        // move the piece to the destination from the source
                        board[command.getDestination().getX()][command.getDestination().getY()]
                                = board[command.getSource().getX()][command.getSource().getY()];
                        // mark the source as now empty
                        board[command.getSource().getX()][command.getSource().getY()] = null;
                        // increment the piece's move counter
                        board[command.getDestination().getX()][command.getDestination().getY()].incrementMoves();

                        // incrementing the number of applied commands
                        appliedCommands++;
                    } else {
                        throw new InvalidCommandException();
                    }

                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                System.out.println("Cannot execute command: " + command);
            }
        }

        return appliedCommands;
    }

    // Method which determines whether a Command is a MOVE command or not
    private static boolean isMoveCommand(Command command){
        return  (command.getCommandType() == CommandType.MOVE);
    }
    // Method which determines whether a Command is an EAT command or not
    private static boolean isEatCommand(Command command){
        return  (command.getCommandType() == CommandType.EAT);
    }
    // Method which determines if a particular Square on the Game Board is occupied or not
    private static boolean isEmptySquare(Location location, GamePiece[][] board){
        return  (board[location.getX()][location.getY()] == null);
    }
    // Method which determines if a particular GamePiece is owned by a particular Player
    private static boolean isOfPlayer(GamePiece gamePiece, Player player){
        return (gamePiece.getPlayer() == player);
    }

    // Method which finds the most used piece from a List of Game Pieces
    static GamePiece findMostUsedPiece(ArrayList<GamePiece> playerPieces){
        GamePiece mostUsed = playerPieces.get(0);

        for (GamePiece piece:playerPieces) {
            if (piece.getMoves() > mostUsed.getMoves()){
                mostUsed = piece;
            }
        }

        return mostUsed;
    }

    // Method which calculates the score given the final Game Board state and the Player
    static int calculateScore(GamePiece[][] board, Player player){
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getPlayer() == player){
                    if (board[i][j].getPieceType() == PieceType.PAWN){
                        score += 1;
                    } else if (board[i][j].getPieceType() == PieceType.BISHOP){
                        score += 3;
                    } else if (board[i][j].getPieceType() == PieceType.KNIGHT){
                        score += 3;
                    } else if (board[i][j].getPieceType() == PieceType.ROOK){
                        score += 5;
                    } else if (board[i][j].getPieceType() == PieceType.QUEEN){
                        score += 9;
                    } else if (board[i][j].getPieceType() == PieceType.KING){
                        score += 20;
                    }
                }
            }
        }
        return score;
    }
}
