package InternationalChess;
import java.awt.*;
import java.util.ArrayList;

import AI.*;
import javax.swing.*;
import ChessUtility.*;
/*represent a KingPiece*/
public class IKingPiece extends Piece{
    private boolean isMoved = false;
    private boolean isShifted = false;
    private ChessBoard chessBoard = getChessBoard();
  /*create a KingPiece*/
  public IKingPiece(ChessBoard board, Color color, String label, IChess.Side side, Icon icon){
    super(board, color, label, side, icon);
  }

    @Override
    public void moveDone() {
        isMoved = true;
        if(isShifted){
            IChess.Side s = this.getSide();
            Color c = Color.WHITE;
            if(s == IChess.Side.NORTH){
                c = Color.RED;
            }
            if(this.getColumn() == 0){
                chessBoard.addPiece(new IKingPiece(chessBoard,c,"Ki", s,null),this.getRow(),2);
                chessBoard.addPiece(new CastlePiece(chessBoard,c, "C", s,null),this.getRow(),3);
                chessBoard.removePiece(this.getRow(),this.getColumn());
            }
            else{
                chessBoard.addPiece(new IKingPiece(chessBoard,c,"Ki", s,null),this.getRow(),6);
                chessBoard.addPiece(new CastlePiece(chessBoard,c, "C", s,null),this.getRow(),5);
                chessBoard.removePiece(this.getRow(),this.getColumn());
            }
            isShifted = false;
        }
    }

    /*check whether the piece can move to input position
   * while there is an empty square*/
  public boolean isLegalNonCaptureMove(int x, int y){
    /*store the result*/
      if(x == this.getRow() && y == this.getColumn()){
          return false;
      }
      else {
          if (Math.abs(this.getRow() - x) <= 1 && Math.abs(this.getColumn() - y) <= 1) {
              return true;
          } else {
              return false;
          }
      }
  }

  public boolean isMoved(){
      return isMoved;
  }

    public boolean isLegalCaptureMove(int x, int y) {
        if(this.getSide() == IChess.Side.NORTH) {
            if (chessBoard.getPiece(x, y).getSide() != this.getSide() && Math.abs(x - this.getRow()) <= 1 && Math.abs(y - this.getColumn()) <= 1) {
                return true;
            } else {
                if (chessBoard.getPiece(x,y).getLabel().equals("C") && chessBoard.getPiece(x, y).getSide() == this.getSide()){
                    CastlePiece target = (CastlePiece)this.getChessBoard().getPiece(x,y);
                    if(this.isMoved() || target.isMoved()){
                        return false;
                    }
                    else{
                        boolean shiftable = true;
                        if(this.getColumn() > y){
                            while(y != getColumn() - 1 && shiftable){
                                y = y + 1;
                                if(chessBoard.hasPiece(0,y)){
                                    shiftable = false;
                                }
                            }

                        }
                        else{
                            while(y != getColumn() + 1 && shiftable){
                                y = y - 1;
                                if(chessBoard.hasPiece(0,y)){
                                    shiftable = false;
                                }
                            }

                        }

                        if(shiftable){
                            isShifted = true;
                        }
                        return shiftable;
                    }
                }
                else{
                    if (chessBoard.getPiece(x, y).getSide() != this.getSide() && Math.abs(x - this.getRow()) <= 1 && Math.abs(y - this.getColumn()) <= 1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        else{
            if (chessBoard.getPiece(x, y).getSide() != this.getSide() && Math.abs(x - this.getRow()) <= 1 && Math.abs(y - this.getColumn()) <= 1) {
                return true;
            } else {
                if (chessBoard.getPiece(x,y).getLabel().equals("C") && chessBoard.getPiece(x, y).getSide() == this.getSide()){
                    CastlePiece target = (CastlePiece)this.getChessBoard().getPiece(x,y);
                    if(this.isMoved() || target.isMoved()){
                        return false;
                    }
                    else{
                        boolean shiftable = true;
                        if(this.getColumn() > y){
                            while(y != getColumn() - 1 && shiftable){
                                y = y + 1;
                                if(chessBoard.hasPiece(7,y)){
                                    shiftable = false;
                                }
                            }
                        }
                        else{
                            while(y != getColumn() + 1 && shiftable){
                                y = y - 1;
                                if(chessBoard.hasPiece(7,y)){
                                    shiftable = false;
                                }
                            }
                        }

                        if(shiftable){
                            isShifted = true;
                        }
                        return shiftable;
                    }
                }
                else{
                    if (chessBoard.getPiece(x, y).getSide() != this.getSide() && Math.abs(x - this.getRow()) <= 1 && Math.abs(y - this.getColumn()) <= 1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
    }
    
    
    /*------------------------AI part-----------------------------*/
    public IKingPiece getClone(Piece[][] s){
        Color newc = getColor();
        String news = getLabel();
        IChess.Side newsi = getSide();
        return new IKingPiece(new ChessBoard(s),newc,news,newsi,null);
    }
    
    public ArrayList<Move> getMoves(){
        ArrayList<Move> moves = new ArrayList<Move>();
        if(chessBoard.hasPiece(this.getRow(),this.getColumn() + 1)){
            if(isLegalCaptureMove(this.getRow(),this.getColumn() + 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn() + 1, false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow(),this.getColumn() + 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn() + 1, false));
            }
        }

        if(chessBoard.hasPiece(this.getRow()+1,this.getColumn() + 1)){
            if(isLegalCaptureMove(this.getRow()+1,this.getColumn() + 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() + 1, false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow()+1,this.getColumn() + 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() + 1, false));
            }
        }

        if(chessBoard.hasPiece(this.getRow()+1,this.getColumn())){
            if(isLegalCaptureMove(this.getRow()+1,this.getColumn() )){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 1, this.getColumn() , false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow()+1,this.getColumn() )){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow() + 1, this.getColumn() , false));
            }
        }

        if(chessBoard.hasPiece(this.getRow(),this.getColumn() - 1 )){
            if(isLegalCaptureMove(this.getRow(),this.getColumn() -1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn() - 1 , false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow(),this.getColumn() -1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow(), this.getColumn()-1 , false));
            }
        }

        if(chessBoard.hasPiece(this.getRow()-1,this.getColumn() )){
            if(isLegalCaptureMove(this.getRow()-1,this.getColumn() )){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() , false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow()-1,this.getColumn() )){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() , false));
            }
        }

        if(chessBoard.hasPiece(this.getRow()+1,this.getColumn() -1)){
            if(isLegalCaptureMove(this.getRow()+1,this.getColumn() - 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() - 1, false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow() +1,this.getColumn() - 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()+1, this.getColumn() -1, false));
            }
        }

        if(chessBoard.hasPiece(this.getRow()-1,this.getColumn() +1)){
            if(isLegalCaptureMove(this.getRow()-1,this.getColumn() + 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() + 1, false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow() -1,this.getColumn() + 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() +1, false));
            }
        }

        if(chessBoard.hasPiece(this.getRow()-1,this.getColumn() -1)){
            if(isLegalCaptureMove(this.getRow()-1,this.getColumn() - 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() - 1, false));
            }
        }
        else{
            if(isLegalNonCaptureMove(this.getRow() -1,this.getColumn() - 1)){
                moves.add(new Move(this.getRow(), this.getColumn(), this.getRow()-1, this.getColumn() -1, false));
            }
        }

        if(this.getSide() == IChess.Side.NORTH){
            if(chessBoard.hasPiece(0,7)) {
                if (chessBoard.getPiece(0, 7).getLabel().equals("C")) {
                    if(chessBoard.getPiece(0, 7).getSide() == this.getSide()) {
                        if (isLegalCaptureMove(0, 7)) {
                            moves.add(new Move(this.getRow(), this.getColumn(), 0, 7, true));
                        }
                    }
                }
            }

            if(chessBoard.hasPiece(0,0)) {
                if (chessBoard.getPiece(0, 0).getLabel().equals("C")) {
                    if(chessBoard.getPiece(0, 0).getSide() == this.getSide()) {
                        if (isLegalCaptureMove(0, 0)) {
                            moves.add(new Move(this.getRow(), this.getColumn(), 0, 0, true));
                        }
                    }
                }
            }

        }
        else{
            if(chessBoard.hasPiece(7,0)) {
                if (chessBoard.getPiece(7, 0).getLabel().equals("C")) {
                    if(chessBoard.getPiece(7, 0).getSide() == this.getSide()) {
                        if (isLegalCaptureMove(7, 0)) {
                            moves.add(new Move(this.getRow(), this.getColumn(), 7, 0, true));
                        }
                    }
                }
            }

            if(chessBoard.hasPiece(7,7)) {
                if (chessBoard.getPiece(7, 7).getLabel().equals("C")) {
                    if (isLegalCaptureMove(7, 7)) {
                        if(chessBoard.getPiece(7, 7).getSide() == this.getSide()) {
                            moves.add(new Move(this.getRow(), this.getColumn(), 7, 7, true));
                        }
                    }
                }
            }


        }
        return moves;
    }
}