package controller;

import java.awt.Point;
import view.*;
import model.*;

public final class ChessController implements Controller {

	private ChessModel chessModel;
	
	public ChessController(ChessModel chessModel) {
		this.chessModel = chessModel;
	}
	
	public void onClick(ChessBoard caller, Point clicked) {		
		Piece piece = chessModel.getPiece(clicked);
		PieceType pieceType = piece.getPieceType();
		PieceColor pieceColor = piece.getPieceColor();
		Status status = chessModel.getStatus();
		
		if (status == Status.WON_WHITE || status == Status.WON_BLACK) {
			chessModel.resetModel();
			
			caller.designChessBoard(true);
		}
		else if (pieceType == PieceType.EMPTY && !piece.isDomain() || piece.isWaitingMove() ||
			status == Status.WAITING_MOVE_WHITE && pieceColor == PieceColor.BLACK && !piece.isDomain() ||
			status == Status.WAITING_MOVE_BLACK && pieceColor == PieceColor.WHITE && !piece.isDomain()) 
		{ 
			// Need to reset domains to permit other moves
			chessModel.resetDomain();
			chessModel.setStatus(
                                status.toString().contains("WHITE") ? 
                                Status.TURN_WHITE : 
                                Status.TURN_BLACK
                        );
			
			caller.designChessBoard(true);
		}
		else if ((status == Status.WAITING_MOVE_WHITE || status == Status.WAITING_MOVE_BLACK) 
			&& piece.isDomain() && !chessModel.getWaitingMove().equals(clicked))
		{
			Piece moved = chessModel.getPiece(chessModel.getWaitingMove().getChessBoardLocation());
			moved.setAsMoved();
			if (!piece.isCaptureDomain()) {
				// When the destination piece is empty simply swap two pieces to perfom the movement
				chessModel.swapPieces(chessModel.getWaitingMove().getChessBoardLocation(), clicked);
			} else {
				// Save capture piece into memory and replace it with a new empty piece
				chessModel.addCapturedPiece(piece);
				chessModel.setPiece(clicked, new Empty());
				chessModel.swapPieces(chessModel.getWaitingMove().getChessBoardLocation(), clicked);
			}
			// Let's promote pawn
			if (moved.getPieceType() == PieceType.PAWN && clicked.x == 0 && 
                                moved.getPieceColor() == PieceColor.WHITE || moved.getPieceType() == PieceType.PAWN && 
                                clicked.x == 7 && moved.getPieceColor() == PieceColor.BLACK) 
			{
				switch (caller.pawnPromotionDialog()) {
				case BISHOP: chessModel.setPiece(clicked, new Bishop(moved.getPieceColor())); break;
				case KNIGHT: chessModel.setPiece(clicked, new Knight(moved.getPieceColor())); break;
				case QUEEN: chessModel.setPiece(clicked, new Queen(moved.getPieceColor())); break;
				default: chessModel.setPiece(clicked, new Rook(moved.getPieceColor()));
				}
			}
			
			// Need to reset domains to permit other moves
			chessModel.resetDomain();
			chessModel.setStatus(
                                status == Status.WAITING_MOVE_WHITE ? 
                                Status.TURN_BLACK : 
                                Status.TURN_WHITE
                        );
			
			// Let's check if there is a checkmate condition
			if (checkmate(
                                status == Status.WAITING_MOVE_WHITE ? 
                                PieceColor.BLACK : 
                                PieceColor.WHITE, 
                                new ChessModel(chessModel)
                        )) {
				chessModel.setStatus(
                                        status == Status.WAITING_MOVE_WHITE ? 
                                        Status.WON_WHITE : 
                                        Status.WON_BLACK
                                );
				caller.signalCheckmate();
			}
			// Let's check if the opposite king is under check after move
			else if (isKingUnderCheck(
                                status == Status.WAITING_MOVE_WHITE ? 
                                PieceColor.BLACK : 
                                PieceColor.WHITE,
                                new ChessModel(chessModel)))
				// Signal user check condition if it happens (it was checked by calling 
                                // isKingUnderCheck
				caller.signalCheck();
			
			caller.designChessBoard(true);
		}
		else if (status == Status.TURN_WHITE && pieceColor == PieceColor.WHITE ||
			status == Status.TURN_BLACK && pieceColor == PieceColor.BLACK) 
		{
			switch(pieceType) {
			case PAWN: pawn(clicked); break;
			case KNIGHT: knight(clicked); break;
			case ROOK: rook(clicked); break;
			case BISHOP: bishop(clicked); break;
			case QUEEN: queen(clicked); break;
			// Doing same as Queen but on next neighbors only (1 step instead of 8)
			case KING: queen(clicked, 1);
			}
			
			// We need to check if by swapping the piece, our king will become capturable. 
			// If so, we must deny specific swap operation by setting the piece's domain
			// as false even if in normal cases it should be true			
			for (Piece domainPiece: chessModel.getDomainPieces(pieceColor))
				if (!clicked.equals(domainPiece.getChessBoardLocation())) {
					ChessModel modelCopy = new ChessModel(chessModel);
					if (domainPiece.isCaptureDomain()) {
						modelCopy.setPiece(domainPiece.getChessBoardLocation(), new Empty());
						modelCopy.swapPieces(clicked, domainPiece.getChessBoardLocation());
					} else
						modelCopy.swapPieces(clicked, domainPiece.getChessBoardLocation());
					
					if (isKingUnderCheck(pieceColor, modelCopy)) {
						domainPiece.setDomain(false);
						domainPiece.setCaptureDomain(false);
					}
				}
			
			chessModel.setStatus(
                                status == Status.TURN_WHITE ? 
                                Status.WAITING_MOVE_WHITE : 
                                Status.WAITING_MOVE_BLACK
                        );
			
			// Design chess board after changing model
			caller.designChessBoard();
		}
		else if (status == Status.WAITING_MOVE_WHITE && pieceColor == PieceColor.WHITE || 
			status == Status.WAITING_MOVE_BLACK && pieceColor == PieceColor.BLACK)
		{
			// Need to reset domains to permit other moves
			chessModel.resetDomain();
			chessModel.setStatus(
                                status == Status.WAITING_MOVE_WHITE ? 
                                Status.TURN_WHITE : 
                                Status.TURN_BLACK
                        );
				
			// Design chess board after changing model
			caller.designChessBoard(true);
			if (!chessModel.getWaitingMove().equals(clicked))
				onClick(caller, clicked);
		}
		
		caller.setTitle("Chess (" + chessModel.getStatus().toString().toLowerCase().replace('_', ' ') + ")");
	}
	
	private boolean checkmate(PieceColor color, ChessModel model) {
		for(Piece piece: model.getPieces(color)) {
			switch(piece.getPieceType()) {
			case PAWN: pawn(piece.getChessBoardLocation(), model); break;
			case KNIGHT: knight(piece.getChessBoardLocation(), model); break;
			case ROOK: rook(piece.getChessBoardLocation(), model); break;
			case BISHOP: bishop(piece.getChessBoardLocation(), model); break;
			case QUEEN: queen(piece.getChessBoardLocation(), model); break;
			case KING: queen(piece.getChessBoardLocation(), model, 1);
			}
			
			for (Piece domainPiece: model.getDomainPieces(color))
				if (!piece.getChessBoardLocation().equals(domainPiece.getChessBoardLocation())) {
					ChessModel modelCopy = new ChessModel(model);
					if (domainPiece.isCaptureDomain()) {
						modelCopy.setPiece(domainPiece.getChessBoardLocation(), new Empty());
						modelCopy.swapPieces(
                                                        piece.getChessBoardLocation(), 
                                                        domainPiece.getChessBoardLocation()
                                                );
					} else
						modelCopy.swapPieces(
                                                        piece.getChessBoardLocation(), 
                                                        domainPiece.getChessBoardLocation()
                                                );
			
					if (!isKingUnderCheck(color, modelCopy))
						return false;
				}
			model.resetDomain();
		}
		return true;
	}
	
	private boolean isKingUnderCheck(PieceColor kingColor, ChessModel model) {
		Status status = model.getStatus();
		Piece king = model.getKing(kingColor);
		boolean isKingUnderCheck = false;
		for (Piece piece: model.getPieces(
                        kingColor == PieceColor.WHITE ? 
                        PieceColor.BLACK : 
                        PieceColor.WHITE
                )) {
			model.setStatus(
                                kingColor == PieceColor.WHITE ? 
                                Status.TURN_BLACK : 
                                Status.TURN_WHITE
                        );
			
			switch (piece.getPieceType()) {
			case PAWN: pawn(piece.getChessBoardLocation(), model); break;
			case KNIGHT: knight(piece.getChessBoardLocation(), model); break;
			case ROOK: rook(piece.getChessBoardLocation(), model); break;
			case BISHOP: bishop(piece.getChessBoardLocation(), model); break;
			case QUEEN: queen(piece.getChessBoardLocation(), model); break;
			case KING: queen(piece.getChessBoardLocation(), model, 1);
			}
			
			if (king.isCaptureDomain()) {
				isKingUnderCheck = true;
				break;
			}
		}
		model.setStatus(status);
		return isKingUnderCheck;
	}
	
	private void queen(Point clicked) {
		queen(clicked, this.chessModel);
	}

	private void queen(Point clicked, ChessModel model) {
		queen(clicked, model, 8);
	}
	
	private void queen(Point clicked, int steps) {
		queen(clicked, this.chessModel, steps);
	}
	
	private void queen(Point clicked, ChessModel model, int steps) {
		Status status = model.getStatus();

		model.getPiece(clicked).setDomain(true);
		model.setWaitingMove(model.getPiece(clicked));
			
		// Trying possible moves by scanning possibilities step by step
		stepDomainScanner(new Point(clicked), model, status, 1, 1, steps);
		stepDomainScanner(new Point(clicked), model, status, -1, -1, steps);
		stepDomainScanner(new Point(clicked), model, status, -1, 1, steps);
		stepDomainScanner(new Point(clicked), model, status, 1, -1, steps);
		stepDomainScanner(new Point(clicked), model, status, 1, 0, steps);
		stepDomainScanner(new Point(clicked), model, status, -1, 0, steps);
		stepDomainScanner(new Point(clicked), model, status, 0, 1, steps);
		stepDomainScanner(new Point(clicked), model, status, 0, -1, steps);
	}
	
	private void bishop(Point clicked) {
		bishop(clicked, this.chessModel);
	}

	private void bishop(Point clicked, ChessModel model) {
		Status status = model.getStatus();

		model.getPiece(clicked).setDomain(true);
		model.setWaitingMove(model.getPiece(clicked));
			
		// Trying possible moves by scanning possibilities step by step
		stepDomainScanner(new Point(clicked), model, status, 1, 1);
		stepDomainScanner(new Point(clicked), model, status, -1, -1);
		stepDomainScanner(new Point(clicked), model, status, -1, 1);
		stepDomainScanner(new Point(clicked), model, status, 1, -1);
	}
	
	private void rook(Point clicked) {
		rook(clicked, this.chessModel);
	}
	
	private void rook(Point clicked, ChessModel model) {
		Status status = model.getStatus();
		
		model.getPiece(clicked).setDomain(true);
		model.setWaitingMove(model.getPiece(clicked));
			
		// Trying possible moves by scanning possibilities step by step
		stepDomainScanner(new Point(clicked), model, status, 1, 0);
		stepDomainScanner(new Point(clicked), model, status, -1, 0);
		stepDomainScanner(new Point(clicked), model, status, 0, 1);
		stepDomainScanner(new Point(clicked), model, status, 0, -1);
	}
	
	private void knight(Point clicked) {
		knight(clicked, this.chessModel);
	}

	private void knight(Point clicked, ChessModel model) {
		Status status = model.getStatus();
		
		// Making a copy of clicked we won't change caller's version
		clicked = new Point(clicked);
		
		model.getPiece(clicked).setDomain(true);
		model.setWaitingMove(model.getPiece(clicked));
			
		// Trying possible moves by translating locations sequentially
		int[] possibleMoves = new int[] { -2, 1, 1, 1, 2, 0, 1, -1, 0, -2, -1, -1, -2, 0, -1, 1 };
		for (int i = 0; i < possibleMoves.length; i += 2)
			stepDomainScanner(clicked, model, status, possibleMoves[i], possibleMoves[i + 1], 1);
	}
	
	private void pawn(Point clicked) {
		pawn(clicked, this.chessModel);
	}

	private void pawn(Point clicked, ChessModel model) {
		Piece piece = model.getPiece(clicked);
		Status status = model.getStatus();
		
		// Making a copy of clicked we won't change caller's version
		clicked = new Point(clicked);
		
		model.getPiece(clicked).setDomain(true);
		model.setWaitingMove(model.getPiece(clicked));
		
		int pieceTypeConstant = status == Status.TURN_WHITE ? -1 : 1;
				
		if (stepDomainScanner(clicked, model, status, pieceTypeConstant, 0, 1, true, false) == 0 && 
                        !piece.wasMoved()
                )
			stepDomainScanner(new Point(clicked), model, status, pieceTypeConstant, 0, 1, true, false);
		stepDomainScanner(clicked, model, status, 0, -1, 1, false, true);
		stepDomainScanner(clicked, model, status, 0, 2, 1, false, true);
	}
	
	private int stepDomainScanner(Point clicked, ChessModel model, Status status, int verticalStep, 
                int horizontalStep
        ) {
		return stepDomainScanner(clicked, model, status, verticalStep, horizontalStep, 8, true,  true);
	}
	
	private int stepDomainScanner(Point clicked, ChessModel model, Status status, int verticalStep, 
                int horizontalStep, int steps
        ) {
		return stepDomainScanner(clicked, model, status, verticalStep, horizontalStep, steps, true, true);
	}
	
	// Return value: (i) 0 - anyone in the way; (ii) 1 - someone of the same color in the way; 
	// (iii) -1 - someone capturable of the opposite color
	private int stepDomainScanner(Point clicked, ChessModel model, Status status, int verticalStep, 
                int horizontalStep, int steps, boolean setDomain, boolean setCaptureDomain
        ) {
		Point point = new Point(clicked);
		for (int i = 0; i < steps; i++) {
			clicked.translate(verticalStep, horizontalStep);
			Piece capturable = model.getPiece(clicked);
			if (clicked.x > 7 || clicked.x < 0 || clicked.y > 7 || clicked.y < 0)
				// Out of bounds, reached chess board limit, returning true specifies that 
				// all the way from start point to end point was empty
				return 0;
			if (capturable.getPieceType() == PieceType.EMPTY && setDomain) {
				// Free way, let's mark it
				capturable.setDomain(true);
			}
			else if (capturable.getPieceType() != PieceType.EMPTY && setCaptureDomain && ( 
				capturable.getPieceColor() != PieceColor.WHITE && status == Status.TURN_WHITE ||
				capturable.getPieceColor() != PieceColor.BLACK && status == Status.TURN_BLACK)) 
			{
				capturable.setDomain(true);
				capturable.setCaptureDomain(true);

				// Reached here? So there is some piece of the opposite color, let's take to 
                                // the player possibility to capture it. By returning false the caller will 
                                // know that there is someone capturable
				return -1;
			} else
				// Someone of the same color in the way
				return 1;
		}
		return 0;
	}
}
