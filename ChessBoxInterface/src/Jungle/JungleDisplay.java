package Jungle;
import java.awt.Color;

import javax.swing.JButton;

public class JungleDisplay {
	
  public void displayRiverSquare(JButton j, int x, int y){
	    j.setIcon(null);
	    j.setBackground(Color.blue);
	    j.setText("");
  }
  
  public void displayBaseSquare(JButton j, int x, int y){
	  	j.setIcon(null);
	    j.setBackground(Color.black);
	    j.setText("Base");
  }
  
  public void displayTrapSquare(JButton j, int x, int y){
	  	j.setIcon(null);
	    j.setBackground(Color.green);
	    j.setText("Trap");
  }

/*display the square when there is nothing*/
public void displayEmptySquare(JButton j, int x, int y){
  j.setIcon(null);
  j.setBackground(Color.GRAY);
  j.setText("");
}

/*display the square when there is a piece*/
public void displayFilledSquare(JButton j, int x, int y ,JunglePiece piece ){
  j.setIcon(piece.getIcon());
  j.setBackground(piece.getColor());
  j.setText(piece.getLabel());
}

/*display when the piece in this square is clicked*/
public void highlightSquare(boolean firstClick, JButton j, int x, int y ,JunglePiece p){
  if(firstClick==true){
    j.setBackground(Color.YELLOW);
  }
  else if(firstClick==false&&p==null){
    this.displayEmptySquare(j,x,y);
  }
  else if(firstClick==false&&p!=null){
    this.displayFilledSquare(j,x,y,p);
  }
}


}
