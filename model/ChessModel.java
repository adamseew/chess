package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import view.*;

/**
 * ChessModel (Class)
 * @author Univr ARFA
 */
public final class ChessModel {
	private Piece[][] pieces;
	private Status status;
	private Piece waitingMove;
	private ArrayList<Piece> captured;
	private Piece blackKing;
	private Piece whiteKing;
	
	/**
     * Default constructor
     */
    public ChessModel() { 
		resetModel();
	}
	
	/**
     * Constructor that make same, not referenced, copy
     * @param chessModel original ChessModel istance
     */
    public ChessModel(ChessModel chessModel) {
		pieces = new Piece[8][8];
		captured = new ArrayList<Piece>();
		for (int i = 0; i < 8; i++) {
			int j = 0;
			for (Piece piece : chessModel.pieces[i]) {
				switch (piece.getPieceType()) {
				case BISHOP: pieces[i][j] = new Bishop(piece); break;
				case EMPTY: pieces[i][j] = new Empty(piece); break;
				case KING: pieces[i][j] = new King(piece);
					if (piece.getPieceColor() == PieceColor.WHITE)
						whiteKing = pieces[i][j];
					else if (piece.getPieceColor() == PieceColor.BLACK)
						blackKing = pieces[i][j];
					break;
				case KNIGHT: pieces[i][j] = new Knight(piece); break;
				case PAWN: pieces[i][j] = new Pawn(piece); break;
				case QUEEN:	pieces[i][j] = new Queen(piece); break;
				case ROOK: pieces[i][j] = new Rook(piece);
				}
				
				pieces[i][j].setChessBoardLocation(new Point(i, j));
				j++;
			}
		}
		this.status = chessModel.status;
	}
	
	/**
     * Will return all ChessModel pieces set
     * @return all ChessModel pieces set
     */
    public Piece[][] getPieces() {
		return pieces;
	}
	

	/**
     * Will return ChessModel pieces as List in a specific color set
     * @param color pecific color set to be returned
     * @return ChessModel pieces as List in a specific color set
     */
    public List<Piece> getPieces(PieceColor color) {
		ArrayList<Piece> colorPieces = new ArrayList<Piece>();
		for (Piece[] pieces : this.pieces)
			for (Piece piece : pieces)
				if (piece.getPieceColor() == color) 
					colorPieces.add(piece);
		return colorPieces;
	}
	
    /**
     * Will return ChessModel domain pieces as List in a specific color set
     * @param color pecific color set to be returned
     * @return ChessModel domain pieces as List in a specific color set
     */
	public List<Piece> getDomainPieces(PieceColor color) {
		ArrayList<Piece> domainPieces = new ArrayList<Piece>();
		for (Piece[] pieces : this.pieces)
			for (Piece piece : pieces)
				if (piece.isDomain()) 
					domainPieces.add(piece);
		return domainPieces;
	}
	
    
	/**
     * Will return king pointer of the specified color
     * @param color king color to be returned as pointer
     * @return king pointer of the specified color
     */
    public Piece getKing(PieceColor color) {
		if (color == PieceColor.WHITE)
			return whiteKing;
		else if (color == PieceColor.BLACK)
			return blackKing;
		return new Empty();
	}
	
	/**
     * Will return the piece from specific point
     * @param point Point pointer to ChessModel coordinates
     * @return the piece from specific point
     */
    public Piece getPiece(Point point) {
		if(point.x < 8 && point.x >= 0 && point.y < 8 && point.y >= 0)
			return pieces[point.x][point.y];
		else
			return new Empty();
	}
	
    /**
     * Will set the piece from specific point
     * @param point Point pointer to ChessModel coordinates
     * @param newPiece new Piece pointer (BEWARE is passed as reference and will be probably changed)
     */
	public void setPiece(Point point, Piece newPiece) {
		pieces[point.x][point.y] = newPiece;
		updatePiecesLocations();
	}
	
     /**
     * Will swap two pieces by start and final point
     * @param startPoint Point pointer to the start point
     * @param finalPoint Point pointer to the final point
     */
	public void swapPieces(Point startPoint, Point finalPoint) {
		Piece piece = pieces[startPoint.x][startPoint.y];
		pieces[startPoint.x][startPoint.y] = pieces[finalPoint.x][finalPoint.y];
		pieces[finalPoint.x][finalPoint.y] = piece;
		updatePiecesLocations();
	}
	
    /**
     * Will return ChessModel status
     * @return ChessModel status
     */
	public Status getStatus() {
		return status;
	}
	
	/**
     * Will set ChessModel status
     * @param status new Status
     */
    public void setStatus(Status status) {
		this.status = status;
	}
	
    /**
     * Will return pointer to the focused piece on the ChessModel
     * @return pointer to the focused piece on the ChessModel
     */
	public Piece getWaitingMove() {
		return waitingMove;
	}
	
    
	/**
     * Will set pointer to the focused piece on the ChessModel
     * @param waitingMove pointer to the focused piece on the ChessModel (BEWARE is passed as reference and will be probably changed)
     */
    public void setWaitingMove(Piece waitingMove) {
		this.waitingMove = waitingMove;
		waitingMove.setWaitingMove(true);
	}
	
    
	/**
     * Will reset ChessModel completely (BEWARE any data about previous configuration are saved)
     */
    public void resetModel() {
		status = Status.TURN_WHITE;
		captured = new ArrayList<Piece>();
		pieces = new Piece[][] {{ 
			new Rook(PieceColor.BLACK), new Knight(PieceColor.BLACK), new Bishop(PieceColor.BLACK),	new Queen(PieceColor.BLACK), new King(PieceColor.BLACK), new Bishop(PieceColor.BLACK),	new Knight(PieceColor.BLACK), new Rook(PieceColor.BLACK) },{ 
			new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK) },{ 
			new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty() },{ 
			new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty() },{
			new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty() },{ 
			new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty() },{ 
			new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE) },{ 
			new Rook(PieceColor.WHITE), new Knight(PieceColor.WHITE), new Bishop(PieceColor.WHITE), new Queen(PieceColor.WHITE), new King(PieceColor.WHITE), new Bishop(PieceColor.WHITE), new Knight(PieceColor.WHITE), new Rook(PieceColor.WHITE) 
		}};
		blackKing = pieces[0][4];
		whiteKing = pieces[7][4];
		updatePiecesLocations();
	}
	
	/**
     * Will add captured piece pointer into captured collection (no swap/delete/redesign operation is done)
     * @param piece captured piece
     */
    public void addCapturedPiece(Piece piece) {
		captured.add(piece);
	}

	/**
     * Will remove captured piece pointer from captured collection (no swap/insert/redesign operation is done)
     * @param piece captured piece
     */
    public void removeCapturedPiece(Piece piece) {
		captured.remove(piece);
		
	}
	
	/**
     * Will reset domain, capture domain, and setWaitingMove pointer on the current ChessModel configuration
     */
    public void resetDomain() {
		for (Piece[] pieces : this.pieces)
			for (Piece piece : pieces) {
				piece.setDomain(false);
				piece.setCaptureDomain(false);
				piece.setWaitingMove(false);
			}
	}
	
	private void updatePiecesLocations() {
		for (int i = 0; i < 8; i++) {
			int j = 0;
			for (Piece piece : pieces[i]) {
				new Point(i, j);
				piece.setChessBoardLocation(new Point(i, j));
				j++;
			}
		}
	}
}
