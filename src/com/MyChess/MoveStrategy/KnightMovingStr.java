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
    private int canColumnPos;
    private int canRowPos;
    private static Board board;
    private BoardUtil boardUtil;

    public KnightMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
    }
    public void Move(Board board,int columnPos,int rowPos){
        System.out.println("KnightMove");
        Tile tile = board.getTile()[columnPos][rowPos];
      for(int i=0;i<8;i++) {
        canColumnPos = columnPos +KnightMove[i][0];
        canRowPos = rowPos +KnightMove[i][1];
         if(boardUtil.isValidMove(canColumnPos,canRowPos)){
             Tile canTile = board.getTile()[canColumnPos][canRowPos];
             if(canTile.isOccupied()){
                 if(boardUtil.isEnemy(tile.getPiece(),canTile.getPiece())){
                     boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                 }
             }
             else{
                 boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
             }
         }
    }
    }
}
