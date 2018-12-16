package InternationalChess;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import AI.Move;
import ChessUtility.*;
/*represent a GuardPiece*/
public class QueenPiece extends Piece{
  private ChessBoard chessBoard = getChessBoard();
  /*create a GuardPiece*/
  public QueenPiece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    if(x != this.getRow() || y != this.getColumn()) {
      if (canMoveHV(x, y)) {
        return true;
      } else if (canMoveCross(x, y)) {
        return true;
      } else {
        return false;
      }
    }
    else{
      return false;
    }
  }

  public boolean canMoveHV(int x, int y){
    /*store the result*/
    boolean result = true;
    if(x==getRow()&&y!=getColumn()){
      int min = 0;
      int max = 0;
      if(y>getColumn()){
        max = y;
        min = getColumn();
      }
      else{
        max = getColumn();
        min = y;
      }

      /*check whether there are pieces between this piece and the input position*/
      for(int a=min+1;a<max;a++){
        if(this.getChessBoard().hasPiece(x,a)==true){
          result = false;
        }
      }
    }
    else if(x!=getRow()&&y==getColumn()){
      int min = 0;
      int max = 0;
      if(x>getRow()){
        max = x;
        min = getRow();
      }
      else{
        max = getRow();
        min = x;
      }

      /*check whether there are pieces between this piece and the input position*/
      for(int a=min+1;a<max;a++){
        if(this.getChessBoard().hasPiece(a,y)==true){
          result = false;
        }
      }
    }
    else{
      result = false;
    }
    return result;
  }

  public boolean canMoveCross(int x, int y){
    boolean reachable = false;
    boolean hasObstacle = false;
    /*store the result*/

    if(Math.abs(this.getRow() - x) == Math.abs(this.getColumn() - y)) {
      reachable = true;
      int i = this.getRow();
      int j = this.getColumn();
      if(x - i != 0 && y - j != 0) {
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
      }
    }
    else{
      reachable = false;
    }
    return !hasObstacle && reachable;
  }
  
  
  
  
  
  
  
  
  /* ----------------------------AI PArt--------------------------------*/
  public QueenPiece getClone(Piece[][] s){
	    Color newc = getColor();
	    String news = getLabel();
	    IChess.Side newsi = getSide();
	    return new QueenPiece(new ChessBoard(s),newc,news,newsi,null);
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
        