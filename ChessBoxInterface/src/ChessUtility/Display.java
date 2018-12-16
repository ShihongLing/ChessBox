package ChessUtility;
import javax.swing.*;
import java.awt.*;

/*a class used to diaplay the board and every piece*/
public class Display implements ChessBoardDisplay{
  
  /*display the square when there is nothing*/
  @Override
  public void displayEmptySquare(JButton j, int x, int y){
    j.setIcon(null);
    j.setBackground(Color.GRAY);
    j.setText("");
  }
  
  /*display the square when there is a piece*/
  @Override
  public void displayFilledSquare(JButton j, int x, int y ,Piece piece ){
    j.setIcon(piece.getIcon());
    j.setBackground(piece.getColor());
    j.setText(piece.getLabel());
  }
  
  /*display when the piece in this square is clicked*/
  @Override
  public void highlightSquare(boolean firstClick, JButton j, int x, int y ,Piece piece){
    if(firstClick==true){
      j.setBackground(Color.YELLOW);
    }
    else if(firstClick==false&&piece==null){
      this.displayEmptySquare(j,x,y);
    }
    else if(firstClick==false&&piece!=null){
      this.displayFilledSquare(j,x,y,piece);
    }
  }
}
    