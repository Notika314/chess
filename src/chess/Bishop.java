package chess;

public class Bishop extends Piece {

	public Bishop(int color, int x, int y) {
		super(color,'B',x,y);
	}
	
	public void generateValidMoves(Piece board[][]) {
		int i, j;
		i = this.xPos + 1;
		j = this.yPos + 1;
		while (i < 8 && j < 8) {
			if (board[i][j] != null) {
				break;
			}
			System.out.println("i: "+i+", j:"+j);
			this.validMoves[i][j] = 1;
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
			this.validMoves[i][j] = 1;
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
			this.validMoves[i][j] = 1;
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
			this.validMoves[i][j] = 1;
			this.hasValidMove = true;
			i--;
			j--;
		}
	}
	
	
}
