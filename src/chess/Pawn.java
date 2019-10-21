package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	
	public Pawn(char color,int x, int y) {
		super(color,'p',x,y);
//		this.color = color;
//		this.xPos = x;
//		this.yPos = y;
	}
	
	public void generateValidMoves(Piece board[][]) {
		
		/* should we check that both squares in front are empty, or just second?*/
		
		if (hasMoved == false && board[this.xPos][this.yPos+2] == null) {
			this.validMoves[this.xPos][this.yPos+2] = true;
			this.hasValidMove = true;
		}
		if (this.yPos + 1 < 8 && board[this.xPos][this.yPos+1] == null) {
			this.validMoves[this.xPos][this.yPos+1] = true;
			this.hasValidMove = true;
		}
		
		/* generate moves when pawn kills the enemy? */
	}
	
}
