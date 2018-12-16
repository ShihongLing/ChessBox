package Shogi;
/*represent a ShogiKnightPiece*/
import java.awt.*;
import ChessUtility.*;
import javax.swing.*;
public class ShogiKnightPiece extends Piece{
  /*create a ShogiKnightPiece*/
  public ShogiKnightPiece(ChessBoard board, Color color, String label, Shogi.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    boolean result=false;
    if(x-this.getRow()==2&&y-this.getColumn()==1){
      if(this.getChessBoard().hasPiece(x-1,y-1)==false){
        result=true;
      }
    }
    else if(x-this.getRow()==2&&y-this.getColumn()==-1){
      if(this.getChessBoard().hasPiece(x-1,y+1)==false){
        result=true;
      }
    }
    else if(x-this.getRow()==-2&&y-this.getColumn()==-1){
      if(this.getChessBoard().hasPiece(x+1,y+1)==false){
        result=true;
      }
    }
    else if(x-this.getRow()==-2&&y-this.getColumn()==1){
      if(this.getChessBoard().hasPiece(x+1,y-1)==false){
        result=true;
      }
    }
    
    if(x-this.getRow()==1&&y-this.getColumn()==2){
      if(this.getChessBoard().hasPiece(x-1,y-1)==false){
        result=true;
      }
    }
    else if(x-this.getRow()==-1&&y-this.getColumn()==2){
      if(this.getChessBoard().hasPiece(x+1,y-1)==false){
        result=true;
      }
    }
    else if(x-this.getRow()==-1&&y-this.getColumn()==-2){
      if(this.getChessBoard().hasPiece(x+1,y+1)==false){
        result=true;
      }
    }
    else if(x-this.getRow()==1&&y-this.getColumn()==-2){
      if(this.getChessBoard().hasPiece(x-1,y+1)==false){
        result=true;
      }
    }
    return result;
  }
}
    