package chess;

public class Rook extends Piece {

	boolean hasMoved;
	
	public Rook(int color, int x, int y) {
		super(color,'R',x,y);
	}
	
	public void path(Piece[][] board, int deltaX, int deltaY) {
		int i = this.xPos + deltaX;
		int j = this.yPos + deltaY;
		while (i < 8 && i >= 0 && j < 8 && j >= 0) {
			if (board[i][j] != null) {
				if (board[i][j].color != this.color) {
					shield(board, i, j, deltaX, deltaY);
					this.validMoves[i][j] = 1;
					this.hasValidMove = true;
				}	
				return;
			}
			this.validMoves[i][j] = 1;
			this.hasValidMove = true;
			i += deltaX;
			j += deltaY;
		}
	}
	
	public void generateValidMoves(Piece board[][]) {
		this.hasValidMove = false;
		if (this.kingShield != null) {
			int deltaX = this.xPos - this.kingShield[0];
			int deltaY = this.yPos - this.kingShield[1];
			if (deltaY == 0) {
				path(board,1,0);
				path(board,-1,0);
				return;
			}
			if (deltaX == 0) {
				path(board,0,1);
				path(board,0,-1);
				return;
			}
			return;
		}
		path(board,1,0);
		path(board,-1,0);
		path(board,0,1);
		path(board,0,-1);
	}
	
}
