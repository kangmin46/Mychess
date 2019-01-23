package com.MyChess.Piece;

import com.MyChess.Board.Aliance;

public class Queen extends Piece {

    public Queen(int columnPos,int rowPos, Aliance aliance,String pieceName){

        super(columnPos,rowPos,aliance,pieceName);
    }

    public String getPieceName() {
        return pieceName;
    }


}