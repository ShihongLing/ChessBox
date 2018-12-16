package ChessUtility;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import AI.*;
import InternationalChess.IChess;

/*an abstract class contain the same methods all knids of piece have*/
public abstract class Piece {
  /*store the board which the piece is in*/
  private ChessBoard board=null;
  /*store the color of piece*/
  private Color color= null;
  /*store the name of piece*/
  private String label = "";
  /*store the side of piece*/
  private IChess.Side side = null;
  /*store the icon of the piece*/
  private Icon icon = null;
  /*store the position of piece*/
  private int x=0;
  private int y=0;
  
  /*create a piece*/
  public Piece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    this.board = board;
    this.color = color;
    this.label = label;
    this.side = side;
    this.icon = icon;
  }
  
  /*get the board the piece is in*/
  public ChessBoard getChessBoard(){
    return board;
  }
  
  /*get the color the piece is*/
  public Color getColor(){
    return color;
  }
  
  /*get the name of the piece*/
  public String getLabel(){
    return label;
  }
  
  /*get the side of piece*/
  public IChess.Side getSide(){
    return side;
  }
  
  /*get the icon of piece*/
  public Icon getIcon(){
    return icon;
  }
  
  /*get the row of the piece*/
  public int getRow(){
    /*find the position of this piece*/
    for(int a=0; a<board.getRow(); a++){
      for(int b=0; b<board.getColumn();b++){
        if(this.equals(board.getPiece(a,b))==true){
          x = a;
        }
      }
    }
    return x;
  }
  
  /*get the column of the piece*/
  public int getColumn(){
    /*find the position of this piece*/
    for(int a=0; a<board.getRow(); a++){
      for(int b=0; b<board.getColumn();b++){
        if(this.equals(board.getPiece(a,b))==true){
          y = b;
        }
      }
    }
    return y;
  }
  
  /*call this method after this piece has been moved*/
  public void moveDone(){
  }
  
  /*check whether this piece can move to input position*/
  public boolean isLegalMove(int x, int y){
    if(this.getChessBoard().hasPiece(x,y)==false){
      return isLegalNonCaptureMove(x,y);
    }
    else{
      return isLegalCaptureMove(x,y);
    }
  }
  
  /*method stub for isLegalNonCaptureMove*/
  public abstract boolean isLegalNonCaptureMove(int x, int y);
  
  /*check whether this piece can move to the input position
   * while there is another piece there*/
  public boolean isLegalCaptureMove(int x, int y){
    if(this.isLegalNonCaptureMove(x,y)==true&&this.getChessBoard().getPiece(x,y).getSide()!=this.getSide()){
      return true;
    }
    else{
      return false;
    }
  }
  
  
  /* ----------------------------AI PArt--------------------------------*/
  public void setSide(IChess.Side side){
	    this.side = side;
  }
  
  public Piece getClone(Piece[][] s){
	    return null;
  }
  
  public void setBoard(ChessBoard board){
	    this.board = board;
  }

  public void setPos(int i, int j){
	    this.x = i;
	    this.y = j;
  }
	  
  public ArrayList<Move> getMoves(){
	    return new ArrayList<Move>();
  }
  
  
  
  
  
  
}
  
  
  