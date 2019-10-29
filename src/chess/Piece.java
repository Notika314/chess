package chess;

public abstract class Piece {
	int color;
	int xPos;
	int yPos;
	char type;
	boolean hasValidMove;
	int validMoves[][];
	boolean hasMoved;
	int kingShield[];
	static int wKingPos[];
	static int bKingPos[];
	static int wKingIsInDanger[] = {-1, -1, -1, -1};
	static int bKingIsInDanger[] = {-1, -1, -1, -1};
	
	public Piece(int color, char type, int xPos, int yPos ){
		this.color = color;
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.hasMoved = false;
		this.validMoves = new int[8][8];
	}

	public boolean move(Piece board[][], int x, int y, int color) {
		if (this.color != color) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if (this.validMoves[x][y] == 0) {
			return false;
		}
		if (this.color == -1) {
			Piece.wKingIsInDanger = new int[] {-1, -1, -1, -1};
		}
		else {
			Piece.bKingIsInDanger = new int[] {-1, -1, -1, -1};
		}
		this.hasMoved = true;
		int i = this.xPos;
		int j = this.yPos;
		this.xPos = x;
		this.yPos = y;
		board[x][y] = this;
		board[i][j] = null;
		return true;
	}
	
	public int[] danger(int color) {
		if (color == -1) {
			return Piece.wKingIsInDanger;
		}
		else {
			return Piece.bKingIsInDanger;
		}
	}
	
	public void generateValidMoves(Piece[][] board) {
		// dependent on each individual piece type
	}
	
	public void flag() {
		if (this.color == 1) {
			if (Piece.wKingIsInDanger[0] == -1) {
				Piece.wKingIsInDanger[0] = this.xPos;
				Piece.wKingIsInDanger[1] = this.yPos;
			}
			else {
				Piece.wKingIsInDanger[2] = this.xPos;
				Piece.wKingIsInDanger[3] = this.yPos;
			}
		}
		else {
			if (Piece.bKingIsInDanger[0] == -1) {
				Piece.bKingIsInDanger[0] = this.xPos;
				Piece.bKingIsInDanger[1] = this.yPos;
			}
			else {
				Piece.bKingIsInDanger[2] = this.xPos;
				Piece.bKingIsInDanger[3] = this.yPos;
			}
		}
	}
		
	public void shield(Piece[][] board, int x, int y, int deltaX, int deltaY) {
		int i = x, j = y;
		i += deltaX;
		j += deltaY;
		while (i < 8 && i >= 0 && j < 8 && j >= 0) {
			if (board[i][j] != null) {
				if (board[i][j].color == board[x][y].color && board[i][j].type == 'K') {
					board[x][y].kingShield = new int[] {i,j};
				}
				else {
					return;
				}
			}
			i += deltaX;
			j += deltaY;
		}
	}
	
	public String toString() {
		if (color == 1) { 
			return "b" + this.type;
		}
		return "w" + this.type;
	}
	
}
