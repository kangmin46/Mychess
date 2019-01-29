package com.MyChess.MoveStrategy;

import com.MyChess.Board.Board;
import com.MyChess.Board.BoardUtil;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class KingMovingStr implements MovingStrategy {

    private static final int[][] kingMove = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
    private int canColumnPos;
    private int canRowPos;
    private static Board board;
    private BoardUtil boardUtil;
    public KingMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
    }
    public void Move(Board board,int columnPos,int rowPos){
        System.out.println("KIngMove!");
        Tile tile = board.getTile()[columnPos][rowPos];
            for (int i = 0; i < 8; i++) {
                canColumnPos = columnPos + kingMove[i][0];
                canRowPos = rowPos + kingMove[i][1];
                if (boardUtil.isValidMove(canColumnPos, canRowPos)) {
                    Tile canTile = board.getTile()[canColumnPos][canRowPos];
                    if(tile.getPiece().getPieceName().charAt(0)=='W'){
                        if(canTile.isbTemMove()){
                            continue;
                        }
                    }
                    else{
                        if(canTile.iswTemMove()){
                            continue;
                        }
                    }
                    if (canTile.isOccupied()) {
                        if (boardUtil.isEnemy(tile.getPiece(), canTile.getPiece())) {
                            boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                        }
                    } else {
                        boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                    }
                }
            }
    }
}
