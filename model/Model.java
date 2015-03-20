package model;

import java.awt.Point;

/**
 * Model (Interface)
 *
 * @author Univr ARFA
 */
public interface Model {

    /**
     * Returns piece's type 
     * 
     * @return piece's type 
     */
    public PieceType getPieceType();

    /**
     * Returns piece's color 
     * 
     * @return piece's color 
     */
    public PieceColor getPieceColor();

    /**
     * Returns if piece was moved 
     * 
     * @return if piece was moved  
     */
    public boolean wasMoved();

    /**
     * Sets piece moved property as true
     * 
     */
    public void setAsMoved();
    
    /**
     * Returns if piece is domain
     *   
     */
    public boolean isDomain();

    /**
     * Sets piece's domain 
     * 
     * @param isDomain piece's domain
     */
    public void setDomain(boolean isDomain);

    /**
     * Returns if piece is capturable 
     * 
     * @return if piece is capturable 
     */
    public boolean isCaptureDomain();

    /**
     * Sets capture domain  
     * 
     * @param isCaptureDomain piece's capture domain 
     */
    public void setCaptureDomain(boolean isCaptureDomain);

    /**
     * Returns piece's location on chessboard
     * 
     * @return piece's location on chessboard
     */
    public Point getChessBoardLocation();

    /**
     * Sets piece's chessboard location  
     * 
     * @param chessBoardLocation piece's new location on chessboard 
     */
    public void setChessBoardLocation(Point chessBoardLocation);
}
