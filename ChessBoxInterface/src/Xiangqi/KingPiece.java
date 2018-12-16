package Xiangqi;
import java.awt.*;
import javax.swing.*;
import ChessUtility.*;
/*represent a KingPiece*/
public class KingPiece extends Piece{
  /*create a KingPiece*/
  public KingPiece(ChessBoard board, Color color, String label, Xiangqi.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    /*store the result*/
    boolean result = true;
    
    if(this.getSide()==Xiangqi.Side.NORTH){
      if(x>2||y<3||y>5){
        result=false;
      }
      else{
        if((this.getRow()==x&&Math.abs(this.getColumn()-y)==1)||(this.getColumn()==y&&Math.abs(this.getRow()-x)==1)){
          result=true;
        }
        else{
          result=false;
        }
      }
    }
 
    else{
      if(x<7||y<3||y>5){
        result=false;
      }
      else{
        if((this.getRow()==x&&Math.abs(this.getColumn()-y)==1)||(this.getColumn()==y&&Math.abs(this.getRow()-x)==1)){
          result=true;
        }
        else{
          result=false;
        }
      }
    }
    return result;
  }
  
}