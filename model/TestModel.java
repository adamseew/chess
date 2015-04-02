package model;

import java.awt.Point;

import controller.*;

import junit.framework.Assert;


/**
 * TestModel (JUnit Test Case)
 *
 * @author Univr ARFA
 */
public class TestModel {
	ChessModel chessModel = new ChessModel();
	ChessController chessController = new ChessController(chessModel);

    /**
     * TestPosition tests the position of pieces on initalized chessboard
     * 
     */
	@org.junit.Test
	public void TestPosition() {
		Assert.assertEquals(PieceType.ROOK, chessModel.getPiece(new Point(0, 0)).getPieceType());
		Assert.assertEquals(PieceType.KNIGHT, chessModel.getPiece(new Point(0, 1)).getPieceType());
		Assert.assertEquals(PieceType.BISHOP, chessModel.getPiece(new Point(0, 2)).getPieceType());
		Assert.assertEquals(PieceType.QUEEN, chessModel.getPiece(new Point(0, 3)).getPieceType());
		Assert.assertEquals(PieceType.KING, chessModel.getPiece(new Point(0, 4)).getPieceType());
		Assert.assertEquals(PieceType.BISHOP, chessModel.getPiece(new Point(0, 5)).getPieceType());
		Assert.assertEquals(PieceType.KNIGHT, chessModel.getPiece(new Point(0, 6)).getPieceType());
		Assert.assertEquals(PieceType.ROOK, chessModel.getPiece(new Point(0, 7)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 0)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 1)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 2)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 3)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 4)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 5)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 6)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(1, 7)).getPieceType());
		
		Assert.assertEquals(PieceType.EMPTY, chessModel.getPiece(new Point(2, 0)).getPieceType());
		Assert.assertEquals(PieceType.EMPTY, chessModel.getPiece(new Point(3, 3)).getPieceType());
		Assert.assertEquals(PieceType.EMPTY, chessModel.getPiece(new Point(4, 5)).getPieceType());
		Assert.assertEquals(PieceType.EMPTY, chessModel.getPiece(new Point(5, 7)).getPieceType());
		
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 0)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 1)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 2)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 3)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 4)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 5)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 6)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessModel.getPiece(new Point(6, 7)).getPieceType());
		Assert.assertEquals(PieceType.ROOK, chessModel.getPiece(new Point(7, 0)).getPieceType());
		Assert.assertEquals(PieceType.KNIGHT, chessModel.getPiece(new Point(7, 1)).getPieceType());
		Assert.assertEquals(PieceType.BISHOP, chessModel.getPiece(new Point(7, 2)).getPieceType());
		Assert.assertEquals(PieceType.QUEEN, chessModel.getPiece(new Point(7, 3)).getPieceType());
		Assert.assertEquals(PieceType.KING, chessModel.getPiece(new Point(7, 4)).getPieceType());
		Assert.assertEquals(PieceType.BISHOP, chessModel.getPiece(new Point(7, 5)).getPieceType());
		Assert.assertEquals(PieceType.KNIGHT, chessModel.getPiece(new Point(7, 6)).getPieceType());
		Assert.assertEquals(PieceType.ROOK, chessModel.getPiece(new Point(7, 7)).getPieceType());
	}

	/**
     * TestStatus tests the status of initialized chessboard
     * 
     */
	@org.junit.Test
	public void TestStatus() {
		Assert.assertEquals(chessModel.getStatus(), Status.TURN_WHITE);
	}
	
	/**
     * TestColor tests the color of pieces of initialized chessboard 
     * 
     */
	@org.junit.Test
	public void TestColor() {

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				Assert.assertEquals(PieceColor.BLACK, chessModel.getPiece(new Point(i, j)).getPieceColor());
			}
		}
		
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				Assert.assertEquals(PieceColor.EMPTY, chessModel.getPiece(new Point(i, j)).getPieceColor());
			}
		}
		
		for (int i = 6; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Assert.assertEquals(PieceColor.WHITE, chessModel.getPiece(new Point(i, j)).getPieceColor());
			}
		}
	}

}
