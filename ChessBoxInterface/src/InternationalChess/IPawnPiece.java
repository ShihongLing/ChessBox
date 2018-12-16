package InternationalChess;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import AI.Move;
import ChessUtility.*;
/*represent a PawnPiece*/
public class IPawnPiece extends Piece{
  /*check whether this pawn piece has evolved*/
  private boolean isEvolved = false;
  private ChessBoard chessboard = getChessBoard();
  /*create a PawnPiece*/
  public IPawnPiece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    super(board, color, label, side, icon);
  }
  
  /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    boolean result=false;
      if(this.getSide()== IChess.Side.NORTH){
              if(this.getRow() == 3 && chessboard.hasPiece(3,y) && chessboard.getPiece(3,y).getSide() != IChess.Side.NORTH&& Math.abs(this.getColumn() - y) == 1 && x - this.getRow() == 1){
                  this.getChessBoard().removePiece(3,y);
                  result = true;
              }
              else {
                  if ((x - this.getRow() == 1 || ((x == 3) && x - this.getRow() == 2)) && y == this.getColumn()) {
                      result = true;
                  }
              }
      }
      else{
              if(this.getRow() == 4 && chessboard.hasPiece(4,y) && chessboard.getPiece(4,y).getSide() != IChess.Side.SOUTH && Math.abs(this.getColumn() - y) == 1 && x - this.getRow() == -1){
                  this.getChessBoard().removePiece(4,y);
                  result = true;
              }
              else{
                  if ((x - this.getRow() == -1 || ((x == 4) && x - this.getRow() == -2)) && y == this.getColumn()) {
                      result = true;
                  }
              }
      }

    return result;
  }
  
  /*check whether the pawn piece has crossed middle of board*/
  @Override
  public void moveDone(){
    if(this.getSide()== IChess.Side.NORTH&&this.getRow()==7 || this.getSide()== IChess.Side.SOUTH&&this.getRow()==0){
        String text = JOptionPane.showInputDialog("Type which piece to promote to", "(input Q,B,C or K):");
        promoted(text,this.getSide(),this.getRow(),this.getColumn());
    }
  }


    public void promoted(String target, IChess.Side s,int x,int y){
      Color c = Color.WHITE;
      if(s == IChess.Side.NORTH){
          c = Color.RED;
      }
      chessboard.removePiece(x,y);
      if(target.contains("q") || target.contains("Q")){
          chessboard.addPiece(new QueenPiece(chessboard,c,"Q", s,null),x,y);
      }
      else if(target.contains("b") || target.contains("B")){
          chessboard.addPiece(new BishopPiece(chessboard,c,"B", s,null),x,y);
      }
      else if(target.contains("c") || target.contains("C")){
          chessboard.addPiece(new CastlePiece(chessboard,c, "C", s,null),x,y);
      }
      else{
          chessboard.addPiece(new IKnightPiece(chessboard,c,"K", s,null), x,y);
      }
  }

    @Override
    public boolean isLegalCaptureMove(int x, int y) {
      if(this.getSide() == IChess.Side.NORTH) {
          if (this.getChessBoard().getPiece(x, y).getSide() != this.getSide() && x - this.getRow() == 1 && Math.abs(y - this.getColumn()) == 1) {
              return true;
          } else {
              return false;
          }
      }
      else{
          if (this.getChessBoard().getPiece(x, y).getSide() != this.getSide() && x - this.getRow() == -1 && Math.abs(y - this.getColumn()) == 1) {
              return true;
          } else {
              return false;
          }
      }
    }
    
    
    
    
    
    
    
    
    /* ----------------------------AI PArt--------------------------------*/
    public IPawnPiece getClone(Piece[][] s){
        Color newc = getColor();
        String news = getLabel();
        IChess.Side newsi = getSide();
        return new IPawnPiece(new ChessBoard(s),newc,news,newsi,null);
    }
    
    
    
    public ArrayList<Move> getMoves(){
        ArrayList<Move> moves = new ArrayList<Move>();

        if(this.getSide() == IChess.Side.NORTH){
            if(this.getRow() == 1){
                if(!chessboard.hasPiece(this.getRow() + 2,this.getColumn())){
                    if(isLegalNonCaptureMove(this.getRow() + 2,this.getColumn())){
                        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 2, this.getColumn(), false));
                    }

                    if(isLegalNonCaptureMove(this.getRow() + 1,this.getColumn())){
                        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 1, this.getColumn(), false));
                    }
                }
            }
            else{
                if(!chessboard.hasPiece(this.getRow() + 1,this.getColumn())){
                    if(isLegalNonCaptureMove(this.getRow() + 1,this.getColumn())){
                        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 1, this.getColumn(), false));
                    }
                }
            }

            if(chessboard.hasPiece(this.getRow()+1,this.getColumn() - 1)){
                if(isLegalCaptureMove(this.getRow() + 1,this.getColumn()-1)){
                    moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 1, this.getColumn()-1, false));
                }
            }

            if(chessboard.hasPiece(this.getRow()+1,this.getColumn() + 1)){
                if(isLegalCaptureMove(this.getRow() + 1,this.getColumn() + 1)){
                    moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 1, this.getColumn()+1, false));
                }
            }
        }
        else{
            if(this.getRow() == 6){
                if(!chessboard.hasPiece(this.getRow() - 2,this.getColumn())){
                    if(isLegalNonCaptureMove(this.getRow() - 2,this.getColumn())){
                        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - 2, this.getColumn(), false));
                    }
                }
            }
            else{
                if(!chessboard.hasPiece(this.getRow() - 1,this.getColumn())){
                    if(isLegalNonCaptureMove(this.getRow() - 1,this.getColumn())){
                        moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - 1, this.getColumn(), false));
                    }
                }
            }

            if(chessboard.hasPiece(this.getRow()-1,this.getColumn() - 1)){
                if(isLegalCaptureMove(this.getRow() - 1,this.getColumn()-1)){
                    moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - 1, this.getColumn()-1, false));
                }
            }

            if(chessboard.hasPiece(this.getRow()-1,this.getColumn() + 1)){
                if(isLegalCaptureMove(this.getRow() - 1,this.getColumn() + 1)){
                    moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() - 1, this.getColumn()+1, false));
                }
            }

        }
        return moves;
    }
}