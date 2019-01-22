package com.MyChess.Piece;

import com.MyChess.Board.Aliance;
import com.MyChess.Board.Board;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;

import java.util.List;

import static com.MyChess.Board.Position.getPosition;

public abstract class Piece {

    protected int rowPos;
    protected int columnPos;
    private Aliance aliance;
    protected int rowCandidate;
    protected int columnCandidate;
    protected boolean isJump = false;
    protected boolean isqueenMove = false;
    String pieceName = "";
    protected Position position = getPosition();

    public Piece(int columnPos,int rowPos, Aliance aliance){
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.aliance = aliance;
    }

    public String getPieceName() {
        return pieceName;
    }

    public Board getboard(){
        return Board.getBoard();
    }

    public Aliance getAliance(){
        return aliance;
    }

    public void setAliance(Aliance aliance){
        this.aliance = aliance;
    }

    public boolean isEnemy(Aliance aliance){
        if(this.aliance.equals(aliance)){ return false;}
        else{return true;}
    }

    public int getrowPos() {
        return rowPos;
    }

    public int getcolumnPos() {
        return columnPos;
    }

    public void setrowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setcolumnPos(int columnPos) {
        this.columnPos = columnPos;
    }
    public void  ActualMove(int rowDestination, int columnDestination){
        this.rowPos = rowDestination;
        this.columnPos = columnDestination;
    }
    public boolean isValidMove( int columnDestination,int rowDestination){
        if( (rowDestination>=0 && rowDestination<8) &&(columnDestination>=0 && columnDestination<8 )){
            return true;
        }
        else{
            return false;
        }
    }
    public void KQB_MoveMent(int[][][] KQBMove,int j){
        for (int i = 0; i < 7; i++) {
            this.columnCandidate = this.columnPos + KQBMove[j][i][0];
            this.rowCandidate = this.rowPos + KQBMove[j][i][1];
            this.settingInvalidMove();
            if(this.isJump){
                this.isJump = false;
                break;
            }
        }

    }
    public void KQB_Move(int[][][] KQBMove){
        if(this.isqueenMove){
            for(int j=0;j<8;j++) {
                KQB_MoveMent(KQBMove,j);
            }
            this.isqueenMove =false;
        }
        else{
            for(int j=0;j<4;j++) {
                KQB_MoveMent(KQBMove,j);
            }
        }
    }
    public void settingInvalidMove(){
        Board board = getboard();

        if (this.isValidMove(this.columnCandidate,this.rowCandidate)) {
            Tile canTile = board.getTile()[this.columnCandidate][this.rowCandidate];
            if (canTile.isOccupied()) {
                    this.isJump= true;
                if (board.getTile()[this.columnPos][this.rowPos].getPiece().
                        isEnemy(canTile.getPiece().getAliance())) {
                    position.setRow_Position(this.rowCandidate);
                    position.setColumn_Position(this.columnCandidate);
                }
            }
            else{
                if(board.getTile()[this.columnPos][this.rowPos].getPiece().getPieceName().equals("BP")||
                        board.getTile()[this.columnPos][this.rowPos].getPiece().getPieceName().equals("WP"))
                {

                }
                else {
                    position.setRow_Position(this.rowCandidate);
                    position.setColumn_Position(this.columnCandidate);
                }
            }
        }
    }
    public abstract void ProvisionalMove();
}
