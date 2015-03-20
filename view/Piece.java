package view;

import java.awt.Point;
import javax.swing.Icon;
import javax.swing.JButton;
import model.*;

/**
 * Piece (class)
 * 
 * @author Univr ARFA
 */
public abstract class Piece extends JButton implements Model {
	private final PieceColor color;
	private Point chessBoardLocation;
	private boolean moved;
	private boolean isDomain;
	private boolean isCaptureDomain;
	private boolean isWaitingMove;
	
	/**
	 * Piece constructor
	 * 
	 * @param image icon on chessbord
	 */
	protected Piece(Icon image) {
		this(image, PieceColor.EMPTY);
	}
	
	/**
	 * Piece constructor
	 * 
	 * @param image icon on chessboard
	 * @param color piece color on chessboard
	 */
	protected Piece(Icon image, PieceColor color) {
		super(image);
		setBorderPainted(false);
		this.color = color;
	}
	
	/**
	 * Piece instance copy constructor
	 * 
	 * @param piece old reference to do the copy with 
	 */
	protected Piece(Piece piece) {
		this.color = piece.color;
		this.moved = piece.moved;
		this.isDomain = piece.isDomain;
		this.isCaptureDomain = piece.isCaptureDomain;
	}
	
    @Override	
	public final Point getChessBoardLocation() {
		return chessBoardLocation;
	}
	
    @Override
	public final void setChessBoardLocation(Point chessBoardLocation) {
		this.chessBoardLocation = chessBoardLocation;
	}
	
    @Override
	public final PieceColor getPieceColor() {
		return this.color;
	}
	
    @Override
	public final boolean wasMoved() {
		return moved;
	}
	
    @Override
	public final void setAsMoved() {
		moved = true;
	}
	
    @Override
	public final boolean isDomain() {
		return isDomain;
	}
	
    @Override
	public final void setDomain(boolean isDomain) {
		this.isDomain = isDomain;
			
	}
	
    @Override
	public final boolean isCaptureDomain() {
		return isCaptureDomain;
	}
	
    @Override
	public final void setCaptureDomain(boolean isCaptureDomain) {
		this.isCaptureDomain = isCaptureDomain;
	}
	
	/**
	 * Returns if the current instance of piece is focused by the players 
	 * 
	 * @return if the current instance of piece is focused by the players 
	 */
	public final boolean isWaitingMove() {
		return isWaitingMove;
	}
	
	/**
	 * Sets if the current instance of piece is focused by the players 
	 * 
	 * @param isWaitingMove specify if the current instance of piece is focused by the players 
	 */
	public final void setWaitingMove(boolean isWaitingMove) {
		this.isWaitingMove = isWaitingMove;
	}
	
    @Override
	public boolean equals(Object other) {
		if (other instanceof Piece && ((Piece)other).getChessBoardLocation().equals(this))
			return true;
		return false;
	}
}