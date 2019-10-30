package chess;

import java.util.Arrays;

public class King extends Piece {

	boolean isInCheck;
	boolean hasMoved;
	
	public King(int color, int x, int y) {
		super(color,'K',x,y);
		isInCheck = false;
	}
	
	
	
	public King copy() {
		King temp = new King(this.color, this.xPos, this.yPos);
		temp.validMoves = this.validMoves;
		temp.isInCheck = this.isInCheck;
		return temp;
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
		if (this.color == -1) {
			Piece.wKingIsInDanger = new int[] {-1, -1, -1, -1};
		}
		else {
			Piece.bKingIsInDanger = new int[] {-1, -1, -1, -1};
		}
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
	
	public boolean updateStatus(Piece[][] board) {
		int[] tempDanger = new int[4];
		if (this.color == -1) {
			tempDanger =  Arrays.copyOf(Piece.wKingIsInDanger, 4);;

		}
		else {
			tempDanger =  Arrays.copyOf(Piece.bKingIsInDanger, 4);;
		}
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[i][j]!=null && board[i][j].color!=this.color ) {
					Piece opponent = board[i][j].copy();
					if (opponent.type != 'K') {
						opponent.generateValidMoves(board);
					}

					if (this.color == -1) {
						Piece.wKingIsInDanger = Arrays.copyOf(tempDanger, 4);
					}
					else {
						Piece.bKingIsInDanger =  Arrays.copyOf(tempDanger, 4);;
					}
					/*
					for (int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (k==this.xPos && l==this.yPos && opponent.validMoves[k][l]>0) {
								this.isInCheck=true;
								board[i][j].validMoves = tempMoves;
								return false;
							}
						}
					}*/
					if (opponent.validMoves[this.xPos][this.yPos]>0) {
						//this.isInCheck=true;
						//board[i][j].validMoves = tempMoves;
						return false;
					}
					//board[i][j].validMoves = tempMoves;
				}
			}
		}
		return true;
	}
	
	public void generateValidMoves(Piece board[][]) {
		//System.out.println("generating valid moves for "+this.type+this.color);
		this.hasValidMove = false;
		this.validMoves = new int[8][8];
		boolean tempCheck = this.isInCheck;
		//System.out.println("Position is "+this.xPos+this.yPos+",checking "+this.xPos+ (this.yPos+this.color));
		if ((this.yPos+(1)<8 && this.yPos+(1)>=0 )&& (board[this.xPos][this.yPos+1]==null 
				|| board[this.xPos][this.yPos+1].color!=this.color)) {
			Piece temp = board[this.xPos][this.yPos+1];
			int i = this.xPos;
			int j = this.yPos;
			this.yPos = this.yPos+1;
			board[this.xPos][this.yPos] = this;
			board[i][j]=null;
			if (this.updateStatus(board))	{
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j]=this;
			this.xPos=i;
			this.yPos = j;
			board[this.xPos][this.yPos+1]=temp;
		}
		
		if (this.yPos-(1)<8 && this.yPos-(1)>=0 && (board[this.xPos][this.yPos-1]==null 
				|| board[this.xPos][this.yPos-1].color!=this.color)) {
			Piece temp = board[this.xPos][this.yPos - 1];
			int i = this.xPos;
			int j = this.yPos;

			this.yPos = this.yPos - 1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos][this.yPos - 1] = temp;
		}
		if (this.xPos+1<8 && this.xPos+1>=0 && (board[this.xPos+1][this.yPos]==null 
				|| board[this.xPos+1][this.yPos].color!=this.color)) {
			Piece temp = board[this.xPos + 1][this.yPos];
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = this.xPos + 1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos + 1][this.yPos] = temp;
		}
		
		
		if (this.xPos-1<8 && this.xPos-1>=0 && (board[this.xPos-1][this.yPos]==null 
				|| board[this.xPos-1][this.yPos].color!=this.color)) {
			//this.validMoves[this.xPos-1][this.yPos]=1;
			Piece temp = board[this.xPos - 1][this.yPos];
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = this.xPos - 1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos - 1][this.yPos] = temp;
		}
		
		if (this.yPos+(1)<8 && this.yPos+1>=0 && (this.xPos+1)<8 && (this.xPos-1)>=0 && (board[this.xPos+1][this.yPos+1]==null 
				|| board[this.xPos+1][this.yPos+1].color!=this.color)) {
			//this.validMoves[this.xPos+1][this.yPos+1]=1;
			Piece temp = board[this.xPos + 1][this.yPos+1];
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = this.xPos + 1;
			this.yPos = this.yPos+1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos + 1][this.yPos+1] = temp;
		}
		
		if (this.yPos+(1)<8 && this.yPos+1>=0 && (this.xPos-1)<8 && (this.xPos-1)>=0 && (board[this.xPos-1][this.yPos+1]==null 
				|| board[this.xPos-1][this.yPos+1].color!=this.color)) {
			//this.validMoves[this.xPos-1][this.yPos+1]=1;
			Piece temp = board[this.xPos - 1][this.yPos+1];
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = this.xPos - 1;
			this.yPos = this.yPos+1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos - 1][this.yPos+1] = temp;
		}
		
		
		if (this.yPos-(1)<8 && this.yPos-1>=0 && (this.xPos+1)<8 && (this.xPos+1)>=0 && (board[this.xPos+1][this.yPos-1]==null 
				|| board[this.xPos+1][this.yPos-1].color!=this.color)) {
			//this.validMoves[this.xPos+1][this.yPos-1]=1;
			Piece temp = board[this.xPos + 1][this.yPos-1];
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = this.xPos + 1;
			this.yPos = this.yPos-1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos + 1][this.yPos-1] = temp;
		}
		
		if ((this.yPos-1)<8 && (this.yPos-1)>=0 && (this.xPos-1)<8 && (this.xPos-1)>=0 && (board[this.xPos-1][this.yPos-1]==null 
				|| board[this.xPos-1][this.yPos-1].color!=this.color)) {
			//this.validMoves[this.xPos-1][this.yPos-1]=1;
			Piece temp = board[this.xPos - 1][this.yPos-1];
			int i = this.xPos;
			int j = this.yPos;
			this.xPos = this.xPos - 1;
			this.yPos = this.yPos-1;
			board[this.xPos][this.yPos] = this;
			board[i][j] = null;
			if (this.updateStatus(board)) {
				this.validMoves[this.xPos][this.yPos] = 1;
				this.hasValidMove = true;
			}
			board[i][j] = this;
			this.xPos = i;
			this.yPos = j;
			board[this.xPos - 1][this.yPos-1] = temp;
		}
		this.isInCheck = tempCheck;
		
		//adding validMoves for castling here:
		if (!this.isInCheck && !this.hasMoved) {
			if ( board[this.xPos+3][this.yPos]!=null && board[this.xPos+3][this.yPos].type=='R' &&
					!(board[this.xPos+3][this.yPos].hasMoved) ) {
				boolean clear = true;
				if (!(isSafeForSliding(this.xPos+1,this.yPos,board)) || !(isSafeForSliding(this.xPos+2,this.yPos,board))) {
					clear =false;
				}
				for (int i=1;i<3;i++) {
					if ( board[this.xPos+i][this.yPos]!=null ) {
						clear = false;
					} 
				}
				if (clear) this.validMoves[this.xPos+2][this.yPos] = 2;
			}
			if ( board[this.xPos-4][this.yPos]!=null && board[this.xPos-4][this.yPos].type=='R' &&
					!(board[this.xPos-4][this.yPos].hasMoved) ) {
				boolean clear = true;
				if (!(isSafeForSliding(this.xPos-1,this.yPos,board)) || !(isSafeForSliding(this.xPos-2,this.yPos,board))) {
					clear =false;
				}
				for (int i=1;i<4;i++) {
					if ( board[this.xPos-i][this.yPos]!=null) {
						clear = false;
					} 
				}
				if (clear) this.validMoves[this.xPos-2][this.yPos] = 2;
			}
		} 

//		checkValidMovesForSafety(board);		
	}
	
}
