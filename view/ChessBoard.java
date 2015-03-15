// UNIVR-ARFA 
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.*;
import controller.*;

public final class ChessBoard extends JFrame {
	private ChessModel chessModel;
	private Controller chessController;	
	private JPanel panel;
	
	public final static int BOARD_BORDER = 3;
	public final static int BOARD_MIN_SIZE_X = 360;
	public final static int BOARD_MIN_SIZE_Y = 406;
	public final static int BOARD_SIZE_X = 560;
	public final static int BOARD_SIZE_Y = 606;
	public final static String EVEN_COLOR = "#facfa2";
	public final static String ODD_COLOR = "#d18a48";
	public final static String WAITING_MOVE_COLOR = "#f3ff8a";
	public final static String EVEN_IS_DOMAIN_COLOR = "#7ce69d";
	public final static String ODD_IS_DOMAIN_COLOR = "#76db96";
	public final static String EVEN_IS_CAPTURE_DOMAIN_COLOR = "#ffa8a8";
	public final static String ODD_IS_CAPTURE_DOMAIN_COLOR = "#ed9f9f";
	

	public static void main(String[] args) {
        new ChessBoard().setVisible(true);
	}
	
	public ChessBoard() {
		super("Chess");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onClose(); }
		});
		setMinimumSize(new Dimension(BOARD_MIN_SIZE_X, BOARD_MIN_SIZE_Y));
		setSize(new Dimension(BOARD_SIZE_X, BOARD_SIZE_Y));
		setLocationRelativeTo(null);
		chessModel = new ChessModel();
		chessController = new ChessController(chessModel);
		initChessBoard(true);
		initMenu();
		setTitle("Chess (" + chessModel.getStatus().toString().toLowerCase().replace('_', ' ') + ")");
	}
	
	public PieceType pawnPromotionDialog() {
		Object[] possibilities = { "Bishop", "Knight", "Queen", "Rook" };
		String promoted = (String)JOptionPane.showInputDialog(this, "Choose a piece to promote pawn with:", "Pawn promotion", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Queen");
		if (promoted != null)
			return PieceType.valueOf(promoted.toUpperCase());
		else
			return PieceType.QUEEN;
	}
	
	public void signalCheck() {
		JOptionPane.showMessageDialog(null, chessModel.getStatus() == Status.TURN_BLACK ?
			"Black king is under check!" : "White king is under check!", 
			"King under check", JOptionPane.WARNING_MESSAGE
		);
	}
	
	public void signalCheckmate() {
		JOptionPane.showMessageDialog(null, chessModel.getStatus() == Status.WON_WHITE ?
			"Checkmate!\nWhite player won the match." : "Checkmate!\nBlack player won the match.", 
			"Checkmate", JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	public void designChessBoard(boolean resetColors) {
		remove(panel);
		initChessBoard(resetColors);
		revalidate();
	}
	
	public void designChessBoard() {
		designChessBoard(false);
	}
	
	private void initChessBoard(boolean resetColors) {
		panel = new JPanel(new GridLayout(8, 8)) { public final Dimension getPreferredSize() {
			if (getParent().getWidth() > getParent().getHeight())
				return new Dimension(getParent().getHeight(), getParent().getHeight());
			else
				return new Dimension(getParent().getWidth(), getParent().getWidth());
		}};
		panel.setBorder(BorderFactory.createEmptyBorder(BOARD_BORDER, BOARD_BORDER, BOARD_BORDER, BOARD_BORDER));
		int i = 0;
		for (Piece[] pieces : chessModel.getPieces()) {
			int j = 0;
			for (Piece piece : pieces) {
				if ((i + j) % 2 == 0)
					if (piece.isCaptureDomain() && !resetColors)
						piece.setBackground(Color.decode(EVEN_IS_CAPTURE_DOMAIN_COLOR));
					else if (piece.isWaitingMove() && !resetColors)
						piece.setBackground(Color.decode(WAITING_MOVE_COLOR));
					else if (piece.isDomain() && !resetColors)
						piece.setBackground(Color.decode(EVEN_IS_DOMAIN_COLOR));
					else
						piece.setBackground(Color.decode(EVEN_COLOR));
				else
					if (piece.isCaptureDomain() && !resetColors)
						piece.setBackground(Color.decode(ODD_IS_CAPTURE_DOMAIN_COLOR));
					else if (piece.isWaitingMove() && !resetColors)
						piece.setBackground(Color.decode(WAITING_MOVE_COLOR));
					else if (piece.isDomain() && !resetColors)
						piece.setBackground(Color.decode(ODD_IS_DOMAIN_COLOR));
					else
						piece.setBackground(Color.decode(ODD_COLOR));
				final Point chessBoardLocation = new Point(i, j);
				final ChessBoard caller = this;
				if (piece.getActionListeners().length > 0)
					piece.removeActionListener(piece.getActionListeners()[0]);
				piece.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				        chessController.onClick(caller, chessBoardLocation);
					}
				});
				piece.setFocusable(false);
				panel.add(piece);
				j++;
			}
			i++;
		}
		setLayout(new GridBagLayout());
		add(panel, new GridBagConstraints());
	}
	
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New match", KeyEvent.VK_M);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.SHIFT_MASK));
		menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (JOptionPane.showConfirmDialog(
            			null, 
            			"Would you like to start a new match?", 
            			"Start a new match", 
            			JOptionPane.YES_NO_OPTION, 
            			JOptionPane.QUESTION_MESSAGE
            		) == JOptionPane.YES_OPTION)
            	{
                	chessModel.resetModel();
                	designChessBoard(true);
            	}
            }
        });
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.SHIFT_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { onClose();	}
		});
		menu.add(menuItem);
		
		setJMenuBar(menuBar);
	}
	
	private void onClose() {
		if (JOptionPane.showConfirmDialog(null, 
				"Are you sure you want to exit chess?", "Exit chess",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
			) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}