package Jungle;
import java.awt.Color;

import javax.swing.Icon;

public class JungleElephantPiece extends JunglePiece{
	/* create a Elephant Piece */
	public JungleElephantPiece(JungleChessBoard game, Color color, String label, Jungle.Side side, Icon icon) {
		super(game, color, label, side, icon);
		super.size = 7;
	}
	
	/* check whether the piece can move to input position
	 * while there is an empty square*/
	public boolean isLegalNonCaptureMove(int x, int y) {
		return this.isLegalNonRiverMove(x, y);
	}

}
