package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * Queen (class)
 *
 * @author Univr ARFA
 */
public final class Queen extends Piece {

	/**
	 * Queen constructor
	 *
	 * @param instance color
	 */
	public Queen(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Queen.class.getResource("/queen-white.png") : Queen.class.getResource("/queen-black.png")),
			color
		);
	}

	/**
	 * Queen instance copy constructor
	 *
	 * @param piece old reference to do the copy with
	 */
	public Queen(Piece piece) {
		super(piece);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.QUEEN;
	}
}