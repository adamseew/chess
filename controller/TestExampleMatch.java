package controller;

import java.awt.Point;

import junit.framework.Assert;
import model.*;

/**
 * TestExampleMatch (JUnit Test Case)
 *
 * @author Univr ARFA
 */
public class TestExampleMatch {

	ChessModel chessModel = new ChessModel();
	ChessController chessController = new ChessController(chessModel);

	/**
	 * TestMatch tests simulation play
	 *
	 */
	@org.junit.Test
	public void testMatch() {
		// ARFA 8/4/2015
		// Standardly a method name should start with lower case char
		chessModel.swapPieces(new Point(6, 7), new Point(4, 7));
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(4, 7)).getPieceType());
		Assert.assertEquals(PieceColor.WHITE, chessModel.getPiece(new Point(4, 7)).getPieceColor());

		chessModel.swapPieces(new Point(1, 1), new Point(3, 1));
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(3, 1)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessModel.getPiece(new Point(3, 1)).getPieceColor());

		chessModel.swapPieces(new Point(7, 1), new Point(5, 2));
		Assert.assertEquals(PieceType.KNIGHT, chessModel.getPiece(new Point(5, 2)).getPieceType());
		Assert.assertEquals(PieceColor.WHITE, chessModel.getPiece(new Point(5, 2)).getPieceColor());

		chessModel.swapPieces(new Point(1, 6), new Point(2, 6));
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(2, 6)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessModel.getPiece(new Point(2, 6)).getPieceColor());

		chessModel.swapPieces(new Point(7, 7), new Point(5, 7));
		Assert.assertEquals(PieceType.ROOK, chessModel.getPiece(new Point(5, 7)).getPieceType());
		Assert.assertEquals(PieceColor.WHITE, chessModel.getPiece(new Point(5, 7)).getPieceColor());

		chessModel.swapPieces(new Point(0, 5), new Point(2, 7));
		Assert.assertEquals(PieceType.BISHOP, chessModel.getPiece(new Point(2, 7)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessModel.getPiece(new Point(2, 7)).getPieceColor());

		Assert.assertTrue(!chessController.checkmate(PieceColor.BLACK, chessModel));
	}
}