package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	boolean passant;
	
	public Pawn(int color, int xPos, int yPos) {
		super(color,'p',xPos,yPos);
	}
	
	public Pawn copy() {
		Pawn temp = new Pawn(this.color, this.xPos, this.yPos);
		temp.validMoves = this.validMoves;
		temp.hasMoved = this.hasMoved;
		temp.passant = this.passant;
		return temp;
	}
		
	public boolean move(Piece board[][], int x, int y, int color) {
		if (this.color != color) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if (this.validMoves[x][y] == 0) {
			return false;
		}
		if (Math.abs(y-this.yPos) > 1) {
			this.passant = true;
		}
		if (this.validMoves[x][y] == 2) {
			board[x][y-this.color] = null;
		}
		int i = this.xPos;
		int j = this.yPos;
		this.xPos = x;
		this.yPos = y;
		board[x][y] = this;
		board[i][j] = null;
		this.hasMoved = true;
		return true;
	}
	
	public void generateValidMoves(Piece board[][]) {
		this.hasValidMove = false;
		this.validMoves = new int[8][8];
		int[] danger = danger(this.color);
		if (danger[0] != -1) {
			if (danger[2] != -1) {
				return;
			}
			if (hasMoved == false && board[this.xPos][this.yPos+1*this.color]==null 
					&& board[this.xPos][this.yPos+2*this.color] == null &&
					board[danger[0]][danger[1]].validMoves[this.xPos][this.yPos+(2*this.color)] == 2) {
				this.validMoves[this.xPos][this.yPos+(2*this.color)] = 1;
				this.hasValidMove = true;
			}

			if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
					&& board[this.xPos][this.yPos+1*this.color] == null && 
					board[danger[0]][danger[1]].validMoves[this.xPos][this.yPos+(2*this.color)] == 2) {
				this.validMoves[this.xPos][this.yPos+(1*this.color)] = 1;
				this.hasValidMove = true;
			}
			//This should handle attacks
			
			if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
					&& this.xPos+1 < 8 && board[this.xPos+1][this.yPos+(1*this.color)] != null
					&& board[this.xPos+1][this.yPos+(1*this.color)].color != this.color &&
					danger[0] == this.xPos+1 && danger[1] == this.yPos+(1*this.color)) {
				if (board[this.xPos+1][this.yPos+(1*this.color)].type == 'K') {
					flag();
					((King)board[this.xPos+1][this.yPos+(1*this.color)]).isInCheck = true;
				}
				this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = 1;
				this.hasValidMove = true;
			}
			if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
					&& this.xPos-1 >= 0 && board[this.xPos-1][this.yPos+(1*this.color)] != null
					&& board[this.xPos-1][this.yPos+(1*this.color)].color != this.color &&
					danger[0] == this.xPos-1 && danger[1] == this.yPos+(1*this.color)) {
				if (board[this.xPos-1][this.yPos+(1*this.color)].type == 'K') {
					flag();
					((King)board[this.xPos-1][this.yPos+(1*this.color)]).isInCheck = true;
				}
				this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = 1;
				this.hasValidMove = true;
			}
			//I think this will handle en passant valid moves
			if (this.xPos+1 < 8 && board[this.xPos+1][this.yPos]!=null && board[this.xPos+1][this.yPos].type=='p'
					&& ((Pawn)board[this.xPos+1][this.yPos]).passant == true && board[this.xPos+1][this.yPos+(1*this.color)] == null &&
					board[danger[0]][danger[1]].validMoves[this.xPos+1][this.yPos+(1*this.color)] == 2) {
				this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = 2;
				this.hasValidMove = true;
			}
			if (this.xPos-1 >= 0 && board[this.xPos-1][this.yPos]!=null && board[this.xPos-1][this.yPos].type=='p'
					&& ((Pawn)board[this.xPos-1][this.yPos]).passant == true && board[this.xPos-1][this.yPos+(1*this.color)] == null &&
					board[danger[0]][danger[1]].validMoves[this.xPos-1][this.yPos+(1*this.color)] == 2) {
				this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = 2;
				this.hasValidMove = true;
			}
			return;
		}
		if (this.kingShield != null) {
			if (this.xPos+1 == this.kingShield[0] && this.yPos-(1*this.color) == this.kingShield[1]) {
				this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = 1;
				this.hasValidMove = true;
				return;
			}
			if (this.xPos-1 == this.kingShield[0] && this.yPos-(1*this.color) == this.kingShield[1]) {
				this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = 1;
				this.hasValidMove = true;
				return;
			}
			if (this.xPos != this.kingShield[0]) {
				return;
			}
			if (hasMoved == false && board[this.xPos][this.yPos+1*this.color]==null 
					&& board[this.xPos][this.yPos+2*this.color] == null) {
				this.validMoves[this.xPos][this.yPos+(2*this.color)] = 1;
				this.hasValidMove = true;
			}
			if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
					&& board[this.xPos][this.yPos+1*this.color] == null) {
				this.validMoves[this.xPos][this.yPos+(1*this.color)] = 1;
				this.hasValidMove = true;
			}
			return;
		}
		if (hasMoved == false && board[this.xPos][this.yPos+1*this.color]==null 
				&& board[this.xPos][this.yPos+2*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+(2*this.color)] = 1;
			this.hasValidMove = true;
		}
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& board[this.xPos][this.yPos+1*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+(1*this.color)] = 1;
			this.hasValidMove = true;
		}
		//This should handle attacks
		
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& this.xPos+1 < 8 && board[this.xPos+1][this.yPos+(1*this.color)] != null
				&& board[this.xPos+1][this.yPos+(1*this.color)].color != this.color) {
			if (board[this.xPos+1][this.yPos+(1*this.color)].type == 'K') {
				flag();
				((King)board[this.xPos+1][this.yPos+(1*this.color)]).isInCheck = true;
			}
			this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = 1;
			this.hasValidMove = true;
		}
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& this.xPos-1 >= 0 && board[this.xPos-1][this.yPos+(1*this.color)] != null
				&& board[this.xPos-1][this.yPos+(1*this.color)].color != this.color) {
			if (board[this.xPos-1][this.yPos+(1*this.color)].type == 'K') {
				flag();
				((King)board[this.xPos-1][this.yPos+(1*this.color)]).isInCheck = true;
			}
			this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = 1;
			this.hasValidMove = true;
		}
		//I think this will handle en passant valid moves
		if (this.xPos+1 < 8 && board[this.xPos+1][this.yPos]!=null && board[this.xPos+1][this.yPos].type=='p'
				&& ((Pawn)board[this.xPos+1][this.yPos]).passant == true && board[this.xPos+1][this.yPos+(1*this.color)] == null) {
			this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = 2;
			this.hasValidMove = true;
		}
		if (this.xPos-1 >= 0 && board[this.xPos-1][this.yPos]!=null && board[this.xPos-1][this.yPos].type=='p'
				&& ((Pawn)board[this.xPos-1][this.yPos]).passant == true && board[this.xPos-1][this.yPos+(1*this.color)] == null) {
			this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = 2;
			this.hasValidMove = true;
		}
	}
	
}