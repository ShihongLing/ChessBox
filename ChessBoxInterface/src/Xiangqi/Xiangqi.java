package Xiangqi;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import AI.MinMaxAI;
import AI.Move;
import ChessUtility.*;
import ChessUtility.ChessGame.Side;
/*a class represent chess game, Xiangqi!*/
public class Xiangqi implements ChessGame{
  /*check whether game starts*/
  private boolean start = true;
  /*store the side which will start game(click firstly)*/
  private Side firstSide = null;
  /*store the current side which has moved its piece*/
  private Side currentSide = null;
  
  /*store chessboard*/
  private ChessBoard game = null;
  
  /*--------------AI fields----------------*/
  private boolean AI_game = false;
  /*---------------------------------------*/
  
  /*create a Xiangqi board and add all the pieces needed into it*/
  public Xiangqi(boolean AI_game){
	  
  this.AI_game = AI_game;
	  
	  
  firstSide = Side.NORTH;
	  
  if(AI_game) {
	firstSide = Side.SOUTH;
  }
  
  /*create the board*/
  game = new ChessBoard(10,9,new Display(), this);
  
  /*add RookPiece*/
  game.addPiece(new RookPiece(game,Color.RED, "R", Xiangqi.Side.NORTH,null),0,0);
  game.addPiece(new RookPiece(game,Color.RED, "R", Xiangqi.Side.NORTH,null),0,8);
  game.addPiece(new RookPiece(game,Color.WHITE, "R", Xiangqi.Side.SOUTH,null),9,0);
  game.addPiece(new RookPiece(game,Color.WHITE, "R", Xiangqi.Side.SOUTH,null),9,8);
  
  /*add Cannon Piece*/
  game.addPiece(new CannonPiece(game,Color.RED, "C", Xiangqi.Side.NORTH,null),2,1);
  game.addPiece(new CannonPiece(game,Color.RED, "C", Xiangqi.Side.NORTH,null),2,7);
  game.addPiece(new CannonPiece(game,Color.WHITE, "C", Xiangqi.Side.SOUTH,null),7,1);
  game.addPiece(new CannonPiece(game,Color.WHITE, "C", Xiangqi.Side.SOUTH,null),7,7);
  
  /*add KingPiece*/
  game.addPiece(new KingPiece(game,Color.RED,"K",Xiangqi.Side.NORTH,null),0,4);
  game.addPiece(new KingPiece(game,Color.WHITE,"K",Xiangqi.Side.SOUTH,null),9,4);
  
  /*add GuardPiece*/
  game.addPiece(new GuardPiece(game,Color.RED,"G",Xiangqi.Side.NORTH,null),0,3);
  game.addPiece(new GuardPiece(game,Color.RED,"G",Xiangqi.Side.NORTH,null),0,5);
  game.addPiece(new GuardPiece(game,Color.WHITE,"G",Xiangqi.Side.SOUTH,null),9,3);
  game.addPiece(new GuardPiece(game,Color.WHITE,"G",Xiangqi.Side.SOUTH,null),9,5);
  
  /*add ElephantPiece*/
  game.addPiece(new ElephantPiece(game,Color.RED,"E",Xiangqi.Side.NORTH,null),0,2);
  game.addPiece(new ElephantPiece(game,Color.RED,"E",Xiangqi.Side.NORTH,null),0,6);
  game.addPiece(new ElephantPiece(game,Color.WHITE,"E",Xiangqi.Side.SOUTH,null),9,2);
  game.addPiece(new ElephantPiece(game,Color.WHITE,"E",Xiangqi.Side.SOUTH,null),9,6);    
  
  /*add KnightPiece*/
  game.addPiece(new KnightPiece(game,Color.RED,"N",Xiangqi.Side.NORTH,null),0,1);
  game.addPiece(new KnightPiece(game,Color.RED,"N",Xiangqi.Side.NORTH,null),0,7);
  game.addPiece(new KnightPiece(game,Color.WHITE,"N",Xiangqi.Side.SOUTH,null),9,1);
  game.addPiece(new KnightPiece(game,Color.WHITE,"N",Xiangqi.Side.SOUTH,null),9,7);    
  
  /*add PawnPiece*/
  game.addPiece(new PawnPiece(game,Color.RED,"P",Xiangqi.Side.NORTH,null),3,0);
  game.addPiece(new PawnPiece(game,Color.RED,"P",Xiangqi.Side.NORTH,null),3,2);
  game.addPiece(new PawnPiece(game,Color.RED,"P",Xiangqi.Side.NORTH,null),3,4);
  game.addPiece(new PawnPiece(game,Color.RED,"P",Xiangqi.Side.NORTH,null),3,6);
  game.addPiece(new PawnPiece(game,Color.RED,"P",Xiangqi.Side.NORTH,null),3,8);
  game.addPiece(new PawnPiece(game,Color.WHITE,"P",Xiangqi.Side.SOUTH,null),6,0);
  game.addPiece(new PawnPiece(game,Color.WHITE,"P",Xiangqi.Side.SOUTH,null),6,2);
  game.addPiece(new PawnPiece(game,Color.WHITE,"P",Xiangqi.Side.SOUTH,null),6,4);
  game.addPiece(new PawnPiece(game,Color.WHITE,"P",Xiangqi.Side.SOUTH,null),6,6);
  game.addPiece(new PawnPiece(game,Color.WHITE,"P",Xiangqi.Side.SOUTH,null),6,8);
  
  }
  
  /*check whether it is the right side to play*/
  @Override
  public boolean legalPieceToPlace(Piece piece){
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
  public boolean makeMove(Piece piece,int x, int y){
    
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
		      
		      Piece AI_piece = findRandomPiece(currentSide);
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
  
  public ChessBoard getBoard() {
	  return this.game;
  }

/*------------------------AI part-----------------------------*/
@Override
public boolean makeMoveAI(int x0, int y0, int x1, int y1) {
	return false;
}

@Override
public ArrayList<Move> getMoves(Side currentSide) {
    return null;
}

@Override
public Piece[][] getState() {
	return null;
}

@Override
public String getGameType() {
	// TODO Auto-generated method stub
	return "Xiangqi";
}

public Xiangqi(Piece[][] state){
    this.game = new ChessBoard(state);
}

public void setSide(Side side) {
	this.currentSide = side;
}

//find ai chess
public Piece findRandomPiece(Side currentside) {
	Random rand = new Random();
	int row = rand.nextInt(10);
	int col = rand.nextInt(9);
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
public void automove(Side side, Piece piece) {
	Random rand = new Random();
	int row = rand.nextInt(10);
	int col = rand.nextInt(9);
	 if(piece.isLegalMove(row,col)==true){
	      piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
	      piece.getChessBoard().addPiece(piece,row,col);
	      piece.moveDone();	      
	      
	 }
	 else{
	     automove(side,piece);
	 }
}
}
   