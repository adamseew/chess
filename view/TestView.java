package view;

import java.awt.Point;

import model.*;

import junit.framework.Assert;

/**
 * TestController (JUnit Test Case)
 * 
 * @author Univr ARFA
 */

public class TestView {

	ChessModel chessCm = new ChessModel();
	
	/**
	 * TestImage tests if the image corresponds to the piece
	 * 
	 */
	@org.junit.Test
	public void TestImage() {
		Assert.assertEquals(""+Rook.class.getResource("/rook-black.png"),(chessCm.getPiece(new Point(0, 0)).getIcon()).toString());
		Assert.assertEquals(""+Knight.class.getResource("/knight-black.png"),(chessCm.getPiece(new Point(0, 1)).getIcon()).toString());
		Assert.assertEquals(""+Bishop.class.getResource("/bishop-black.png"),(chessCm.getPiece(new Point(0, 2)).getIcon()).toString());
		Assert.assertEquals(""+Queen.class.getResource("/queen-black.png"),(chessCm.getPiece(new Point(0, 3)).getIcon()).toString());
		Assert.assertEquals(""+King.class.getResource("/king-black.png"),(chessCm.getPiece(new Point(0, 4)).getIcon()).toString());
		Assert.assertEquals(""+Bishop.class.getResource("/bishop-black.png"),(chessCm.getPiece(new Point(0, 5)).getIcon()).toString());
		Assert.assertEquals(""+Knight.class.getResource("/knight-black.png"),(chessCm.getPiece(new Point(0, 6)).getIcon()).toString());
		Assert.assertEquals(""+Rook.class.getResource("/rook-black.png"),(chessCm.getPiece(new Point(0, 7)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 0)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 1)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 2)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 3)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 4)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 5)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 6)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-black.png"),(chessCm.getPiece(new Point(1, 7)).getIcon()).toString());

		Assert.assertEquals(""+Rook.class.getResource("/rook-white.png"),(chessCm.getPiece(new Point(7, 0)).getIcon()).toString());
		Assert.assertEquals(""+Knight.class.getResource("/knight-white.png"),(chessCm.getPiece(new Point(7, 1)).getIcon()).toString());
		Assert.assertEquals(""+Bishop.class.getResource("/bishop-white.png"),(chessCm.getPiece(new Point(7, 2)).getIcon()).toString());
		Assert.assertEquals(""+Queen.class.getResource("/queen-white.png"),(chessCm.getPiece(new Point(7, 3)).getIcon()).toString());
		Assert.assertEquals(""+King.class.getResource("/king-white.png"),(chessCm.getPiece(new Point(7, 4)).getIcon()).toString());
		Assert.assertEquals(""+Bishop.class.getResource("/bishop-white.png"),(chessCm.getPiece(new Point(7, 5)).getIcon()).toString());
		Assert.assertEquals(""+Knight.class.getResource("/knight-white.png"),(chessCm.getPiece(new Point(7, 6)).getIcon()).toString());
		Assert.assertEquals(""+Rook.class.getResource("/rook-white.png"),(chessCm.getPiece(new Point(7, 7)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 0)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 1)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 2)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 3)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 4)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 5)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 6)).getIcon()).toString());
		Assert.assertEquals(""+Pawn.class.getResource("/pawn-white.png"),(chessCm.getPiece(new Point(6, 7)).getIcon()).toString());
	}

}
