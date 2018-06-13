package chess;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Declaring a List of commands
        List<Command> commands;
        // Populating the List of commands using the readInput method
        commands = CSVReader.readInput(args[0]);

        // Declaring a 2D array of type GamePiece which will store where each Game Piece is
        GamePiece[][] board = new GamePiece[8][8];

        // Declaring an ArrayList of type GamePiece for each player which will store all the pieces owned by that player
        ArrayList<GamePiece> playerPiecesW = new ArrayList<>();
        ArrayList<GamePiece> playerPiecesB = new ArrayList<>();

        // Calling the method which sets up the game board and initializes the list of players' pieces
        GameBoard.setUpBoard(board, playerPiecesW, playerPiecesB);

        // Calling the method which processes the commands
        int appliedCommands = GameEngine.processCommands(commands, board);


        // Calculating each player's score
        int whiteScore = GameEngine.calculateScore(board, Player.W);
        int blackScore = GameEngine.calculateScore(board, Player.B);

        // Printing the end of game stats
        System.out.println();

        // Printing the Number of Applied Commands
        System.out.println("Number of Applied Commands: " + appliedCommands);

        // Printing the Number of Invalid/Skipped Commands
        System.out.println("Number of Invalid/Skipped Commands: " + InvalidCommandException.getInvalidCommands());

        // Printing Winner
        if (whiteScore > blackScore){
            System.out.println("Winning Player: WHITE");
        } else if (blackScore > whiteScore){
            System.out.println("Winning Player: BLACK");
        } else {
            System.out.println("Match resulted in a TIE");
        }

        // Printing each player's Most Used Game Piece
        GamePiece mostUsedW = GameEngine.findMostUsedPiece(playerPiecesW);
        System.out.println("White Player's Most Used Chess Piece: " + mostUsedW.getPieceType().toString() + ", " + mostUsedW.getMoves());
        GamePiece mostUsedB = GameEngine.findMostUsedPiece(playerPiecesB);
        System.out.println("Black Player's Most Used Chess Piece: " + mostUsedB.getPieceType().toString() + ", " + mostUsedB.getMoves());

        // Calling the method which prints the current (final) state of the Game Board
        System.out.println("Last Chess Board State:");
        GameBoard.displayBoard(board);
    }
}