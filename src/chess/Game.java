package chess;

public class Game {
	private Piece[][] board;
	boolean isDone;
	int winner; /* 0 for no winner, 1 - for white ,2 -for black */
	int currMove; /*flag to indicate whose turn to move */
	public Game() {
		board = new Piece[8][8];
		isDone = false;
		currMove = 1;
		board[0][0] = new Rook(-1,0,0);
		board[0][7] = new Rook(-1,0,7);
		board[0][1] = new Knight(-1,0,1);
		board[0][6] = new Knight(-1,0,6);
		board[0][2] = new Bishop(-1,0,2);
		board[0][5] = new Bishop(-1,0,5);
		board[0][3] = new Queen(-1,0,3);
		board[0][4] = new King(-1,0,4);
		board[7][0] = new Rook(1,7,0);
		board[7][7] = new Rook(1,7,7);
		board[7][1] = new Knight(1,7,1);
		board[7][6] = new Knight(1,7,6);
		board[7][2] = new Bishop(1,7,2);
		board[7][5] = new Bishop(1,7,5);
		board[7][3] = new Queen(1,7,3);
		board[7][4] = new King(1,7,4);
		for (int i=0;i<8;i++) {
			board[1][i] = new Pawn(-1,1,i);
			board[6][i] = new Pawn(1,6,i);
		}
	}
	public void printBoard() {
		System.out.println();
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[i][j]==null) {
					if (i%2==j%2) System.out.print("   ");
					else System.out.print("## ");
				} else {
					System.out.print(board[i][j]); 
				}
			}
			System.out.println(8-i);
		}
		System.out.println(" a  b  c  d  e  f  g  h  ");
	}
}
