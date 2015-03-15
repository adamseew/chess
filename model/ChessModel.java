package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.*;

public final class ChessModel {
	private Piece[][] pieces;
	private Status status;
	private Piece waitingMove;
	private ArrayList<Piece> captured;
	private Piece blackKing;
	private Piece whiteKing;
	
	public ChessModel() { 
		resetModel();
	}
	
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
	
	public Piece[][] getPieces() {
		return pieces;
	}
	
	public List<Piece> getPieces(PieceColor color) {
		ArrayList<Piece> colorPieces = new ArrayList<Piece>();
		for (Piece[] pieces : this.pieces)
			for (Piece piece : pieces)
				if (piece.getPieceColor() == color) 
					colorPieces.add(piece);
		return colorPieces;
	}
	
	public List<Piece> getDomainPieces(PieceColor color) {
		ArrayList<Piece> domainPieces = new ArrayList<Piece>();
		for (Piece[] pieces : this.pieces)
			for (Piece piece : pieces)
				if (piece.isDomain()) 
					domainPieces.add(piece);
		return domainPieces;
	}
	
	public Piece getKing(PieceColor color) {
		if (color == PieceColor.WHITE)
			return whiteKing;
		else if (color == PieceColor.BLACK)
			return blackKing;
		return new Empty();
	}
	
	public Piece getPiece(Point point) {
		if(point.x < 8 && point.x >= 0 && point.y < 8 && point.y >= 0)
			return pieces[point.x][point.y];
		else
			return new Empty();
	}
	
	public void setPiece(Point point, Piece newPiece) {
		pieces[point.x][point.y] = newPiece;
		updatePiecesLocations();
	}
	
	public void swapPieces(Point startPoint, Point finalPoint) {
		Piece piece = pieces[startPoint.x][startPoint.y];
		pieces[startPoint.x][startPoint.y] = pieces[finalPoint.x][finalPoint.y];
		pieces[finalPoint.x][finalPoint.y] = piece;
		updatePiecesLocations();
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Piece getWaitingMove() {
		return waitingMove;
	}
	
	public void setWaitingMove(Piece waitingMove) {
		this.waitingMove = waitingMove;
		waitingMove.setWaitingMove(true);
	}
	
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
	
	public void addCapturedPiece(Piece piece) {
		captured.add(piece);
	}

	public void removeCapturedPiece(Piece piece) {
		captured.remove(piece);
		
	}
	
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
