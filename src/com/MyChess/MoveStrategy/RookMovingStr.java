package com.MyChess.MoveStrategy;

import com.MyChess.Board.Board;
import com.MyChess.Board.BoardUtil;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class RookMovingStr implements MovingStrategy {

    private int canColumnPos;
    private int canRowPos;
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
    }

    public void Move(Board board,int columnPos,int rowPos){

        Tile tile = board.getTile()[columnPos][rowPos];
        for(int j=0;j<4;j++) {
            for (int i = 0; i < 7; i++) {
                canColumnPos = columnPos + rookMove[j][i][0];
                canRowPos = rowPos + rookMove[j][i][1];
                if(!boardUtil.isValidMove(canColumnPos,canRowPos)){
                    continue;
                };
                Tile canTile = board.getTile()[canColumnPos][canRowPos];
                if(canTile.isOccupied()){
                    if(boardUtil.isEnemy(tile.getPiece(),canTile.getPiece())){
                        boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                    }
                   break;
                }
                else{
                    boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                }
            }
        }
    }
}
