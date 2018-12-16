package Shogi;
import java.awt.*;
import javax.swing.*;
import ChessUtility.*;
/*represent a ShogiRookPiece*/
public class ShogiRookPiece extends Piece{
  /*create a ShogiRookPiece*/
  public ShogiRookPiece(ChessBoard board, Color color, String label, Shogi.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
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
  
}
      
    