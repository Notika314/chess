/**
 * @author Natalia Bryzhatenko nb631
 * @author Christopher Taglieri cat197
 */
package chess;
import java.util.Scanner;

public class Chess {

	private static Scanner scanner;
		
	public static void promote() {
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String line;
		scanner = new Scanner(System.in);
		
		Game game = new Game();
		King whiteKing = (King)game.board[4][7];
		King blackKing = (King)game.board[4][0];
		boolean drawOffer = false;
		char promote = 'Q';
		game.printBoard();
		game.updateValidMoves(-1);
		game.updateValidMoves(1);
		game.printBoard();
		System.out.print("White's move: ");
		line = scanner.nextLine();
		while (true) {
			String[] tokens = game.tokenizeInput(line);
			if (tokens.length < 1) {
				System.out.println("Illegal move, try again");
			}
			else if (tokens[0].contentEquals("resign")) {		//need to handle this - write the winner
				if (game.currMove == -1) {
					System.out.println("Black wins");
				}
				else {
					System.out.println("White wins");
				}
				return;
			} 
			else if (tokens[0].contentEquals("draw") && drawOffer) {	//handle this case
				return;
			} 
			else {
				if (tokens.length < 2) {
					System.out.println("Illegal move, try again");
				}
				else {
					drawOffer = false;
					
					if (tokens[0].length()!=2||tokens[1].length()!=2 || (int)tokens[0].charAt(0)<97 
							|| (int)tokens[0].charAt(0)>104  || tokens[0].charAt(1)<49 || tokens[0].charAt(1)>56 || 
							 (int)tokens[1].charAt(0)<97 || (int)tokens[1].charAt(0)>104  || 
							 tokens[1].charAt(1)<49 || tokens[1].charAt(1)>56 ) {
						System.out.println("Illegal move, try again");
						line = scanner.nextLine();
						continue;
					}
		
					Piece piece = game.findPiece(tokens[0]);
					if (tokens.length == 3) {
						if (tokens[2].contentEquals("draw?")) {
							drawOffer = true;
						}
						else if (tokens[2].contentEquals("R")) {
							promote = 'R';
						}
						else if (tokens[2].contentEquals("N")) {
							promote = 'N';
						}
						else if (tokens[2].contentEquals("B")) {
							promote = 'B';
						}
					}
					if (piece!=null) {
						int i = (int)(tokens[1].charAt(0)-97);
						int j = (int)(8-(tokens[1].charAt(1)-48));
						if (piece.move(game.board,i,j,game.currMove)) {
							if (piece.type == 'p' && ((game.currMove == -1 && j == 0) || (game.currMove == 1 && j == 7))) {
								if (promote == 'Q') {
									game.board[i][j] = new Queen(game.currMove,i,j);
								}
								else if (promote == 'R') {
									game.board[i][j] = new Rook(game.currMove,i,j);
								}
								else if (promote == 'N') {
									game.board[i][j] = new Knight(game.currMove,i,j);
								}
								else {
									game.board[i][j] = new Bishop(game.currMove,i,j);
								}
								promote = 'Q';
							}
							if (game.currMove == -1) {
								//whiteKing.isInCheck = false;
								
							}
							else {
								//blackKing.isInCheck = false;
							}
							game.currMove *= -1;
							game.printBoard();
							/////// I wonder if this part is still needed
							game.updateValidMoves(-game.currMove);
							game.updateValidMoves(game.currMove);
							game.disarmShields();
							/*
							if (game.currMove == -1) {
								whiteKing.updateStatus(game.board);

							}
							else {
								blackKing.updateStatus(game.board);
							}*/
							////////
							//whiteKing.generateValidMoves(game.board);
							//blackKing.generateValidMoves(game.board);
							
							
							///// Stalemate or Checkmate 
							King king2 = game.currMove==-1 ?  whiteKing : blackKing;
							if (king2.isInCheck && !king2.hasValidMove && !game.protector()) {
								String winner = game.currMove==-1? "Black" : "White" ;
								System.out.println(winner+" wins");
								return;
							}
							if (game.hasNoValidMoves() ) {
								King king = game.currMove==-1 ?  whiteKing : blackKing;
								if (!king.isInCheck) {
									System.out.println("Draw by stalemate");
									return;
								} else {
									String winner = game.currMove==-1? "Black" : "White" ;
									System.out.println(winner+" wins");
									return;
								}
							///// Stalemate
//							if (game.hasNoValidMoves()) {
//								System.out.println("Stalemate");
//								return;

							}
							//// end of Stalemate
							
							
						}
						else {
							System.out.println("Illegal move, try again");
						}
					}
					else {
						System.out.println("Illegal move, try again");
					}
				}
			}
			if (game.currMove == -1) {
				System.out.print("White's move: ");
			}
			else {
				System.out.print("Black's move: ");
			}
			line = scanner.nextLine(); 
		}	
	}

}
