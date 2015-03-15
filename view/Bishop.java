// UNIVR-ARFA 
package view;

import javax.swing.ImageIcon;
import model.*;

public final class Bishop extends Piece {
	
	public Bishop(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Bishop.class.getResource("/bishop-white.png") : Bishop.class.getResource("/bishop-black.png")), 
			color
		);
	}

	public Bishop(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.BISHOP;
	}
}