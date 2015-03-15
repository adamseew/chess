// UNIVR-ARFA 
package view;

import javax.swing.ImageIcon;
import model.*;

public final class King extends Piece {

	public King(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? King.class.getResource("/king-white.png") : King.class.getResource("/king-black.png")), 
			color
		);
	}
	
	public King(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.KING;
	}
}