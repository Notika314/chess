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
		
		String line;
		scanner = new Scanner(System.in);
		
		Game game = new Game();
		game.currMove=1;
		while (true) {
			System.out.println("hihi");
			game.printBoard();
			game.updateValidMoves();
			line = scanner.nextLine();
			String[] tokens = game.tokenizeInput(line);
			if (tokens[0].contentEquals("resign")) {		//need to handle this - write the winner
				System.out.println("Game over. Someone won");
				return;
			} else if (tokens[0].contentEquals("draw")) {	//handle this case
				System.out.println("Game over. It's a draw.");
				return;
			} else {
				Piece piece = game.findPiece(tokens[0]);
				if (piece!=null) {
					int i = (int)(tokens[1].charAt(0)-97);
					int j = (int)(8-(tokens[1].charAt(1)-48));
					boolean moved =piece.move(game.board,i,j,piece.color);
					if (moved) game.currMove *=-1;
					else {
						System.out.println("Invalid move. Try again");
					}
				}
				else {
					System.out.println("There is no piece at this position.Try again");
					continue;
				}
			}
			
			System.out.println();	
		}	
	}

}
