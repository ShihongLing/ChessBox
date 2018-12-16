package InternationalChess;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import AI.Move;
import ChessUtility.*;
/*represent a RookPiece*/
public class CastlePiece extends Piece{
  private ChessBoard chessBoard = getChessBoard();
  private boolean isMoved = false;
  private boolean isShifted = false;
  /*create a RookPiece*/
  public CastlePiece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    super(board, color, label, side, icon);
  }

  public boolean isMoved(){
      return isMoved;
  }
  
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    boolean reachable = false;
    boolean hasObstacle = false;
    /*store the result*/
    if(x != this.getRow() || y != this.getColumn()) {
      if ((Math.abs(this.getRow() - x) > 0 && y == this.getColumn()) || (Math.abs(this.getColumn() - y) > 0 && x == this.getRow())) {
        reachable = true;
        if(Math.abs(this.getRow() - x) > 0){
          int i = this.getRow();
          int stepx = (x - i) / Math.abs(x - i);
          while (i != x && !hasObstacle) {
            if (chessBoard.hasPiece(i + stepx, y) && (i + stepx != x)) {
              hasObstacle = true;
            } else {
              i = stepx + i;
            }
          }
        }
        else{
          int j = this.getColumn();
          int stepy = (y - j) / Math.abs(y - j);
          while (j != y && !hasObstacle) {
            if (chessBoard.hasPiece(x, j + stepy) && j + stepy != y) {
              hasObstacle = true;
            } else {
              j = stepy + j;
            }
          }
        }

      } else {
        reachable = false;
      }
    }

    return !hasObstacle && reachable;
  }
  
  
  
  
  
  
  
  
  
  
  /* ----------------------------AI PArt--------------------------------*/
  public CastlePiece getClone(Piece[][] s){
	    Color newc = getColor();
	    String news = getLabel();
	    IChess.Side newsi = getSide();
	    return new CastlePiece(new ChessBoard(s),newc,news,newsi,null);
	  }

  public ArrayList<Move> getMoves(){
	    ArrayList<Move> moves = new ArrayList<Move>();
	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow(),this.getColumn() + i)){
	        if(isLegalCaptureMove(this.getRow(),this.getColumn() + i)){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn() + i, false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow(), this.getColumn() + i)) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn() + i, false));
	        }
	      }
	    }

	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() ,this.getColumn() - i)){
	        if(isLegalCaptureMove(this.getRow() ,this.getColumn() - i)){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn() - i, false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() , this.getColumn() - i)) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() , this.getColumn() - i, false));
	        }
	      }
	    }

	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() + i,this.getColumn() )){
	        if(isLegalCaptureMove(this.getRow() + i,this.getColumn() )){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + i, this.getColumn(), false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() + i, this.getColumn() )) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + i, this.getColumn() , false));
	        }
	      }
	    }

	    for(int i = 1; i < 8; i++) {
	      if(chessBoard.hasPiece(this.getRow() - i,this.getColumn() )){
	        if(isLegalCaptureMove(this.getRow() - i,this.getColumn() )){
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - i, this.getColumn() , false));
	        }
	      }
	      else {
	        if (isLegalNonCaptureMove(this.getRow() - i, this.getColumn() )) {
	          moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - i, this.getColumn() , false));
	        }
	      }
	    }

	    return moves;
	  }
  
  
  
  
  
}
      
    