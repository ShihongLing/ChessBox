package ChessUtility;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

/*this class is used to create a chess board*/
public class ChessBoard extends JFrame implements ActionListener{
  /*express whether this click is the first click*/
  private boolean firstClick = true;
  /*create a instance of ChessBoardDisply*/
  private ChessBoardDisplay chessDisplay = null;
  /*create a instance of ChessGame*/
  private ChessGame chessVersion = null;
  /*record the positions of first click and second click*/
  private int x0=0, y0=0, x1=0, x2=0, y1=0, y2=0;
  /*create a piece array*/
  private Piece[][] p = null;
  /*create a JButton array*/
  private JButton[][] j = null;
  /*record the rows of board*/
  private int rows = 0;
  /*recoed the columns of board*/
  private int columns = 0;
  
  /*this constructor is used to create a new chess board
   * it needs rows, columns, a ChessBoardDisply instance , a ChessGame instance*/
  public ChessBoard(int rows, int columns, ChessBoardDisplay chessDisplay, ChessGame chessVersion){
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    this.rows=rows;
    this.columns=columns;
    this.chessDisplay = chessDisplay;
    this.chessVersion = chessVersion;
    
    /*create a JPanel to store JButton*/
    JPanel board = new JPanel(new GridLayout(rows, columns));
    this.getContentPane().add(board,"Center");
    
    this.j=new JButton[rows][columns];
    
    /*add every JButton into right place in JPanel*/
    for(int x=0; x<j.length;x++){
      for(int y=0; y<j[x].length;y++){
        j[x][y] = new JButton();
        j[x][y].addActionListener(this);
        board.add(j[x][y]);
        chessDisplay.displayEmptySquare(j[x][y], x, y);
        
      }
    }
    
    this.setSize(500,500);
    //this.setVisible(true);  
    
    
    this.p= new Piece[rows][columns];
  
  }
  
  
  /*this method will work every time you click the button in a chess board*/
  @Override
  public void actionPerformed(ActionEvent e){
    
    /*store the JButton you've clicked*/
    JButton clicked = (JButton)e.getSource();
    
    /*store the position of first click*/
    for(int x=0; x<j.length;x++){
      for(int y=0; y<j[x].length;y++){
        if(clicked == j[x][y]){
          x0=x;
          y0=y;
        }
      }
    }
    if(firstClick==true&&hasPiece(x0, y0)==true){
      if(chessVersion.legalPieceToPlace(p[x0][y0])==true){
        x1=x0;
        y1=y0;
        chessDisplay.highlightSquare(firstClick, clicked, x1, y1, p[x1][y1]);
        System.out.println("("+x1+","+y1+")");
        firstClick = false;
   
      }
    }
    
    else if(firstClick==false&&(x0!=x1||y0!=y1)){
      x2=x0;
      y2=y0;
      chessDisplay.highlightSquare(firstClick, j[x1][y1], x1,y1, p[x1][y1]);
      chessVersion.makeMove(p[x1][y1], x2, y2);
      System.out.println("("+x2+","+y2+")");
      firstClick = true;
    }
  }

  /*this method is used to add piece into chess board*/
  public void addPiece(Piece piece, int x, int y){
  p[x][y]=piece;
  chessDisplay.displayFilledSquare(j[x][y],x,y,piece);
  }
  
  /*this method is used to remove piece from chess board*/
  public void removePiece(int x, int y){
  p[x][y] = null;
  chessDisplay.displayEmptySquare(j[x][y],x,y);
  }
  
  /*this method is used to check whether there is a piece*/
  public boolean hasPiece(int x, int y){
	    if((x < 8 && y < 8)  &&(x >= 0 && y >= 0) ){
	      return p[x][y] != null;
	    }
	    else{
	      return false;
	    }
  }
  
  /*this method is used to get the piece in this location*/
  public Piece getPiece(int x, int y){
    return p[x][y];
  }

  /*helper method
   * get rows of board*/
  public int getRow(){
    return rows;
  }
  
  /*get columns of board*/
  public int getColumn(){
    return columns;
  }
  
  
  /* ----------------------------AI PArt--------------------------------*/
  public ChessBoard(Piece[][] state){
	    this.rows=8;
	    this.columns=8;
	    this.chessDisplay = null;
	    this.chessVersion = null;
	    this.j= null;
	    this.p = state;
	    this.firstClick = false;
  }
  
  public Piece[][] getPieces(){
	    return p;
  }

}
   


   