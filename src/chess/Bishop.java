package chess;

public class Bishop extends Piece {

	public Bishop(char color, int x, int y) {
		super(color,'B',x,y);
//		this.color = color;
//		this.xPos = x;
//		this.yPos = y;
	}
	
	public void generateValidMoves(Piece board[][]) {
		int i, j;
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
	}
	
	
}
