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
    public void AttackingMove(int columnPos,int rowPos) {
        Tile tile = board.getTile()[columnPos][rowPos];
        for (int i = 0; i < 2; i++) {
            canColumnPos = PawnMove[i][0] + columnPos;
            canRowPos = PawnMove[i][1] + rowPos;
            if (boardUtil.isValidMove(canColumnPos, canRowPos)) {
                Tile canTile = board.getTile()[canColumnPos][canRowPos];
                if (boardUtil.isPawnMove()) {
                    position.setPosition(canColumnPos, canRowPos);
                } else {
                    if ((canTile.isOccupied()) && (boardUtil.isEnemy(canTile.getPiece(), tile.getPiece()))) {
                        position.setPosition(canColumnPos, canRowPos);
                    }
                }
            }

        }
    }


    public void CommonMove(int columnPos,int rowPos){
        Tile tile = board.getTile()[columnPos][rowPos];
        for(int i=2;i<4;i++){
            canColumnPos = PawnMove[i][0] + columnPos;
            canRowPos = PawnMove[i][1] + rowPos;
            if(boardUtil.isValidMove(canColumnPos,canRowPos)) {
                Tile canTile = board.getTile()[canColumnPos][canRowPos];
                if(canTile.isOccupied()){
                    continue;
                }
                else if(i==2){
                    position.setPosition(canColumnPos,canRowPos);
                }
                else{
                    //commonMove
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
    public void Move(int columnPos,int rowPos){
        CheckAliance(columnPos,rowPos);
        if(boardUtil.isPawnMove()){
            AttackingMove(columnPos,rowPos);
        }
        else {
            AttackingMove(columnPos,rowPos);
            CommonMove(columnPos, rowPos);
        }
        }
    }

