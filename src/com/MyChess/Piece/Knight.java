package com.MyChess.Piece;

import com.MyChess.Board.Aliance;
import com.MyChess.Board.Board;
import com.MyChess.GUI.ChessLabel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static com.MyChess.Board.Board.getBoard;

public class Knight extends Piece {


    public static final int[][] KnightMove = {{2,1},{2,-1},{1,2},{1,-2},{-2,1},{-2,-1},{-1,2},{-1,-2}};
    public Knight(int columnPos,int rowPos, Aliance aliance,String pieceName){

        super(columnPos,rowPos,aliance,pieceName);
    }
    public void ProvisionalMove(){


    };

    public String getPieceName(){
        return this.pieceName;
    }


}