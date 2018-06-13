package chess;

import chess.gamepieces.*;

import java.util.ArrayList;

public class GameBoard {

    // Method which initialises the Game Board with all the game pieces in their respective positions for both players
    static void setUpBoard(GamePiece[][] board, ArrayList<GamePiece> playerPiecesW, ArrayList<GamePiece> playerPiecesB){
        // Setting up the White Player's Pawns
        for (int i = 0; i < 8; i++) {
            // Creating a new GamePiece of type Pawn
            GamePiece p = new Pawn(Player.W, new Location(i,1), PieceType.PAWN);
            // Setting the contents of the current location on the board to that GamePiece
            board[i][1] = p;
            // Adding the GamePiece to the player's list of pieces
            playerPiecesW.add(p);
        }

        // Setting up the Black Player's Pawns
        for (int i = 0; i < 8; i++) {
            GamePiece p = new Pawn(Player.B, new Location(i,6), PieceType.PAWN);
            board[i][6] = p;
            playerPiecesB.add(p);
        }

        // Setting up the White Player's other Pieces
        for (int i = 0; i < 8; i++) {
            GamePiece p;

            if (i==0 || i==7){
                p = new Rook(Player.W, new Location(i,0), PieceType.ROOK);
            } else if (i==1 || i==6) {
                p = new Knight(Player.W, new Location(i,0), PieceType.KNIGHT);
            } else if (i==2 || i==5) {
                p = new Bishop(Player.W, new Location(i,0), PieceType.BISHOP);
            } else if (i==3) {
                p = new Queen(Player.W, new Location(i,0), PieceType.QUEEN);
            } else {
                p = new King(Player.W, new Location(i,0), PieceType.KING);
            }

            board[i][0] = p;
            playerPiecesW.add(p);
        }

        // Setting up the Black Player's other Pieces
        for (int i = 0; i < 8; i++) {
            GamePiece p;

            if (i==0 || i==7){
                p = new Rook(Player.B, new Location(i,7), PieceType.ROOK);
            } else if (i==1 || i==6) {
                p = new Knight(Player.B, new Location(i,7), PieceType.KNIGHT);
            } else if (i==2 || i==5) {
                p = new Bishop(Player.B, new Location(i,7), PieceType.BISHOP);
            } else if (i==3) {
                p = new King(Player.B, new Location(i,7), PieceType.KING);
            } else {
                p = new Queen(Player.B, new Location(i,7), PieceType.QUEEN);
            }

            board[i][7] = p;
            playerPiecesB.add(p);
        }
    }

    // method which prints out the game board
    static void displayBoard(GamePiece[][] board){
        // starts top down (hence descending)
        for (int j = 7; j >= 0; j--) {
            for (int i = 0; i < 8; i++) {
                if (board[i][j] != null) {
                    if (board[i][j].getPlayer() == Player.W) {
                        if (board[i][j].getPieceType() == PieceType.PAWN) {
                            System.out.print("♙\t");
                        } else if (board[i][j].getPieceType() == PieceType.ROOK) {
                            System.out.print("♖\t");
                        } else if (board[i][j].getPieceType() == PieceType.KNIGHT) {
                            System.out.print("♘\t");
                        } else if (board[i][j].getPieceType() == PieceType.BISHOP) {
                            System.out.print("♗\t");
                        } else if (board[i][j].getPieceType() == PieceType.QUEEN) {
                            System.out.print("♕\t");
                        } else if (board[i][j].getPieceType() == PieceType.KING) {
                            System.out.print("♔\t");
                        }
                    } else if (board[i][j].getPlayer() == Player.B) {
                        if (board[i][j].getPieceType() == PieceType.PAWN) {
                            System.out.print("♟\t");
                        } else if (board[i][j].getPieceType() == PieceType.ROOK) {
                            System.out.print("♜\t");
                        } else if (board[i][j].getPieceType() == PieceType.KNIGHT) {
                            System.out.print("♞\t");
                        } else if (board[i][j].getPieceType() == PieceType.BISHOP) {
                            System.out.print("♝\t");
                        } else if (board[i][j].getPieceType() == PieceType.QUEEN) {
                            System.out.print("♛\t");
                        } else if (board[i][j].getPieceType() == PieceType.KING) {
                            System.out.print("♚\t");
                        }
                    }
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }
}
