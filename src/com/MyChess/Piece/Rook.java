package com.MyChess.Piece;

import com.MyChess.Board.Aliance;

public class Rook extends Piece {

    String pieceName = "R";
    private static int[][][] rookMove= {{{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}},
                                        {{0,-1},{0,-2},{0,-3},{0,-4},{0,-5},{0,-6},{0,-7}},
                                        {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0}},
                                         {{-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{-6,0},{-7,0}}};
    public Rook(int columnPos,int rowPos, Aliance aliance){

        super(columnPos,rowPos,aliance);
        this.pieceName= this.getAliance().name() + this.pieceName;
    }

    @Override
    public void ProvisionalMove(){
     KQB_Move(rookMove);
    };

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void  pieceMove(int piecePosition, int destination){

    };
}
