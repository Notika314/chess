package chess;

public class Rook extends Piece {

	boolean hasMoved;
	
	public Rook(int color, int x, int y) {
		super(color,'R',x,y);
	}
	
	public void generateValidMoves(Piece board[][]) {
		int i, j;
		i = this.xPos + 1;
		j = this.yPos;
		while (i < 8) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = 1;
			this.hasValidMove = true;
			i++;
		}
		i = this.xPos - 1;
		while (i >= 0) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = 1;
			this.hasValidMove = true;
			i--;
		}
		i = this.xPos;
		j = this.yPos + 1;
		while (j < 8) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = 1;
			this.hasValidMove = true;
			j++;
		}
		j = this.yPos - 1;
		while (j >= 0) {
			if (board[i][j] != null) {
				break;
			}
			this.validMoves[i][j] = 1;
			this.hasValidMove = true;
			j--;
		}
	}
	
}
