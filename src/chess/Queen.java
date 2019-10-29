package chess;

public class Queen extends Piece {

	public Queen(int color, int x, int y) {
		super(color,'Q',x,y);
	}
	
	public void path(Piece[][] board, int deltaX, int deltaY) {
		int i = this.xPos + deltaX;
		int j = this.yPos + deltaY;
		while (i < 8 && i >= 0 && j < 8 && j >= 0) {
			if (board[i][j] != null) {
				if (board[i][j].color != this.color) {
					shield(board, i, j, deltaX, deltaY);
					this.validMoves[i][j] += 1;
					this.hasValidMove = true;
					if (board[i][j].type == 'K' && this.validMoves[i][j] == 1) {
						flag();
						((King)board[i][j]).isInCheck = true;
						path(board, deltaX, deltaY);
					}
				}	
				return;
			}
			this.validMoves[i][j] += 1;
			this.hasValidMove = true;
			i += deltaX;
			j += deltaY;
		}
	}
	
	public void pathUnderCheck(Piece[][] board, int deltaX, int deltaY) {
		int i = this.xPos + deltaX;
		int j = this.yPos + deltaY;
		while (i < 8 && i >= 0 && j < 8 && j >= 0) {
			if (board[i][j] == null ) {
				if (this.color == -1) {
					if (board[Piece.wKingIsInDanger[0]][Piece.wKingIsInDanger[1]].validMoves[i][j] == 2) {
						this.validMoves[i][j] = 1;
						this.hasValidMove = true;
					}
				}
				else {
					if (board[Piece.bKingIsInDanger[0]][Piece.bKingIsInDanger[1]].validMoves[i][j] == 2) {
						this.validMoves[i][j] = 1;
						this.hasValidMove = true;
					}
				}
			}
			else {
				if (this.color == -1) {
					if (i == Piece.wKingIsInDanger[0] && j == Piece.wKingIsInDanger[1]) {
						this.validMoves[i][j] = 1;
						this.hasValidMove = true;
					}
				}
				else {
					if (i == Piece.bKingIsInDanger[0] && j == Piece.bKingIsInDanger[1]) {
						this.validMoves[i][j] = 1;
						this.hasValidMove = true;
					}
				}
				return;
			}
			i += deltaX;
			j += deltaY;
		}
	}
	
	public void generateValidMoves(Piece board[][]) {
		this.hasValidMove = false;
		this.validMoves = new int[8][8];
		int[] danger = danger(this.color);
		if (danger[0] != -1) {
			if (danger[2] != -1) {
				return;
			}
			pathUnderCheck(board,1,1);
			pathUnderCheck(board,1,-1);
			pathUnderCheck(board,-1,1);
			pathUnderCheck(board,-1,-1);
			pathUnderCheck(board,1,0);
			pathUnderCheck(board,-1,0);
			pathUnderCheck(board,0,1);
			pathUnderCheck(board,0,-1);
			return;
		}
		if (this.kingShield != null) {
			int deltaX = this.xPos - this.kingShield[0];
			int deltaY = this.yPos - this.kingShield[1];
			if (deltaX != 0 && deltaY != 0) {
				int slope = deltaY/deltaX;
				if (slope > 0) {
					path(board,1,1);
					path(board,-1,-1);
				}
				else {
					path(board,1,-1);
					path(board,-1,1);
				}
				return;
			}
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
		path(board,1,1);
		path(board,1,-1);
		path(board,-1,1);
		path(board,-1,-1);
		path(board,1,0);
		path(board,-1,0);
		path(board,0,1);
		path(board,0,-1);
	}
	
}
