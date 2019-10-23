/**
 * @author Natalia Bryzhatenko nb631
 * @author Christopher Taglieri cat197
 */
package chess;
import java.util.Scanner;


public class Chess {

	private static Scanner scanner;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String currColor ;
		String line;
		scanner = new Scanner(System.in);
		
		Game game = new Game();
		while (true) {
			game.printBoard();
			game.updateValidMoves();
			currColor = game.currMove==1? "White" : "Black";
			System.out.println(currColor+"'s move: ");
			line = scanner.nextLine();
			String[] tokens = game.tokenizeInput(line);
			if (tokens[0].contentEquals("resign")) {		//need to handle this - write the winner
				System.out.println("Game over. Someone won");
				return;
			} else if (tokens[0].contentEquals("draw")) {	//handle this case
				System.out.println("Game over. It's a draw.");
				return;
			} else {
				if (game.findPiece(tokens[0])==null) {
					System.out.println("Invalid move- no piece at position "+ tokens[0]);
					continue;
				} else {
					Piece piece = game.findPiece(tokens[0]);
					if (piece.color!=game.currMove) {
						System.out.println("It's "+currColor+"'s turn to make a move. Try again");
						continue;
					}
//					for (int i=0;i<piece.validMoves.length;i++) {
//						for (int j=0;j<piece.validMoves.length;j++) {
//							System.out.print(piece.validMoves[j][i]+" ");
//						}
//						System.out.println();
//					}
					int i = (int)(tokens[1].charAt(0)-97);
					int j = (int)(8-(tokens[1].charAt(1)-48));
					boolean moved =piece.move(game.board,i,j,piece.color);
					if (moved) game.currMove *=-1;
					else {
						System.out.println("Invalid move. Try again");
					}
				}

			}
//			System.out.println();	
		}	
	}

}
