package view;

import javax.swing.ImageIcon;
import model.*;

/**
 * Empty (class)
 *
 * @author Univr ARFA
 */
public final class Empty extends Piece {

	/**
	 * Empty constructor
	 *
	 */
	public Empty() {
		super(new ImageIcon());
	}

	/**
	 * Empty instance copy constructor
	 *
	 * @param piece old reference to do the copy with
	 */
	public Empty(Piece piece) {
		super(piece);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.EMPTY;
	}
}