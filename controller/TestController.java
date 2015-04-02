package controller;

import java.awt.Point;

import model.*;
import view.*;

import junit.framework.Assert;

/**
 * TestController (JUnit Test Case)
 *
 * @author Univr ARFA
 */
public class TestController {
	ChessModel chessModel = new ChessModel();
	ChessModel chessCm = new ChessModel();
	ChessController chessController = new ChessController(chessModel);

	 /**
     * TestSwapAndKinkUnderCheck tests of method of swap and if the king is under check
     * 
     */
	@org.junit.Test
	public void TestSwapAndKinkUnderCheck() {
		ChessModel chessKUC = new ChessModel();
		ChessController chessController = new ChessController(chessKUC);

		Assert.assertTrue(!chessController.isKingUnderCheck(PieceColor.BLACK,chessModel));

		chessKUC.swapPieces(new Point(6, 2), new Point(4, 2));
        
		Assert.assertEquals(PieceType.PAWN, chessKUC.getPiece(new Point(4, 2)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessKUC.getPiece(new Point(1, 3)).getPieceType());

		chessKUC.swapPieces(new Point(1, 3), new Point(3, 3));
        
		Assert.assertEquals(PieceType.PAWN, chessKUC.getPiece(new Point(3, 3)).getPieceType());

		chessKUC.swapPieces(new Point(7, 3), new Point(0, 4));
        
		Assert.assertEquals(PieceType.QUEEN, chessKUC.getPiece(new Point(0, 4)).getPieceType());

		Assert.assertTrue(chessController.isKingUnderCheck(PieceColor.BLACK, chessKUC));
	}
	
	/**
     * TestCheckmateAndResetModel tests the checkmate and the method of reset model
     * 
     */	
	@org.junit.Test
	public void TestCheckmateAndResetModel() {
		Assert.assertTrue(!chessController.checkmate(PieceColor.BLACK, chessCm));
		Assert.assertTrue(!chessController.checkmate(PieceColor.WHITE, chessCm));
        
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!((i == 0 && j == 4) || (i == 7 && j == 4) || (i == 7 && j == 7)))
					chessCm.setPiece(new Point(i, j), new Empty());
			}
		}
		
		chessCm.swapPieces(new Point(7, 7), new Point(0, 5));
		chessCm.swapPieces(new Point(7, 4), new Point(2, 4));
		
		Assert.assertEquals(PieceType.KING, chessCm.getPiece(new Point(2, 4)).getPieceType());
		Assert.assertEquals(PieceColor.WHITE, chessCm.getPiece(new Point(2, 4)).getPieceColor());
		Assert.assertEquals(PieceType.ROOK, chessCm.getPiece(new Point(0, 5)).getPieceType());
		Assert.assertEquals(PieceColor.WHITE, chessCm.getPiece(new Point(0, 5)).getPieceColor());
		Assert.assertEquals(PieceType.KING, chessCm.getPiece(new Point(0, 4)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessCm.getPiece(new Point(0, 4)).getPieceColor());
        
		Assert.assertTrue(chessController.checkmate(PieceColor.BLACK, chessCm));
		
		chessCm.resetModel();
        
		Assert.assertEquals(PieceType.KING, chessCm.getPiece(new Point(0, 4)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessCm.getPiece(new Point(1, 4)).getPieceType());
		Assert.assertEquals(PieceType.EMPTY, chessCm.getPiece(new Point(4, 5)).getPieceType());
		Assert.assertEquals(PieceType.PAWN, chessCm.getPiece(new Point(6, 2)).getPieceType());
		Assert.assertEquals(PieceType.KNIGHT, chessCm.getPiece(new Point(7, 6)).getPieceType());
	}
		
	/**
	 * TestCheckmate tests the checkmate
	 * 
	 */	
	@org.junit.Test
	public void TestCheckmate() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (!((i == 7 && j == 4) || (i == 0 && j == 4) || (i == 0 && j == 2) || (i == 0 && j == 5)))
					chessCm.setPiece(new Point(i, j), new Empty());
		
		chessCm.swapPieces(new Point(7, 4), new Point(7, 7));
		chessCm.swapPieces(new Point(0, 4), new Point(5, 7));
		chessCm.swapPieces(new Point(0, 2), new Point(5, 5));
		chessCm.swapPieces(new Point(0, 5), new Point(4, 3));
		
		Assert.assertEquals(PieceType.KING, chessCm.getPiece(new Point(7, 7)).getPieceType());
		Assert.assertEquals(PieceColor.WHITE, chessCm.getPiece(new Point(7, 7)).getPieceColor());
		
		Assert.assertEquals(PieceType.KING, chessCm.getPiece(new Point(5, 7)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessCm.getPiece(new Point(5, 7)).getPieceColor());
		
		Assert.assertEquals(PieceType.BISHOP, chessCm.getPiece(new Point(5, 5)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessCm.getPiece(new Point(5, 5)).getPieceColor());
		
		Assert.assertEquals(PieceType.BISHOP, chessCm.getPiece(new Point(4, 3)).getPieceType());
		Assert.assertEquals(PieceColor.BLACK, chessCm.getPiece(new Point(4, 3)).getPieceColor());
				
		Assert.assertTrue(chessController.checkmate(PieceColor.WHITE, chessCm));
	}
}	