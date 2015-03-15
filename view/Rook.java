package view;

import javax.swing.ImageIcon;
import model.*;

public final class Rook extends Piece {

	public Rook(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Rook.class.getResource("/rook-white.png") : Rook.class.getResource("/rook-black.png")), 
			color
		);
	}
	
	public Rook(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.ROOK;
	}
}