package com.MyChess.Piece;

import com.MyChess.Board.Aliance;

public class King extends Piece {

    String pieceName = "K";
    private static int[][] kingMove = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
    public King(int columnPos,int rowPos, Aliance aliance){

        super(columnPos,rowPos,aliance);
        this.pieceName= this.getAliance().name() + this.pieceName;
    }
    @Override
    public void ProvisionalMove(){
        for(int i=0;i<8;i++) {
            this.columnCandidate = this.columnPos +kingMove[i][0];
            this.rowCandidate = this.rowPos +kingMove[i][1];
            settingInvalidMove();
        }
    };

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

}