package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import view.*;

public interface Controller {
	void onClick(ChessBoard caller, Point clicked);
}
