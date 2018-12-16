package InternationalChess;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import AI.Move;
import ChessUtility.*;

/*represent a KnightPiece*/
public class IKnightPiece extends Piece{
  private ChessBoard chessBoard = getChessBoard();
  /*create a KnightPiece*/
  public IKnightPiece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    if((Math.abs(x - this.getRow()) == 2 && Math.abs(y - this.getColumn()) == 1) || (Math.abs(x - this.getRow()) == 1 && Math.abs(y - this.getColumn()) == 2)){
      return true;
    }
    else{
      return false;
    }
  }
  
  
  
  
  
  
  
  
  
  /* ----------------------------AI PArt--------------------------------*/
  public IKnightPiece getClone(Piece[][] s){
	    Color newc = getColor();
	    String news = getLabel();
	    IChess.Side newsi = getSide();
	    return new IKnightPiece(new ChessBoard(s),newc,news,newsi,null);
	  }
  
  
  public ArrayList<Move> getMoves(){
	    ArrayList<Move> moves = new ArrayList<Move>();

	    if(chessBoard.hasPiece(this.getRow() + 2,this.getColumn() + 1)){
	      if(isLegalCaptureMove(this.getRow() + 2,this.getColumn() + 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+2, this.getColumn() + 1, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()+2,this.getColumn() + 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+2, this.getColumn() + 1, false));
	      }
	    }

	    if(chessBoard.hasPiece(this.getRow() - 2,this.getColumn() - 1)){
	      if(isLegalCaptureMove(this.getRow() - 2,this.getColumn() - 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-2, this.getColumn() - 1, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()-2,this.getColumn() - 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-2, this.getColumn() - 1, false));
	      }
	    }

	    if(chessBoard.hasPiece(this.getRow() + 2,this.getColumn() - 1)){
	      if(isLegalCaptureMove(this.getRow() + 2,this.getColumn() - 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+2, this.getColumn() - 1, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()+2,this.getColumn() - 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+2, this.getColumn() - 1, false));
	      }
	    }

	    if(chessBoard.hasPiece(this.getRow() - 2,this.getColumn() + 1)){
	      if(isLegalCaptureMove(this.getRow() - 2,this.getColumn() + 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-2, this.getColumn() + 1, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()-2,this.getColumn() + 1)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-2, this.getColumn() + 1, false));
	      }
	    }

	    if(chessBoard.hasPiece(this.getRow() - 1,this.getColumn() + 2)){
	      if(isLegalCaptureMove(this.getRow() - 1,this.getColumn() + 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() + 2, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()-1,this.getColumn() + 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() + 2, false));
	      }
	    }


	    if(chessBoard.hasPiece(this.getRow() + 1 ,this.getColumn() - 2)){
	      if(isLegalCaptureMove(this.getRow() + 1,this.getColumn() - 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() - 2, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()+1,this.getColumn() - 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() - 2, false));
	      }
	    }

	    if(chessBoard.hasPiece(this.getRow() - 1,this.getColumn() - 2)){
	      if(isLegalCaptureMove(this.getRow() - 1,this.getColumn() - 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() - 2, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()-1,this.getColumn() - 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() - 2, false));
	      }
	    }

	    if(chessBoard.hasPiece(this.getRow() + 1,this.getColumn() + 2)){
	      if(isLegalCaptureMove(this.getRow() + 1,this.getColumn() + 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() + 2, false));
	      }
	    }
	    else{
	      if(isLegalNonCaptureMove(this.getRow()+1,this.getColumn() + 2)){
	        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() + 2, false));
	      }
	    }

	    return moves;
	  }
}
    