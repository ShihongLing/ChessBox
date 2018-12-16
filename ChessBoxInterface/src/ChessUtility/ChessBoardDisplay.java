package ChessUtility;
import javax.swing.*;

/* a interface used to display the board*/
public interface ChessBoardDisplay{
  /*method stubs for displayEmptySquare*/
  public void displayEmptySquare(JButton j, int x, int y);
  
  /*method stubs for displayFilledSquare*/
  public void displayFilledSquare(JButton j, int x, int y,Piece piece );
  
  /*method stubs for highlightSquare*/
  public void highlightSquare(boolean firstClick, JButton j, int x, int y, Piece piece);
  
}