package view;

import javax.swing.ImageIcon;
import model.*;

public final class Queen extends Piece {

	public Queen(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Queen.class.getResource("/queen-white.png") : Queen.class.getResource("/queen-black.png")), 
			color
		);
	}
	
	public Queen(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.QUEEN;
	}
}