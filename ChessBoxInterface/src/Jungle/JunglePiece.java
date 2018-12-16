package Jungle;
import java.awt.Color;
import javax.swing.Icon;

/*an abstract class contain the same methods all knids of piece have*/
public abstract class JunglePiece{
  /*store the board which the piece is in*/
  private JungleChessBoard board=null;
  /*store the color of piece*/
  private Color color= null;
  /*store the name of piece*/
  private String label = "";
  /*store the side of piece*/
  private Jungle.Side side = null;
  /*store the icon of the piece*/
  private Icon icon = null;
  /*store the size of the piece*/
  protected int size = 0;
  /*store the position of piece*/
  private int x=0;
  private int y=0;
  
  /*create a piece*/
  public JunglePiece(JungleChessBoard game, Color color, String label, Jungle.Side side, Icon icon){
    this.board = game;
    this.color = color;
    this.label = label;
    this.side = side;
    this.icon = icon;
  }
  
  /*get the board the piece is in*/
  public JungleChessBoard getChessBoard(){
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
  public Jungle.Side getSide(){
    return side;
  }
  
  public int getSize() {
	  if(this.getChessBoard().isTrap(this.getRow(), this.getColumn()))
		  return -1;
	  return size;
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
  
  private boolean isLargerSize(JunglePiece piece) {
	  if (this.getSize() < piece.getSize() && !(this.getSize()==0 && piece.getSize()==7))
		  return false;
	  else
		  return true;
  }
  
  public boolean isLegalNonRiverMove(int x, int y) {
	  if (!this.getChessBoard().isRiver(x, y)&&((Math.abs(x - this.getRow()) == 1 && y == this.getColumn()) || (x == this.getRow() && Math.abs(y - this.getColumn()) == 1))) {
		  if(this.getSide() == Jungle.Side.NORTH && !this.getChessBoard().isNorthBase(x, y))
			  return true;
		  else if(this.getSide() == Jungle.Side.SOUTH && !this.getChessBoard().isSouthBase(x, y))
			  return true;
		} 
		return false;
  }
	/*
	 * check whether the piece can move to input position while there is an empty
	 * legal square
	 */
	public abstract boolean isLegalNonCaptureMove(int x, int y);
  
  /*check whether this piece can move to the input position
   * while there is another piece there*/
  public boolean isLegalCaptureMove(int x, int y){
	  JunglePiece otherPiece = this.getChessBoard().getPiece(x,y);
    if(this.isLegalNonCaptureMove(x,y)==true&&otherPiece.getSide()!=this.getSide()&&this.isLargerSize(otherPiece)){
      return true;
    }
    else{
      return false;
    }
  }

}
  
  
  