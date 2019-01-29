package com.MyChess.Piece;


import com.MyChess.Board.*;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.Position.getPosition;

public class Pawn extends Piece {


    public Pawn(int columnPos,int rowPos, Aliance aliance,String pieceName){

        super(columnPos,rowPos,aliance,pieceName);

    }

}
