// UNIVR-ARFA 
package view;

import javax.swing.ImageIcon;
import model.*;

public final class Empty extends Piece {

	public Empty() {
		super(new ImageIcon());
	}
	
	public Empty(Piece piece) {
		super(piece);
	}
	
	public PieceType getPieceType() {
		return PieceType.EMPTY;
	}
}