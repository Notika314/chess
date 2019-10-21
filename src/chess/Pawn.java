package chess;

public class Pawn extends Piece {

	boolean hasMoved;
	
	public Pawn(char color, int x, int y) {
		this.color = color;
		this.xPos = x;
		this.yPos = y;
	}
	
	public void generateValidMoves(Piece board[][]) {
		if (hasMoved == false && board[this.xPos][this.yPos+2] == null) {
			this.validMoves[this.xPos][this.yPos+2] = true;
			this.hasValidMove = true;
		}
		if (this.yPos + 1 < 8 && board[this.xPos][this.yPos+1] == null) {
			this.validMoves[this.xPos][this.yPos+1] = true;
			this.hasValidMove = true;
		}
	}
	
}
