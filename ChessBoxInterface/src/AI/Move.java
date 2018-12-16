package AI;
public class Move {
    private int x0,y0, x1,y1;
    private boolean castling = false;
    public Move(int x0,int y0,int x1,int y1, boolean castling){
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.castling = castling;
    }

    public int getX0(){
        return x0;
    }

    public int getX1(){
        return x1;
    }

    public int getY0(){
        return y0;
    }

    public int getY1(){
        return y1;
    }
}

