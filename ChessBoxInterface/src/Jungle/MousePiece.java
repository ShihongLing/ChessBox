package Jungle;
import java.awt.Color;

import javax.swing.Icon;

public class MousePiece extends JunglePiece{
	/* create a mouse Piece */
	public MousePiece(JungleChessBoard game, Color color, String label, Jungle.Side side, Icon icon) {
		super(game, color, label, side, icon);
	}

	/* check whether the piece can move to input position
	 * while there is an empty square*/
	public boolean isLegalNonCaptureMove(int x, int y) {
		  if (!this.getChessBoard().isBase(x, y)&&((Math.abs(x - this.getRow()) == 1 && y == this.getColumn()) || (x == this.getRow() && Math.abs(y - this.getColumn()) == 1))) {
				return true;
			} 
			return false;
	}

    
  }