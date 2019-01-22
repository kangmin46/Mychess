package com.MyChess.Piece;


import com.MyChess.Board.Aliance;
import com.MyChess.Board.Board;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.Position.getPosition;

public class Pawn extends Piece {

    String pieceName = "P";

    public static int[][] PawnMove ={{1,1},{1,-1},{1,0},{2,0}};

 
    private boolean firstMove = true;
    private boolean isJumnp = false;


    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }



    public Pawn(int columnPos,int rowPos, Aliance aliance){

        super(columnPos,rowPos,aliance);
        this.pieceName= this.getAliance().name() + this.pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }


    @Override
    public void ProvisionalMove(){
        this.CheckAliance();
        CanAttackMove();
        CancommonMove();

    };
    public void CheckAliance(){
        Board board = this.getboard();
        if(board.getTile()[this.columnPos][this.rowPos].getTileOnPiece().getAliance()==Aliance.W){
            if(PawnMove[0][0] == 1){
                for(int i=0;i<4;i++){
                    PawnMove[i][0] =  -PawnMove[i][0];
                }
            }
        }
        else{
            if(PawnMove[0][0] == -1){
                for(int i=0;i<4;i++){
                    PawnMove[i][0] =  -PawnMove[i][0];
                }
            }
        }

        
    }
    public void CanAttackMove(){
        for(int j=0;j<2;j++) {
            this.columnCandidate = this.columnPos + PawnMove[j][0];
            this.rowCandidate = this.rowPos + PawnMove[j][1];
            settingInvalidMove();
        }
    }
    public void commonMove(int columnCandidate,int rowCandidate){
        Board board = this.getboard();
        Tile canTile = board.getTile()[columnCandidate][rowCandidate];
        if(this.isValidMove(columnCandidate,rowCandidate)){
            if(canTile.isOccupied()){
                this.isJumnp=true;
            }
            else{
                position.setRow_Position(rowCandidate);
                position.setColumn_Position(columnCandidate);
            }
        }
    }
    public void CancommonMove(){

        if(this.firstMove) {
            for (int i = 2; i < 4; i++) {
                this.columnCandidate = this.columnPos + PawnMove[i][0];
                this.rowCandidate = this.rowPos + PawnMove[i][1];
              commonMove(this.columnCandidate,this.rowCandidate);
              if(this.isJumnp){
                  break;
              }
            }
        }
        else{
            this.columnCandidate = this.columnPos + PawnMove[2][0];
            this.rowCandidate = this.rowPos + PawnMove[2][1];
            commonMove(this.columnCandidate,this.rowCandidate);
        }
        if(this.isJumnp){this.isJumnp = false;}
    }
}
