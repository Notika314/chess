package chess;

public class Queen extends Piece {

	public Queen(char color, int x, int y) {
		super(color,'Q',x,y);
//		this.color = color;
//		this.xPos = x;
//		this.yPos = y;
	}
	
	public void generateValidMoves(Piece board[][]) {
		int i, j;
		// first generate the diagonals
		i = this.xPos + 1;
		j = this.yPos + 1;
		while (i < 8 && j < 8) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			i++;
			j++;
		}
		i = this.xPos + 1;
		j = this.yPos - 1;
		while (i < 8 && j >= 0) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			i++;
			j--;
		}
		i = this.xPos - 1;
		j = this.yPos + 1;
		while (i >= 0 && j < 8) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			i--;
			j++;
		}
		i = this.xPos - 1;
		j = this.yPos - 1;
		while (i >= 0 && j >= 0) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			i--;
			j--;
		}
		// second generate the horizontals
		i = this.xPos + 1;
		j = this.yPos;
		while (i < 8) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			i++;
		}
		i = this.xPos - 1;
		while (i >= 0) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			i--;
		}
		i = this.xPos;
		j = this.yPos + 1;
		while (j < 8) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			j++;
		}
		j = this.yPos - 1;
		while (j >= 0) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = true;
			this.hasValidMove = true;
			j--;
		}
	}
	
}
