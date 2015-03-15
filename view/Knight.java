// UNIVR-ARFA 
package view;

import javax.swing.ImageIcon;
import model.*;

public final class Knight extends Piece {

	public Knight(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Knight.class.getResource("/knight-white.png") : Knight.class.getResource("/knight-black.png")), 
			color
		);
	}

	public Knight(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.KNIGHT;
	}
}