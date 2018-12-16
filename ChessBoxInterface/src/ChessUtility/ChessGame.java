package ChessUtility;

import java.util.ArrayList;

import AI.Move;
import InternationalChess.IChess;

/*a interface represent all chess game*/
public interface ChessGame{
  
  /*method stubs for legalPieceToPlace*/
  public boolean legalPieceToPlace(Piece piece);
  
  /*method stubs for makeMove*/
  public boolean makeMove(Piece piece,int x, int y);
  
  /* a inner class to define two different player*/
  public enum Side { NORTH, SOUTH };
  
  /*-------------------------AI Part---------------------------*/
  
  public boolean makeMoveAI(int x0,int y0,int x1,int y1);
  
  public ArrayList<Move> getMoves(IChess.Side currentSide);
  
  public Piece[][] getState();
  
  public String getGameType();
}