package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	
	public Pawn(int color,int x, int y) {
		super(color,'p',x,y);
	}
	
	public void generateValidMoves(Piece board[][]) {
//		int nextStep=1;
//		int nextNextStep = 2;
//		if (this.color=='w') {
//			nextStep=-1;
//			nextNextStep=-2;
//		}
		if (hasMoved == false && board[this.xPos][this.yPos+1*this.color]==null && board[this.xPos][this.yPos+2*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+2*this.color] = true;
			this.hasValidMove = true;
		}
		if (this.yPos + 1*this.color < 8 && board[this.xPos][this.yPos+1*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+1*this.color] = true;
			this.hasValidMove = true;
		}
		
		/* generate moves when pawn kills the enemy? */
	}
	
}
