package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * Rook (class)
 * 
 * @author Univr ARFA
 */
public final class Rook extends Piece {

	/**
	 * Rook constructor
	 * 
	 * @param instance color
	 */
	public Rook(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Rook.class.getResource("/rook-white.png") : Rook.class.getResource("/rook-black.png")), 
			color
		);
	}
	
	/**
	 * Rook instance copy constructor
	 * 
	 * @param piece old reference to do the copy with 
	 */
	public Rook(Piece piece) {
		super(piece);
	}
	
	@Override
	public PieceType getPieceType() {
		return PieceType.ROOK;
	}
}