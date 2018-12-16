package Jungle;

/*a interface represent all chess game*/
public interface JungleChessGame{
  
  /*method stubs for legalPieceToPlace*/
  public boolean legalPieceToPlace(JunglePiece piece);
  
  /*method stubs for makeMove*/
  public boolean makeMove(JunglePiece piece,int x, int y);
  
  /* a inner class to define two different player*/
  public enum Side { NORTH, SOUTH }
}