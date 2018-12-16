package Xiangqi;
import java.awt.*;
import javax.swing.*;
import ChessUtility.*;
/*represent an ElephantPiece*/
public class ElephantPiece extends Piece{
  /*create an ElephantPiece*/
  public ElephantPiece(ChessBoard board, Color color, String label, Xiangqi.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    /*store the result*/
    boolean result = true;
    if(this.getSide()==Xiangqi.Side.NORTH){
      if(x>4){
        result = false;
      }
      else{
        if(x-this.getRow()==2&&y-this.getColumn()==2){
          if(this.getChessBoard().hasPiece(x-1,y-1)==false){
            result=true;
          }
          else{
            result=false;
          }
        }
        else if(x-this.getRow()==2&&y-this.getColumn()==-2){
          if(this.getChessBoard().hasPiece(x-1,y+1)==false){
          result=true;
          }
          else{
            result=false;
          }
        }
        else if(x-this.getRow()==-2&&y-this.getColumn()==-2){
          if(this.getChessBoard().hasPiece(x+1,y+1)==false){
          result=true;
          }
          else{
            result=false;
          }
        }
        else if(x-this.getRow()==-2&&y-this.getColumn()==2){
          if(this.getChessBoard().hasPiece(x+1,y-1)==false){
          result=true;
          }
          else{
            result=false;
          }
        }
        else{
          result=false;
        }
      }
    }
    else{
      if(x<5){
        result = false;
      }
      else{
        if(x-this.getRow()==2&&y-this.getColumn()==2){
          if(this.getChessBoard().hasPiece(x-1,y-1)==false){
            result=true;
          }
          else{
            result=false;
          }
        }
        else if(x-this.getRow()==2&&y-this.getColumn()==-2){
          if(this.getChessBoard().hasPiece(x-1,y+1)==false){
          result=true;
          }
          else{
           result=false;
          }
        }
        else if(x-this.getRow()==-2&&y-this.getColumn()==-2){
          if(this.getChessBoard().hasPiece(x+1,y+1)==false){
          result=true;
          }
          else{
            result=false;
          }
        }
        else if(x-this.getRow()==-2&&y-this.getColumn()==2){
          if(this.getChessBoard().hasPiece(x+1,y-1)==false){
          result=true;
          }
          else{
            result=false;
          }
        }
        else{
          result=false;
        }
      }
    }
    return result;
  }
  
}