package view;

import java.awt.Color;
import java.awt.Point;
import javax.swing.Icon;
import javax.swing.JButton;
import model.*;

public abstract class Piece extends JButton implements Model {
	private final PieceColor color;
	private Point chessBoardLocation;
	private boolean moved;
	private boolean isDomain;
	private boolean isCaptureDomain;
	private boolean isWaitingMove;
	
	protected Piece(Icon image) {
		this(image, PieceColor.EMPTY);
	}

	protected Piece(Icon image, PieceColor color) {
		super(image);
		setBorderPainted(false);
		this.color = color;
	}
	
	protected Piece(Piece piece) {
		this.color = piece.color;
		this.moved = piece.moved;
		this.isDomain = piece.isDomain;
		this.isCaptureDomain = piece.isCaptureDomain;
	}
	
	public final Point getChessBoardLocation() {
		return chessBoardLocation;
	}
	
	public final void setChessBoardLocation(Point chessBoardLocation) {
		this.chessBoardLocation = chessBoardLocation;
	}
	
	public final PieceColor getPieceColor() {
		return this.color;
	}
	
	public final boolean wasMoved() {
		return moved;
	}
	
	public final void setAsMoved() {
		moved = true;
	}
	
	public final boolean isDomain() {
		return isDomain;
	}
	
	public final void setDomain(boolean isDomain) {
		this.isDomain = isDomain;
			
	}
	
	public final boolean isCaptureDomain() {
		return isCaptureDomain;
	}
	
	public final void setCaptureDomain(boolean isCaptureDomain) {
		this.isCaptureDomain = isCaptureDomain;
	}
	
	public final boolean isWaitingMove() {
		return isWaitingMove;
	}
	
	public final void setWaitingMove(boolean isWaitingMove) {
		this.isWaitingMove = isWaitingMove;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Piece && ((Piece)other).getChessBoardLocation().equals(this))
			return true;
		return false;
	}
}