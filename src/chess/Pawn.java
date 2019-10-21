package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	boolean passant;
	
	public Pawn(int color, int x, int y) {
		super(color,'P',x,y);
	}
	
	public void generateValidMoves(Piece board[][]) {
		this.hasValidMove = false;
		if (hasMoved == false && board[this.xPos][this.yPos+1*this.color]==null 
				&& board[this.xPos][this.yPos+2*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+(2*this.color)] = true;
			this.hasValidMove = true;
		}
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& board[this.xPos][this.yPos+1*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
		//This should handle attacks
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& this.xPos+1 < 8 && board[this.xPos+1][this.yPos+(1*this.color)] != null) {
			this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
		if ((this.yPos+(1*this.color) < 8 && this.yPos+(1*this.color) >= 0) 
				&& this.xPos-1 >= 0 && board[this.xPos-1][this.yPos+(1*this.color)] != null) {
			this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
		//I think this will handle en passant valid moves
		if (this.xPos+1 < 8 && board[this.xPos+1][this.yPos].code.equals("P")
				&& ((Pawn)board[this.xPos+1][this.yPos]).passant == true) {
			this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
		if (this.xPos-1 >= 0 && board[this.xPos-1][this.yPos].code.equals("P")
				&& ((Pawn)board[this.xPos-1][this.yPos]).passant == true) {
			this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
	}
	
}
