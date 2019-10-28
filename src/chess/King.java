package chess;

public class King extends Piece {

	boolean isInCheck;
	boolean hasMoved;
	
	public King(int color, int x, int y) {
		super(color,'K',x,y);
		isInCheck = false;
	}
	
	public boolean move(Piece board[][], int x, int y, int color) {
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
//		for (int i=0;i<8;i++) {
//			for (int j=0;j<8;j++) {
//				if (board[i][j]!=null && board[i][j].color!=this.color) {
//					Piece opponent = board[i][j];
//					for (int k=0;k<8;k++) {
//						for (int l=0;l<8;l++) {
//							if (k==x && l==y && opponent.validMoves[k][l]>0) {
//								return false;
//							}
//						}
//					}
//				}
//			}
//		}
		if (this.validMoves[x][y] == 1) {
			this.hasMoved = true;
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = x;
			this.yPos = y;
			board[x][y] = this;
			board[i][j] = null;
		} else if (this.validMoves[x][y]==2) {	//if trying to castle 
			if (x==6) {
				Rook theRook = (Rook)board[7][y];
				int lastX = this.xPos;
				int lastY = this.yPos;
				theRook.xPos = 5;
				theRook.yPos = y;
				theRook.hasMoved = true;
				board[7][y]=null;
				this.xPos = x;
				this.yPos = y;
				this.hasMoved = true;
				board[x][y]=this;
				board[5][y]=theRook;
				board[lastX][lastY]=null;
			} else if (x==2) {
				Rook theRook = (Rook)board[0][y];
				theRook.xPos = 3;
				theRook.yPos = y;
				theRook.hasMoved = true;
				board[0][y]=null;
				int lastX = this.xPos;
				int lastY = this.yPos;
				this.xPos = x;
				this.yPos = y;
				this.hasMoved = true;
				board[x][y]=this;
				board[3][y]=theRook;
				board[lastX][lastY]=null;
			}
		}
		return true;
	}
	
	private boolean isSafeForSliding(int x, int y,Piece[][] board) {
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[i][j]!=null && board[i][j].color!=this.color) {
					Piece opponent = board[i][j];
					for (int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (k==x && l==y && opponent.validMoves[k][l]>0) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	private void checkValidMovesForSafety(Piece[][] board) {
		for (int i=0;i<0;i++) {
			for (int j=0;j<0;j++) {
				if (board[i][j]!=null && board[i][j].color!=this.color) {
					Piece opponent = board[i][j];
					for (int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (opponent.validMoves[k][l]>0) {
								this.validMoves[k][l]=0;
							}
						}
					}
				}
			}
		}
	}
	
	public void updateStatus(Piece[][] board) {
		this.isInCheck=false;
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[i][j]!=null && board[i][j].color!=this.color) {
					Piece opponent = board[i][j];
					opponent.generateValidMoves(board);
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
		
		//adding validMoves for castling here:
		if (!this.isInCheck && !this.hasMoved) {
			if ( board[this.xPos+3][this.yPos]!=null && board[this.xPos+3][this.yPos].type=='R' &&
					!(board[this.xPos+3][this.yPos].hasMoved) ) {
				boolean clear = true;
				for (int i=1;i<3;i++) {
					if ( board[this.xPos+i][this.yPos]!=null || !(isSafeForSliding(this.xPos+i,this.yPos,board)) ) {
						clear = false;
					} 
				}
				if (clear) this.validMoves[this.xPos+2][this.yPos] = 2;
			}
			if ( board[this.xPos-4][this.yPos]!=null && board[this.xPos-4][this.yPos].type=='R' &&
					!(board[this.xPos-4][this.yPos].hasMoved) ) {
				boolean clear = true;
				for (int i=1;i<4;i++) {
					if ( board[this.xPos-i][this.yPos]!=null || !(isSafeForSliding(this.xPos-i,this.yPos,board)) ) {
						clear = false;
					} 
				}
				if (clear) this.validMoves[this.xPos-2][this.yPos] = 2;
			}
		} 
		
		checkValidMovesForSafety(board);		
	}
	
}
