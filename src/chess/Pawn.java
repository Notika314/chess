//package chess;
//
//public class Pawn extends Piece {
//
//	boolean hasMoved;
//	
//	public Pawn(int color,int x, int y) {
//		super(color,'P',x,y);
//	}
//	
//	public void generateValidMoves(Piece board[][]) {
//		if (hasMoved == false && board[this.xPos][this.yPos+this.color]==null && board[this.xPos][this.yPos+2*this.color] == null) {
//			this.validMoves[this.xPos][this.yPos+2*this.color] = true;
//			this.hasValidMove = true;
//		}
//		if (this.yPos + this.color < 8 && board[this.xPos][this.yPos+this.color] == null) {
//			this.validMoves[this.xPos][this.yPos+this.color] = true;
//			this.hasValidMove = true;
//		}
//		
//		/* generate moves when pawn kills the enemy? */
//	}
//	
//}
//
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
		
		// Question - do we check here if the piece on the position (x+1,y+1) is the enemy? 
		// Because if it's not, the move shouldn't be valid
		// Or do we make this check in Controller, before calling #move method on Pawn?
		
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
		if (this.xPos+1 < 8 && board[this.xPos+1][this.yPos].type=='P'
				&& ((Pawn)board[this.xPos+1][this.yPos]).passant == true) {
			this.validMoves[this.xPos+1][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
		if (this.xPos-1 >= 0 && board[this.xPos-1][this.yPos].type=='P'
				&& ((Pawn)board[this.xPos-1][this.yPos]).passant == true) {
			this.validMoves[this.xPos-1][this.yPos+(1*this.color)] = true;
			this.hasValidMove = true;
		}
	}
	
}