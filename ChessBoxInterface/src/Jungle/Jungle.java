package Jungle;
import java.awt.Color;
import java.util.Random;

import ChessUtility.Piece;
import ChessUtility.ChessGame.Side;

public class Jungle implements JungleChessGame{

 /*check whether game starts*/
  private boolean start = true;
  /*store the side which will start game(click firstly)*/
  private Side firstSide = null;
  /*store the current side which has moved its piece*/
  private Side currentSide = null;
  
  /*store chessboard*/
  private JungleChessBoard game = null;
  
  /*--------------AI fields----------------*/
  private boolean AI_game = false;
  /*---------------------------------------*/
  
  /*create a Jungle board and add all the pieces needed into it*/
  public Jungle(boolean AI_game){
	  
  this.AI_game = AI_game;  
  firstSide = Side.NORTH;
  
  if(AI_game) {
	  firstSide = Side.SOUTH;
  }
  
  /*create the board*/
  game = new JungleChessBoard(9,7,new JungleDisplay(), this);

  game.addPiece(new CatPiece(game,Color.RED, "CAT", Jungle.Side.NORTH,null),1,5);
  game.addPiece(new CatPiece(game,Color.WHITE, "CAT", Jungle.Side.SOUTH,null),7,1);
  
  game.addPiece(new DogPiece(game,Color.RED, "DOG", Jungle.Side.NORTH,null),1,1);
  game.addPiece(new DogPiece(game,Color.WHITE, "DOG", Jungle.Side.SOUTH,null),7,5);
  
  game.addPiece(new JungleElephantPiece(game,Color.RED, "ELE", Jungle.Side.NORTH,null),2,6);
  game.addPiece(new JungleElephantPiece(game,Color.WHITE, "ELE", Jungle.Side.SOUTH,null),6,0);
  
  game.addPiece(new LeopardPiece(game,Color.RED, "LEO", Jungle.Side.NORTH,null),2,2);
  game.addPiece(new LeopardPiece(game,Color.WHITE, "LEO", Jungle.Side.SOUTH,null),6,4);
  
  game.addPiece(new LionPiece(game,Color.RED, "LIO", Jungle.Side.NORTH,null),0,0);
  game.addPiece(new LionPiece(game,Color.WHITE, "LIO", Jungle.Side.SOUTH,null),8,6);
  
  game.addPiece(new MousePiece(game,Color.RED, "MOU", Jungle.Side.NORTH,null),2,0);
  game.addPiece(new MousePiece(game,Color.WHITE, "MOU", Jungle.Side.SOUTH,null),6,6);
  
  game.addPiece(new TigerPiece(game,Color.RED, "TIG", Jungle.Side.NORTH,null),0,6);
  game.addPiece(new TigerPiece(game,Color.WHITE, "TIG", Jungle.Side.SOUTH,null),8,0);
  
  game.addPiece(new WolfPiece(game,Color.RED, "WOL", Jungle.Side.NORTH,null),2,4);
  game.addPiece(new WolfPiece(game,Color.WHITE, "WOL", Jungle.Side.SOUTH,null),6,2);
  
  
  }
  
  /*check whether it is the right side to play*/
  public boolean legalPieceToPlace(JunglePiece piece){
    if(start==true&&piece.getSide()==firstSide){
      return true;
    }
    else if(start==true&&piece.getSide()!=firstSide){
      return false;
    }
    else if(start==false&&piece.getSide()!=currentSide){
      return true;
    }
    else{
      return false;
    }
  }
  
  /*check whether it is legal to move and then move the piece*/
  public boolean makeMove(JunglePiece piece,int x, int y){
	  if(AI_game) {
			 if(piece.isLegalMove(x,y)==true){
			      piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
			      piece.getChessBoard().addPiece(piece,x,y);
			      piece.moveDone();
			      
			      if(start==true){
			        currentSide=firstSide;
			        start=false;
			      }
			      else if(start==false){
			        currentSide=piece.getSide();
			      }
			      
			      JunglePiece AI_piece = findRandomPiece(currentSide);
			      automove(currentSide,AI_piece);
			      currentSide = AI_piece.getSide();
			      
			      return true;
			      }
			    else{
			      return false;
			    }
				
		}
	  else {
    if(piece.isLegalMove(x,y)==true){
      piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
      piece.getChessBoard().addPiece(piece,x,y);
      piece.moveDone();
      
      if(start==true){
        currentSide=firstSide;
        start=false;
      }
      else if(start==false){
        currentSide=piece.getSide();
      }
      return true;
      }
    else{
      return false;
    }
	  }
  }
  
  public JungleChessBoard getBoard() {
	  return this.game;
  }
//find ai chess
public JunglePiece findRandomPiece(Side currentside) {
	Random rand = new Random();
	int row = rand.nextInt(9);
	int col = rand.nextInt(7);
	if(this.getBoard().hasPiece(row, col)) {
		if(this.getBoard().getPiece(row, col).getSide()!=currentside) {
			return this.getBoard().getPiece(row, col);
		}
		else {
			return findRandomPiece(currentside);
		}
	}
	else {
		return findRandomPiece(currentside);
	}
}

//make a workable move
public void automove(Side side, JunglePiece aI_piece) {
	Random rand = new Random();
	int row = rand.nextInt(9);
	int col = rand.nextInt(7);
	 if(aI_piece.isLegalMove(row,col)==true){
	      aI_piece.getChessBoard().removePiece(aI_piece.getRow(),aI_piece.getColumn());
	      aI_piece.getChessBoard().addPiece(aI_piece,row,col);
	      aI_piece.moveDone();	      
	      
	 }
	 else{
	     automove(side,aI_piece);
	 }
}
}
   