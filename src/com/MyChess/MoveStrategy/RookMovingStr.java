package com.MyChess.MoveStrategy;

import com.MyChess.Board.Board;
import com.MyChess.Board.BoardUtil;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class RookMovingStr implements MovingStrategy {

    private int columnCandidate;
    private int rowCandidate;
    private Position position;
    private static Board board;
    private BoardUtil boardUtil;
    private boolean isJump = false;
    private static final int[][][] rookMove= {{{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}},
            {{0,-1},{0,-2},{0,-3},{0,-4},{0,-5},{0,-6},{0,-7}},
            {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0}},
            {{-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{-6,0},{-7,0}}};
    public RookMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
        position = getPosition();
    }

    public void Move(int columnPos,int rowPos){
        Tile tile = board.getTile()[columnPos][rowPos];
        for(int j=0;j<4;j++) {
            for (int i = 0; i < 7; i++) {
                columnCandidate = columnPos + rookMove[j][i][0];
                rowCandidate = rowPos + rookMove[j][i][1];
                if(!boardUtil.isValidMove(columnCandidate,rowCandidate)){
                    continue;
                };
                Tile canTile = board.getTile()[columnCandidate][rowCandidate];
                if(canTile.isOccupied()){
                    if(boardUtil.isEnemy(tile.getPiece(),canTile.getPiece())){
                        position.setPosition(columnCandidate,rowCandidate);
                    }
                   break;
                }
                else{
                    position.setPosition(columnCandidate,rowCandidate);
                }
            }
        }
    }
}
