package chess;

public class Knight extends Piece {

	public Knight(int color, int x, int y) {
		super(color,'N',x,y);
	}
	
	public Piece copy() {
		Knight temp = new Knight(this.color, this.xPos, this.yPos);
		temp.validMoves = this.validMoves;
		temp.hasValidMove = this.hasValidMove;
		return temp;
	}

	
	public void generateValidMoves(Piece board[][]) {
		this.hasValidMove = false;
		this.validMoves = new int[8][8];
		int i = this.xPos, j = this.yPos;
		int[] danger = danger(this.color);
		if (danger[0] != -1) {
			if (danger[2] != -1 || this.kingShield != null) {
				return;
			}
			if ((i+2 < 8 && i+2 >= 0 && j+1 < 8 && j+1 >= 0) && 
					(board[i+2][j+1] == null || board[i+2][j+1].color != this.color) &&
					((i+2 == danger[0] && j+1 == danger[1] || board[danger[0]][danger[1]].validMoves[i+2][j+1] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i+2][j+1] = 1;
			}
			if ((i+1 < 8 && i+1 >= 0 && j+2 < 8 && j+2 >= 0) && 
					(board[i+1][j+2] == null || board[i+1][j+2].color != this.color) &&
					((i+1 == danger[0] && j+2 == danger[1] || board[danger[0]][danger[1]].validMoves[i+1][j+2] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i+1][j+2] = 1;
			}
			if ((i-1 < 8 && i-1 >= 0 && j+2 < 8 && j+2 >= 0) && 
					(board[i-1][j+2] == null || board[i-1][j+2].color != this.color) &&
					((i-1 == danger[0] && j+2 == danger[1] || board[danger[0]][danger[1]].validMoves[i-1][j+2] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i-1][j+2] = 1;
			}
			if ((i-2 < 8 && i-2 >= 0 && j+1 < 8 && j+1 >= 0) && 
					(board[i-2][j+1] == null || board[i-2][j+1].color != this.color) &&
					((i-2 == danger[0] && j+1 == danger[1] || board[danger[0]][danger[1]].validMoves[i-2][j+1] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i-2][j+1] = 1;
			}
			
			if ((i-2 < 8 && i-2 >= 0 && j-1 < 8 && j-1 >= 0) && 
					(board[i-2][j-1] == null || board[i-2][j-1].color != this.color) &&
					((i-2 == danger[0] && j-1 == danger[1] || board[danger[0]][danger[1]].validMoves[i-2][j-1] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i-2][j-1] = 1;
			}
			if ((i-1 < 8 && i-1 >= 0 && j-2 < 8 && j-2 >= 0) && 
					(board[i-1][j-2] == null || board[i-1][j-2].color != this.color) &&
					((i-1 == danger[0] && j-2 == danger[1] || board[danger[0]][danger[1]].validMoves[i-1][j-2] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i-1][j-2] = 1;
			}
			if ((i+1 < 8 && i+1 >= 0 && j-2 < 8 && j-2 >= 0) && 
					(board[i+1][j-2] == null || board[i+1][j-2].color != this.color) &&
					((i+1 == danger[0] && j-2 == danger[1] || board[danger[0]][danger[1]].validMoves[i+1][j-2] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i+1][j-2] = 1;
			}
			if ((i+2 < 8 && i+2 >= 0 && j-1 < 8 && j-1 >= 0) && 
					(board[i+2][j-1] == null || board[i+2][j-1].color != this.color) &&
					((i+2 == danger[0] && j-1 == danger[1] || board[danger[0]][danger[1]].validMoves[i+2][j-1] == 2))) {
				this.hasValidMove = true;
				this.validMoves[i+2][j-1] = 1;
			}
			return;
		}
		if (this.kingShield != null) {
			return;
		}
		if ((i+2 < 8 && i+2 >= 0 && j+1 < 8 && j+1 >= 0) && 
				(board[i+2][j+1] == null || board[i+2][j+1].color != this.color)) {
			if (board[i+2][j+1] != null && board[i+2][j+1].type == 'K') {
				flag();
				((King)board[i+2][j+1]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i+2][j+1] = 1;
		}
		if ((i+1 < 8 && i+1 >= 0 && j+2 < 8 && j+2 >= 0) && 
				(board[i+1][j+2] == null || board[i+1][j+2].color != this.color)) {
			if (board[i+1][j+2] != null && board[i+1][j+2].type == 'K') {
				flag();
				((King)board[i+1][j+2]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i+1][j+2] = 1;
		}
		if ((i-1 < 8 && i-1 >= 0 && j+2 < 8 && j+2 >= 0) && 
				(board[i-1][j+2] == null || board[i-1][j+2].color != this.color)) {
			if (board[i-1][j+2] != null && board[i-1][j+2].type == 'K') {
				flag();
				((King)board[i-1][j+2]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i-1][j+2] = 1;
		}
		if ((i-2 < 8 && i-2 >= 0 && j+1 < 8 && j+1 >= 0) && 
				(board[i-2][j+1] == null || board[i-2][j+1].color != this.color)) {
			if (board[i-2][j+1] != null && board[i-2][j+1].type == 'K') {
				flag();
				((King)board[i-2][j+1]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i-2][j+1] = 1;
		}
		if ((i-2 < 8 && i-2 >= 0 && j-1 < 8 && j-1 >= 0) && 
				(board[i-2][j-1] == null || board[i-2][j-1].color != this.color)) {
			if (board[i-2][j-1] != null && board[i-2][j-1].type == 'K') {
				flag();
				((King)board[i-2][j-1]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i-2][j-1] = 1;
		}
		if ((i-1 < 8 && i-1 >= 0 && j-2 < 8 && j-2 >= 0) && 
				(board[i-1][j-2] == null || board[i-1][j-2].color != this.color)) {
			if (board[i-1][j-2] != null && board[i-1][j-2].type == 'K') {
				flag();
				((King)board[i-1][j-2]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i-1][j-2] = 1;
		}
		if ((i+1 < 8 && i+1 >= 0 && j-2 < 8 && j-2 >= 0) && 
				(board[i+1][j-2] == null || board[i+1][j-2].color != this.color)) {
			if (board[i+1][j-2] != null && board[i+1][j-2].type == 'K') {
				flag();
				((King)board[i+1][j-2]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i+1][j-2] = 1;
		}
		if ((i+2 < 8 && i+2 >= 0 && j-1 < 8 && j-1 >= 0) && 
				(board[i+2][j-1] == null || board[i+2][j-1].color != this.color)) {
			if (board[i+2][j-1] != null && board[i+2][j-1].type == 'K') {
				flag();
				((King)board[i+2][j-1]).isInCheck = true;
			}
			this.hasValidMove = true;
			this.validMoves[i+2][j-1] = 1;
		}
	}
	
}
