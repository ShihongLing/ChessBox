package Xiangqi;
import java.awt.*;
import javax.swing.*;
import ChessUtility.*;
  
/*represent a CannonPiece*/
public class CannonPiece extends RookPiece{
  
  /*create a CannonPiece*/
  public CannonPiece(ChessBoard board, Color color, String label, Xiangqi.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether this piece can move to the input position
   * while there is another piece there*/
  @Override
  public boolean isLegalCaptureMove(int x, int y){
  /*store the result*/
  boolean result = true;
  /*store the number of pieces between this piece and the input position*/
  int count = 0;
  if(x!=getRow()&&y==getColumn()){
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
    
    /*count the number of pieces between this piece and the input position*/
    for(int a=min+1;a<max;a++){
      if(this.getChessBoard().hasPiece(a,y)==true){
          count++;
      }
    }
    if(count!=1||this.getChessBoard().getPiece(x,y).getSide()==this.getSide()){
      result = false;
    }
  }
  else if(x==getRow()&&y!=getColumn()){
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
      
      /*count the number of pieces between this piece and the input position*/
      for(int a=min+1;a<max;a++){
        if(this.getChessBoard().hasPiece(x,a)==true){
          count++;
        }
      }
      if(count!=1||this.getChessBoard().getPiece(x,y).getSide()==this.getSide()){
        result = false;
      }
  }
  else{
    result = false;
  }
  return result;
  }
}