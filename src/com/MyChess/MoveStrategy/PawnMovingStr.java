package com.MyChess.MoveStrategy;

import com.MyChess.Board.*;
import com.MyChess.Piece.Piece;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class PawnMovingStr implements MovingStrategy {

    public static int[][] PawnMove ={{1,1},{1,-1},{1,0},{2,0}};
    private Tile tile;
    private int canColumnPos;
    private int canRowPos;
    private Position position;
    private static Board board;
    private BoardUtil boardUtil;


    public PawnMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
        position = getPosition();

    }

    public void CheckAliance(int columnPos,int rowPos){
        tile = board.getTile()[columnPos][rowPos];
        if(tile.getTileOnPiece().getAliance()== Aliance.W){
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
    public void Move(int columnPos,int rowPos){
        CheckAliance(columnPos,rowPos);
        Tile tile = board.getTile()[columnPos][rowPos];
        for(int i=0;i<4;i++){
            canColumnPos = PawnMove[i][0] + columnPos;
            canRowPos = PawnMove[i][1] + rowPos;
            if(boardUtil.isValidMove(canColumnPos,canRowPos)){
                System.out.println(canColumnPos);
                System.out.println(canRowPos);
                Tile canTile = board.getTile()[canColumnPos][canRowPos];
                if(i==0 || i==1){
                    if((canTile.isOccupied()) && (boardUtil.isEnemy(canTile.getPiece(),tile.getPiece()))) {
                       position.setPosition(canColumnPos,canRowPos);
                    }
                    else{
                        continue;
                        }
                    }
                else if(i==2){
                    System.out.println("일로옴");
                    if(canTile.isOccupied()){
                        continue;
                    }
                    else{
                        position.setPosition(canColumnPos,canRowPos);
                    }
                    }
                else{
                    System.out.println("여기옴");
                    if(tile.getPiece().getFirstMove()){
                        position.setPosition(canColumnPos,canRowPos);
                    }
                    else{
                        continue;
                    }
                }
            }
        }
    }
}
