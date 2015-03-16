package model;

import java.awt.Point;

/**
 * Model (Interface)
 *
 * @author Univr ARFA
 */
public interface Model {

    public PieceType getPieceType();

    public PieceColor getPieceColor();

    public boolean wasMoved();

    public void setAsMoved();

    public boolean isDomain();

    public void setDomain(boolean isDomain);

    public boolean isCaptureDomain();

    public void setCaptureDomain(boolean isCaptureDomain);

    public Point getChessBoardLocation();

    public void setChessBoardLocation(Point chessBoardLocation);
}
