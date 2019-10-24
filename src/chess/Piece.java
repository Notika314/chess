package chess;

public abstract class Piece {
	int color;
	int xPos;
	int yPos;
	char type;
	boolean hasValidMove;
	int validMoves[][];
	
	public Piece(int color, char type, int xPos, int yPos ){
		this.color = color;
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.validMoves = new int[8][8];
	}

	public boolean move(Piece board[][], int x, int y, int color) {
//		System.out.println("Moving piece "+ this.type+", "+this.color+ " to position " + x + " " + y);
		if (this.color != color) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if (this.validMoves[x][y] == 0) {
			return false;
		}
		int i = this.xPos;
		int j = this.yPos;
		this.xPos = x;
		this.yPos = y;
		board[x][y] = this;
		board[i][j] = null;
		return true;
	}
	
	public void generateValidMoves(Piece[][] board) {
		// dependent on each individual piece type
	}
	
	public String toString() {
		if (color == 1) {
			return "b" + this.type;
		}
		return "w" + this.type;
	}
	
}
