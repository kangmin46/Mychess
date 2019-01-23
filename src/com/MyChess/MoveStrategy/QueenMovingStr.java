package com.MyChess.MoveStrategy;

import com.MyChess.Board.Board;
import com.MyChess.Board.BoardUtil;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;
import com.MyChess.Piece.Queen;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class QueenMovingStr implements MovingStrategy {

    private int columnCandidate;
    private int rowCandidate;
    private Position position;
    private static Board board;
    private BoardUtil boardUtil;
    private boolean isJump = false;
    private static final int[][][] queenMove = {{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}},
            {{-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7}},
            {{1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5}, {6, -6}, {7, -7}},
            {{-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}, {-6, -6}, {-7, -7}},
            {{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}},
            {{0,-1},{0,-2},{0,-3},{0,-4},{0,-5},{0,-6},{0,-7}},
            {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0}},
            {{-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{-6,0},{-7,0}}};
    public QueenMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
        position = getPosition();
    }

    public void Move(int columnPos,int rowPos){
        Tile tile = board.getTile()[columnPos][rowPos];
        for(int j=0;j<8;j++) {
            for (int i = 0; i < 7; i++) {
                columnCandidate = columnPos + queenMove[j][i][0];
                rowCandidate = rowPos + queenMove[j][i][1];
                if(!boardUtil.isValidMove(columnCandidate,rowCandidate)){
                    continue;
                }
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
