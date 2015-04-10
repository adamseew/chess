package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * Pawn (class)
 *
 * @author Univr ARFA
 */
public final class Pawn extends Piece {

	/**
	 * Pawn constructor
	 *
	 * @param instance color
	 */
	public Pawn(PieceColor color) {
		super(
			new ImageIcon(color == PieceColor.WHITE ? Pawn.class.getResource("/pawn-white.png") : Pawn.class.getResource("/pawn-black.png")),
			color
		);
	}

	/**
	 * Pawn instance copy constructor
	 *
	 * @param piece old reference to do the copy with
	 */
	public Pawn(Piece piece) {
		super(piece);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.PAWN;
	}
}