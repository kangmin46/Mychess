package com.MyChess.Piece;

import com.MyChess.Board.Aliance;
import com.MyChess.Board.Board;

public class Knight extends Piece {

    String pieceName = "N";
    public static int[][] KnightMove = {{2,1},{2,-1},{1,2},{1,-2},{-2,1},{-2,-1},{-1,2},{-1,-2}};
    public Knight(int columnPos,int rowPos, Aliance aliance){

        super(columnPos,rowPos,aliance);
        this.pieceName= this.getAliance().name() + this.pieceName;
    }
    @Override
    public void ProvisionalMove(){

        for(int i=0;i<8;i++) {
            this.columnCandidate = this.columnPos +KnightMove[i][0];
            this.rowCandidate = this.rowPos +KnightMove[i][1];
            settingInvalidMove();
        }
    };

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }
    public String getPieceName(){
        return this.pieceName;
    }

    public void  pieceMove(int piecePosition, int destination){

    };
}