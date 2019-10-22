package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	
	public Pawn(int color,int x, int y) {
		super(color,'p',x,y);
	}
	
	public void generateValidMoves(Piece board[][]) {
		if (hasMoved == false && board[this.xPos][this.yPos+this.color]==null && board[this.xPos][this.yPos+2*this.color] == null) {
			this.validMoves[this.xPos][this.yPos+2*this.color] = true;
			this.hasValidMove = true;
		}
		if (this.yPos + this.color < 8 && board[this.xPos][this.yPos+this.color] == null) {
			this.validMoves[this.xPos][this.yPos+this.color] = true;
			this.hasValidMove = true;
		}
		
		/* generate moves when pawn kills the enemy? */
	}
	
}
