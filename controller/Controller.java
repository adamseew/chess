package controller;

import java.awt.Point;
import view.*;

/**
 * Controller (Interface)
 *
 * @author Univr ARFA
 */
public interface Controller {

	/**
	 * OnClick event handler for ChessBoard Pieces
	 *
	 * @param caller caller pointer
	 * @param clicked clicked piece pointer represented as a point in x, y
	 * coordinates space
	 */
	void onClick(ChessBoard caller, Point clicked);
}
