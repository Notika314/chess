package chess;

public abstract class Piece {

	char color;
	int xPos;
	int yPos;
	boolean hasValidMove;
	boolean validMoves[][];
	
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
	
	public void generateValidMoves() {
		// dependent on each individual piece type
	}
	
}
