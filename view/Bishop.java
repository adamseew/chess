package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * Bishop (class)
 *
 * @author Univr ARFA
 */
public final class Bishop extends Piece {

	/**
	 * Bishop constructor
	 *
	 * @param instance color
	 */
	public Bishop(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Bishop.class.getResource("/bishop-white.png") : Bishop.class.getResource("/bishop-black.png")),
			color
		);
	}

	/**
	 * Bishop instance copy constructor
	 *
	 * @param piece old reference to do the copy with
	 */
	public Bishop(Piece piece) {
		super(piece);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.BISHOP;
	}
}