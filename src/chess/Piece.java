package chess;

public abstract class Piece {
	int color;
	int xPos;
	int yPos;
	char type;
	boolean hasValidMove;
	int validMoves[][];
	boolean hasMoved;
	
	public Piece(int color, char type, int xPos, int yPos ){
		this.color = color;
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.hasMoved = false;
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
//		if (this.type=='K') {
//			for (int i=0;i<8;i++) {
//				for (int j=0;j<8;j++) {
//					if (board[i][j]!=null && board[i][j].color!=this.color) {
//						Piece opponent = board[i][j];
//						for (int k=0;k<8;k++) {
//							for (int l=0;l<8;l++) {
//								if (k==x && l==y && opponent.validMoves[k][l]>0) {
//									return false;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		this.hasMoved = true;
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
//		if (color == -1) {
			return "b" + this.type;
		}
		return "w" + this.type;
	}
	
}
