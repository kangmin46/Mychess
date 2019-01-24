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
    private int columnCandidate;
    private int rowCandidate;
    private Position position;
    private static Board board;
    private BoardUtil boardUtil;
    public KingMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
        position = getPosition();
    }
    public void Move(int columnPos,int rowPos){
        Tile tile = board.getTile()[columnPos][rowPos];
            for (int i = 0; i < 8; i++) {
                columnCandidate = columnPos + kingMove[i][0];
                rowCandidate = rowPos + kingMove[i][1];
                if (boardUtil.isValidMove(columnCandidate, rowCandidate)) {
                    Tile canTile = board.getTile()[columnCandidate][rowCandidate];
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
                            position.setPosition(columnCandidate, rowCandidate);
                        }
                    } else {
                        position.setPosition(columnCandidate, rowCandidate);
                    }
                }
            }
    }
}
