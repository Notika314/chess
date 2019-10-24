package chess;

public class King extends Piece {

	boolean isInCheck;
	boolean hasMoved;
	
	public King(int color, int x, int y) {
		super(color,'K',x,y);
		isInCheck = false;
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
		if (this.validMoves[x][y] == 0) {
			return false;
		}
		return true;
	}
	
	public void updateStatus(Piece[][] board) {
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[i][j]!=null && board[i][j].color!=this.color) {
					Piece opponent = board[i][j];
					for (int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (k==this.xPos && l==this.yPos && opponent.validMoves[k][l]>0) {
								this.isInCheck=true;
							}
						}
					}
				}
			}
		}
	}
	public void generateValidMoves(Piece board[][]) {
		if (this.yPos+(this.color)<8 && this.yPos+(this.color)>=0 && (board[this.xPos][this.yPos+this.color]==null 
				|| board[this.xPos][this.yPos+this.color].color!=this.color)) {
			this.validMoves[this.xPos][this.yPos+this.color]=1;
		}
		if (this.yPos-(this.color)<8 && this.yPos-(this.color)>=0 && (board[this.xPos][this.yPos-this.color]==null 
				|| board[this.xPos][this.yPos-this.color].color!=this.color)) {
			this.validMoves[this.xPos][this.yPos-this.color]=1;
		}
		if (this.xPos+1<8 && this.xPos+1>=0 && (board[this.xPos+1][this.yPos]==null 
				|| board[this.xPos+1][this.yPos].color!=this.color)) {
			this.validMoves[this.xPos+1][this.yPos]=1;
		}
		if (this.xPos-1<8 && this.xPos-1>=0 && (board[this.xPos-1][this.yPos]==null 
				|| board[this.xPos-1][this.yPos].color!=this.color)) {
			this.validMoves[this.xPos-1][this.yPos]=1;
		}
		if (this.yPos+(this.color)<8 && this.yPos+this.color>=0 && (this.xPos+1)<8 && (this.xPos-1)>=0 && (board[this.xPos+1][this.yPos+this.color]==null 
				|| board[this.xPos+1][this.yPos+this.color].color!=this.color)) {
			this.validMoves[this.xPos+1][this.yPos+this.color]=1;
		}
		if (this.yPos+(this.color)<8 && this.yPos+this.color>=0 && (this.xPos-1)<8 && (this.xPos-1)>=0 && (board[this.xPos-1][this.yPos+this.color]==null 
				|| board[this.xPos-1][this.yPos+this.color].color!=this.color)) {
			this.validMoves[this.xPos-1][this.yPos+this.color]=1;
		}
		if (this.yPos-(this.color)<8 && this.yPos-this.color>=0 && (this.xPos+1)<8 && (this.xPos+1)>=0 && (board[this.xPos+1][this.yPos-this.color]==null 
				|| board[this.xPos+1][this.yPos-this.color].color!=this.color)) {
			this.validMoves[this.xPos+1][this.yPos-this.color]=1;
		}
		if (this.yPos-(this.color)<8 && this.yPos-this.color>=0 && (this.xPos-1)<8 && (this.xPos-1)>=0 && (board[this.xPos-1][this.yPos-this.color]==null 
				|| board[this.xPos-1][this.yPos-this.color].color!=this.color)) {
			this.validMoves[this.xPos-1][this.yPos-this.color]=1;
		}
		
	}
	
}
