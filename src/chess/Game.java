package chess;

public class Game {
	
	
	public Piece[][] board;
	boolean isDone;
	int winner; /* 0 for no winner, -1 - for white ,1 -for black */
	int currMove; /*flag to indicate whose turn to move -1 for white, 1 for black*/
	public Game() {
		winner = 0;
		isDone = false;
		currMove = -1;
		board = new Piece[8][8];
		board[0][0] = new Rook(1,0,0);
		board[1][0] = new Knight(1,1,0);
		board[2][0] = new Bishop(1,2,0);
		board[3][0] = new Queen(1,3,0);
		board[4][0] = new King(1,4,0);
		board[5][0] = new Bishop(1,5,0);
		board[6][0] = new Knight(1,6,0);
		board[7][0] = new Rook(1,7,0);
		board[0][7] = new Rook(-1,0,7);
		board[1][7] = new Knight(-1,1,7);
		board[2][7] = new Bishop(-1,2,7);
		board[3][7] = new Queen(-1,3,7);
		board[4][7] = new King(-1,4,7);
		board[5][7] = new Bishop(-1,5,7);
		board[6][7] = new Knight(-1,6,7);
		board[7][7] = new Rook(-1,7,7);

		for (int i=0;i<8;i++) {
			board[i][1] = new Pawn(1,i,1);
			board[i][6] = new Pawn(-1,i,6);
		}
		return;
	}
	public String[] tokenizeInput(String input) {
		String[] tokens = input.split("\\s+");
		return tokens;
	}
	public void updateValidMoves() {
		int i,j;
		for (i=0;i<8;i++) {
			for (j=0;j<8;j++) {
				if (board[j][i]!=null) {
					board[j][i].validMoves = new int[8][8];
					board[j][i].generateValidMoves(board);
				}
			}
		}
		return;
	}
	
	public Piece findPiece(String token) {
		Piece piece; 
		int i = (int)(token.charAt(0)-97);
		int j = (int)(8-(token.charAt(1)-48));
//		System.out.println("i is "+ i+ ", j is "+j);
		if (i>7 || j>7) {
			return null;
		}
		piece = board[i][j];
		return piece;
	}
	public void printBoard() {
		System.out.println();
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[j][i]==null) {
					if (i%2==j%2) System.out.print("   ");
					else System.out.print("## ");
				} else {
					System.out.print(board[j][i]+" "); 
				}
			}
			System.out.println(8-i);
		}
		System.out.println(" a  b  c  d  e  f  g  h  ");
		System.out.println();
	}
}
