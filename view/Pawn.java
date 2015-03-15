// UNIVR-ARFA 
package view;

import javax.swing.ImageIcon;
import model.*;

public final class Pawn extends Piece {

	public Pawn(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Pawn.class.getResource("/pawn-white.png") : Pawn.class.getResource("/pawn-black.png")), 
			color
		);
	}
	
	public Pawn(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.PAWN;
	}
}