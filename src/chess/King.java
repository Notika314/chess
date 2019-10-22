package chess;

public class King extends Piece {

	boolean isInCheck;
	boolean hasMoved;
	
	public King(int color, int x, int y) {
		super(color,'K',x,y);
	}
	
	public boolean move(Piece board[][], int x, int y, char color) {
		if (this.color != color) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if (board[x][y] != null) {
			return false;
		}
		if (!this.validMoves[x][y]) {
			return false;
		}
		return true;
	}
	
	public void generateValidMoves(Piece board[][]) {
		
		
	}
	
}
