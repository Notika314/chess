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
		game.printBoard();
		line = scanner.nextLine();
		System.out.println(line);
		System.out.println();
		
		
	}

}
