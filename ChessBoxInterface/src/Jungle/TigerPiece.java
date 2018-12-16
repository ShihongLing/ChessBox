package Jungle;
import java.awt.Color;

import javax.swing.Icon;

public class TigerPiece extends JunglePiece{
	/* create a tiger Piece */
	public TigerPiece(JungleChessBoard game, Color color, String label, Jungle.Side side, Icon icon) {
		super(game, color, label, side, icon);
		super.size = 5;
	}

	/* check whether the piece can move to input position
	 * while there is an empty square*/
	public boolean isLegalNonCaptureMove(int x, int y) {
		boolean allRiver = true;
		if (!this.getChessBoard().isRiver(x, y)&&this.getRow() == x && Math.abs(this.getColumn() - y)>1 ) {
			if(this.getColumn() < y) {
				for(int i = this.getColumn()+1; i < y; i++) {
					if(!this.getChessBoard().isRiver(x, i))
						allRiver = false;
				}
				return allRiver;
			}
			else {
				for(int i = y+1; i < this.getColumn(); i++) {
					if(!this.getChessBoard().isRiver(x, i))
						allRiver = false;
				}
				return allRiver;
			}
		}
		else if (!this.getChessBoard().isRiver(x, y)&&Math.abs(this.getRow() - x)>1 && this.getColumn() == y) {
			if(this.getRow() < x) {
				for(int i = this.getRow()+1; i < x; i++) {
					if(!this.getChessBoard().isRiver(i, y))
						allRiver = false;
				}
				return allRiver;
			}
			else {
				for(int i = x+1; i < this.getRow(); i++) {
					if(!this.getChessBoard().isRiver(i, y))
						allRiver = false;
				}
				return allRiver;
			}
		}
		return this.isLegalNonRiverMove(x, y);
	}

}
