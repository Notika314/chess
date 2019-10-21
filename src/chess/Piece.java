package chess;

public abstract class Piece {

	char color;
	int xPos;
	int yPos;
	String code;
	char type;
	boolean hasValidMove;
	boolean validMoves[][];
	public Piece(char color, char type,int x, int y ){
		this.type = type;
		this.xPos = x;
		this.yPos = y;
		this.color = color;
		if (color=='w') code = "w";
		else if (color=='b') code="b";
		if (type=='p') code = code+"p";
		if (type=='R') code = code +"R";
		if (type=='N') code = code + "N";
		if (type=='B') code = code+"B";
		if (type=='Q') code += "Q";
		if (type=='K') code+="K";
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
	
	public void generateValidMoves() {
		// dependent on each individual piece type
	}
	
	public String toString() {
		return (code+" ");
	}
	
}
