package Shogi;
/*represent a ShogiPawnPiece*/
import java.awt.*;
import ChessUtility.*;
import javax.swing.*;
public class ShogiPawnPiece extends Piece{
  /*check whether this pawn piece has evolved*/
  private boolean isEvolved = false;
  
  /*create a ShogiPawnPiece*/
  public ShogiPawnPiece(ChessBoard board, Color color, String label, Shogi.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    boolean result=false;
    if(isEvolved==false){
      if(this.getSide()==Shogi.Side.NORTH){
        if(x-this.getRow()==1&&y==this.getColumn()){
          result=true;
        }
      }
      else{
        if(x-this.getRow()==-1&&y==this.getColumn()){
          result=true;
        }
      }
    }
    else{
      if(this.getSide()==Shogi.Side.NORTH){
        if((x-this.getRow()==1&&y==this.getColumn())||(x==this.getRow()&&Math.abs(y-this.getColumn())==1)){
          result=true;
        }
      }
      else{
        if((x-this.getRow()==-1&&y==this.getColumn())||(x==this.getRow()&&Math.abs(y-this.getColumn())==1)){
          result=true;
        }
      }
    }
    return result;
  }
  
  /*check whether the pawn piece has crossed middle of board*/
  @Override
  public void moveDone(){
    if(this.getSide()==Shogi.Side.NORTH&&this.getRow()==5){
      isEvolved=true;
    }
    
    else if(this.getSide()==Shogi.Side.SOUTH&&this.getRow()==4){
      isEvolved=true;
    }
  }
    
  }