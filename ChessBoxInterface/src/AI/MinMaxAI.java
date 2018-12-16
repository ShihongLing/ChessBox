package AI;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import ChessUtility.*;
import InternationalChess.*;
import Jungle.*;
import Shogi.*;
import Xiangqi.*;

public class MinMaxAI {
    int maxDepth;
    Random rand = new Random();
    public MinMaxAI(int maxDepth, long seed){
        this.maxDepth = maxDepth;
        this.rand.setSeed(seed);
    }

    public void makeMove(ChessGame game, Move move){
        game.makeMoveAI(move.getX0(),move.getY0(),move.getX1(),move.getY1());
    }


    public Move generateNextMove(ChessGame game){
        ArrayList<Move> moves = game.getMoves(ChessGame.Side.NORTH);
        int index = rand.nextInt(moves.size());
        Move nextmove = moves.get(index);
        return nextmove;
    }

    public Move getNextBestmove(ChessGame game){
        Piece[][] state = game.getState();
        ArrayList<Move> moves = game.getMoves(ChessGame.Side.NORTH);
        ArrayList<Piece[][]> states = new ArrayList<Piece[][]>();
        List<Integer> eval = new ArrayList<Integer> ();
        for(int i = 0; i < moves.size();i++){
            Piece[][] next = getNextState(state,moves.get(i));
            states.add(next);
            eval.add(eval(next));
        }
        int maxindex = Collections.max(eval);

        return moves.get(maxindex);
    }

    private static class movingInfo{
        int value;
        Move possibleMove;
        public movingInfo(int v,Move move){
            this.value = v;
            this.possibleMove = move;
        }
    }

    public Move getDecision(ChessGame game){
        movingInfo decision = decision(maxDepth,game,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        return decision.possibleMove;
    }

    public movingInfo decision(int depth,ChessGame game,int alpha,int beta,boolean isMaximizingPlayer){
        if(depth > 0){
                int bestMoveV = isMaximizingPlayer ? Integer.MIN_VALUE: Integer.MAX_VALUE;
                Move best = null;
                ArrayList<Move> moves = null;
                if(isMaximizingPlayer) {
                    moves = game.getMoves(ChessGame.Side.NORTH);
                }
                else{
                    moves = game.getMoves(ChessGame.Side.SOUTH);
                }

                Collections.shuffle(moves);
                for (int i = 0; i < moves.size();i++){
                    Piece[][] nextState = getNextState(game.getState(),moves.get(i));
                    //need to check chess type
                    movingInfo next = decision(depth-1,new IChess(nextState),alpha,beta,!isMaximizingPlayer);
                    if(isMaximizingPlayer) {
                        if (next.value > bestMoveV) {
                            bestMoveV = next.value;
                            best = moves.get(i);
                        }
                        alpha = Math.max(alpha,next.value);
                    }
                    else{
                        if (next.value <= bestMoveV) {
                            bestMoveV = next.value;
                            best = moves.get(i);
                        }
                        beta = Math.min(beta,next.value);
                    }
                    if(beta <= alpha){
                        break;
                    }
                }
                return new movingInfo(bestMoveV,best);
        }
        else{
            return new movingInfo(eval(game.getState()),null);
        }
    }

    public int eval(Piece[][] b){
        // Sets the value for each piece using standard piece value
        // Loop through all pieces on the board and sum up total
        int score = 0;
        for(int i = 0; i < 8; i ++){
            for(int j =0; j < 8; j ++){
                if(b[i][j] != null){
                    float t = 0;
                    if(b[i][j].getLabel().equals("Ki")){
                        t = 10000;
                    }
                    else  if(b[i][j].getLabel().equals("C")){
                        t = 500;
                    }
                    else if(b[i][j].getLabel().equals("B")){
                        t = 300;
                    }
                    else if(b[i][j].getLabel().equals("K")){
                        t = 300;
                    }
                    else if(b[i][j].getLabel().equals("Q")){
                        t = 1000;
                    }
                    else{
                        t = 100;
                    }

                    if(b[i][j].getSide() == ChessGame.Side.NORTH){
                        score += t;
                    }
                    else{
                        score -= t;
                    }
                }
            }
        }
        return score;
    };



    public Piece[][] getNextState(Piece[][] state, Move move){
        Piece[][] nextState = new Piece[8][8];

        for(int i = 0; i < 8; i ++){
            for(int j =0; j < 8; j ++){
                if(state[i][j] != null){
                    nextState[i][j] = state[i][j].getClone(nextState);
                }
            }
        }


        for(int i = 0; i < 8; i ++){
            for(int j =0; j < 8; j ++){
                if(i == move.getX1() && j == move.getY1()){
                    nextState[i][j] = state[move.getX0()][move.getY0()].getClone(nextState);
                }
            }
        }

        for(int i = 0; i < 8; i ++){
            for(int j =0; j < 8; j ++){
                if(i == move.getX0() && j == move.getY0()){
                    nextState[i][j] = null;
                }
            }
        }

        for(int i = 0; i < 8; i ++){
            for(int j =0; j < 8; j ++){
                if(nextState[i][j] != null){
                    nextState[i][j].setBoard(new ChessBoard(nextState));
                    nextState[i][j].setPos(i,j);
                }
            }
        }

        return nextState;
    }



}
