package chess;

public class Knight extends Piece {

	public Knight(int color, int x, int y) {
		super(color,'N',x,y);
	}
	
	public void generateValidMoves(Piece board[][]) {
		this.hasValidMove = false;
		int i = this.xPos, j = this.yPos;
		if (i+2 < 8 && i+2 >= 0 && j+1 < 8 && j+1 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i+2][j+1] = 1;
		}
		if (i+1 < 8 && i+1 >= 0 && j+2 < 8 && j+2 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i+1][j+2] = 1;
		}
		if (i-1 < 8 && i-1 >= 0 && j+2 < 8 && j+2 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i-1][j+2] = 1;
		}
		if (i-2 < 8 && i-2 >= 0 && j+1 < 8 && j+1 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i-2][j+1] = 1;
		}
		if (i-2 < 8 && i-2 >= 0 && j-1 < 8 && j-1 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i-2][j-1] = 1;
		}
		if (i-1 < 8 && i-1 >= 0 && j-2 < 8 && j-2 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i-1][j-2] = 1;
		}
		if (i+1 < 8 && i+1 >= 0 && j-2 < 8 && j-2 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i+1][j-2] = 1;
		}
		if (i+2 < 8 && i+2 >= 0 && j-1 < 8 && j-1 >= 0) {
			this.hasValidMove = true;
			this.validMoves[i+2][j-1] = 1;
		}
		
		
		
	}
	
}
