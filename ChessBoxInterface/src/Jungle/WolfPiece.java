package Jungle;
import java.awt.Color;

import javax.swing.Icon;

public class WolfPiece extends JunglePiece{
	/* create a wolf Piece */
	public WolfPiece(JungleChessBoard game, Color color, String label, Jungle.Side side, Icon icon) {
		super(game, color, label, side, icon);
		super.size = 3;
	}
	
	/* check whether the piece can move to input position
	 * while there is an empty square*/
	public boolean isLegalNonCaptureMove(int x, int y) {
		return this.isLegalNonRiverMove(x, y);
	}

}
