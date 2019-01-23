package com.MyChess.MoveStrategy;

import com.MyChess.Board.Board;
import com.MyChess.Board.BoardUtil;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;
import com.MyChess.Piece.Knight;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class KnightMovingStr implements MovingStrategy {

    public static final int[][] KnightMove = {{2,1},{2,-1},{1,2},{1,-2},{-2,1},{-2,-1},{-1,2},{-1,-2}};
    private int columnCandidate;
    private int rowCandidate;
    private Position position;
    private static Board board;
    private BoardUtil boardUtil;

    public KnightMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
        position = getPosition();
    }
    public void Move(int columnPos,int rowPos){
        Tile tile = board.getTile()[columnPos][rowPos];
      for(int i=0;i<8;i++) {
        columnCandidate = columnPos +KnightMove[i][0];
        rowCandidate = rowPos +KnightMove[i][1];
         if(boardUtil.isValidMove(columnCandidate,rowCandidate)){
             Tile canTile = board.getTile()[columnCandidate][rowCandidate];
             if(canTile.isOccupied()){
                 if(boardUtil.isEnemy(tile.getPiece(),canTile.getPiece())){
                     position.setPosition(columnCandidate,rowCandidate);
                 }
             }
             else{
                 position.setPosition(columnCandidate,rowCandidate);
             }
         }
    }
    }
}
