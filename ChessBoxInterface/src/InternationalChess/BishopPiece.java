package InternationalChess;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import AI.Move;
import ChessUtility.*;

/*represent an ElephantPiece*/
public class BishopPiece extends Piece{
  private ChessBoard chessBoard = getChessBoard();
  /*create an ElephantPiece*/
  public BishopPiece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    boolean reachable = false;
    boolean hasObstacle = false;
    /*store the result*/
    if(x != this.getRow() || y != this.getColumn()) {
      if (Math.abs(this.getRow() - x) == Math.abs(this.getColumn() - y)) {
        reachable = true;
        int i = this.getRow();
        int j = this.getColumn();
        int stepx = (x - i) / Math.abs(x - i);
        int stepy = (y - j) / Math.abs(y - j);
        while (i != x && j != y && !hasObstacle) {
          if (chessBoard.hasPiece(i + stepx, j + stepy) && i + stepx != x && j + stepy != y) {
            hasObstacle = true;
          } else {
            i = stepx + i;
            j = stepy + j;
          }
        }
      } else {
        reachable = false;
      }
    }

    return !hasObstacle && reachable;
  }

  
  
  /* ----------------------------AI PArt--------------------------------*/
  public BishopPiece getClone(Piece[][] s){
	    Color newc = getColor();
	    String news = getLabel();
	    IChess.Side newsi = getSide();
	    return new BishopPiece(new ChessBoard(s),newc,news,newsi,null);
	  }

  
  public ArrayList<Move> getMoves(){
	    ArrayList<Move> moves = new ArrayList<Move>();
	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() + i,this.getColumn() + i)){
	        if(isLegalCaptureMove(this.getRow() + i,this.getColumn() + i)){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + i, this.getColumn() + i, false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() + i, this.getColumn() + i)) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + i, this.getColumn() + i, false));
	        }
	      }
	    }

	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() - i,this.getColumn() + i)){
	        if(isLegalCaptureMove(this.getRow() - i,this.getColumn() + i)){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - i, this.getColumn() + i, false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() - i, this.getColumn() + i)) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - i, this.getColumn() + i, false));
	        }
	      }
	    }

	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() + i,this.getColumn() - i)){
	        if(isLegalCaptureMove(this.getRow() + i,this.getColumn() - i)){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + i, this.getColumn() - i, false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() + i, this.getColumn() - i)) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + i, this.getColumn() - i, false));
	        }
	      }
	    }

	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() - i,this.getColumn() - i)){
	        if(isLegalCaptureMove(this.getRow() - i,this.getColumn() - i)){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - i, this.getColumn() - i, false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() - i, this.getColumn() - i)) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - i, this.getColumn() - i, false));
	        }
	      }
	    }

	    return moves;
	  }
}