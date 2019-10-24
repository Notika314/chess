package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	boolean passant;
	
	public Pawn(int color, int xPos, int yPos) {
		super(color,'p',xPos,yPos);
	}
		
	public boolean move(Piece board[][], int x, int y, int color) {
//		System.out.println("Moving piece "+ this.type+", "+this.color+ " to position " + x + " " + y);
		if (this.color != color) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if (this.validMoves[x][y] == 0) {
			return false;
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
			//System.out.println("xpos+1: "+(this.xPos+1)+", ypos+...: "+(this.yPos+(1*this.color)));
			this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = 1;
			this.hasValidMove = true;
		}
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& this.xPos-1 >= 0 && board[this.xPos-1][this.yPos+(1*this.color)] != null
				&& board[this.xPos-1][this.yPos+(1*this.color)].color != this.color) {
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