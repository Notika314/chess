/**
 * @author Natalia Bryzhatenko nb631
 * @author Christopher Taglieri cat197
 */
package chess;
import java.io.File;
import java.io.FileNotFoundException;
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
		
		/*
		try {
			scanner = new Scanner(new File("Stalemate.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Game game = new Game();
		King whiteKing = (King)game.board[4][7];
		King blackKing = (King)game.board[4][0];
		boolean drawOffer = false;
		char promote = 'Q';
//		game.printBoard();
		game.updateValidMoves(-1);
		game.updateValidMoves(1);
		whiteKing.generateValidMoves(game.board);
		blackKing.generateValidMoves(game.board);
		game.printBoard();
		System.out.print("White's move: ");
		line = scanner.nextLine();
		while (true) {
			String[] tokens = game.tokenizeInput(line);
			
			//System.out.println(line);	
			/*
			if (line.equals("c4 b5")) {
				scanner = new Scanner(System.in);
			}*/
			
			
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
						else {
							if (piece != null && (piece.type != 'p' || (
									(piece.color == -1 && piece.yPos != 1) || (piece.color == 1 && piece.yPos != 6)))) {
								System.out.println("Illegal move, try again");
								line = scanner.nextLine();
								continue;
							}
							else if (tokens[2].equals("Q")) {
								promote = 'Q';
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
							else {
								System.out.println("Illegal move, try again");
								line = scanner.nextLine();
								continue;
							}
						}
						
					}
					if (piece!=null) {
						int i = (int)(tokens[1].charAt(0)-97);
						int j = (int)(8-(tokens[1].charAt(1)-48));
						if (piece.move(game.board,i,j,game.currMove)) {
							if (piece.type == 'p' && ((piece.color == -1 && j == 0) || (piece.color == 1 && j == 7))) {
								if (promote == 'Q') {
									game.board[i][j] = new Queen(piece.color,i,j);
								}
								else if (promote == 'R') {
									game.board[i][j] = new Rook(piece.color,i,j);
								}
								else if (promote == 'N') {
									game.board[i][j] = new Knight(piece.color,i,j);
								}
								else {
									game.board[i][j] = new Bishop(piece.color,i,j);
								}
								promote = 'Q';
							}
							if (game.currMove == -1) {
								whiteKing.isInCheck = false;
								
							}
							else {
								blackKing.isInCheck = false;
							}
							
							game.currMove *= -1;
							game.clearPassant(game.currMove);
							game.printBoard();
							/////// I wonder if this part is still needed
							game.updateValidMoves(-game.currMove);
							game.updateValidMoves(game.currMove);
							if (game.currMove == -1) {
								blackKing.generateValidMoves(game.board);
								whiteKing.generateValidMoves(game.board);
							}
							else {
								whiteKing.generateValidMoves(game.board);
								blackKing.generateValidMoves(game.board);
							}
							///// Stalemate or Checkmate 
							King king2 = game.currMove==-1 ?  whiteKing : blackKing;
							if (king2.isInCheck && !king2.hasValidMove && !game.protector() && !game.blocker()) {
								System.out.println("Checkmate");
								String winner = game.currMove==-1? "Black" : "White" ;
								System.out.println(winner+" wins");
								return;
							}
							if (game.hasNoValidMoves() ) {
								King king = game.currMove==-1 ?  whiteKing : blackKing;
								if (!king.isInCheck) {
									System.out.println("Stalemate");
									System.out.println("draw");
									return;
								} 
								else {
									System.out.println("Checkmate");
									String winner = game.currMove==-1? "Black" : "White" ;
									System.out.println(winner+" wins");
									return;
								}
							}
							game.disarmShields();

							
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
