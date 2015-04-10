package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * Knight (class)
 *
 * @author Univr ARFA
 */
public final class Knight extends Piece {

	/**
	 * Knight constructor
	 *
	 * @param instance color
	 */
	public Knight(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Knight.class.getResource("/knight-white.png") : Knight.class.getResource("/knight-black.png")),
			color
		);
	}

	/**
	 * Knight instance copy constructor
	 *
	 * @param piece old reference to do the copy with
	 */
	public Knight(Piece piece) {
		super(piece);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.KNIGHT;
	}
}