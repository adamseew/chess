package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * King (class)
 *
 * @author Univr ARFA
 */
public final class King extends Piece {

	/**
	 * King constructor
	 *
	 * @param instance color
	 */
	public King(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? King.class.getResource("/king-white.png") : King.class.getResource("/king-black.png")),
			color
		);
	}

	/**
	 * King instance copy constructor
	 *
	 * @param piece old reference to do the copy with
	 */
	public King(Piece piece) {
		super(piece);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.KING;
	}
}