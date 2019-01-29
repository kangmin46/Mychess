package com.MyChess.Piece;

import com.MyChess.Board.*;
import com.MyChess.MoveStrategy.MovingStrategy;


import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.Position.getPosition;

public abstract class Piece {

    protected int rowPos;
    protected int columnPos;
    private Aliance aliance;
    protected boolean isJump = false;
    protected boolean firstMove=true;
    private MovingStrategy movingStrategy;

    public void setJump(boolean jump) {
        isJump = jump;
    }

    public boolean isJump() {
        return isJump;
    }

    String pieceName = "";
    protected Position position = getPosition();

    public Piece(int columnPos,int rowPos, Aliance aliance,String pieceName){
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.aliance = aliance;
        this.pieceName= this.getAliance().name() + pieceName;

    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public Aliance getAliance(){
        return aliance;
    }

    public void setrowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setcolumnPos(int columnPos) {
        this.columnPos = columnPos;
    }

    public boolean getFirstMove(){
        return this.firstMove;
    }

    public int getColumnPos() {
        return columnPos;
    }

    public int getRowPos() {
        return rowPos;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove){

        this.firstMove = firstMove;
    }
}
