package com.MyChess.MoveStrategy;

import com.MyChess.Board.*;
import com.MyChess.Piece.Piece;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class PawnMovingStr implements MovingStrategy {

    public static int[][] BlackPawnMove ={{1,1},{1,-1},{1,0},{2,0}};
    public static int[][] WhitePawnMove ={{-1,1},{-1,-1},{-1,0},{-2,0}};

    private int canColumnPos;
    private int canRowPos;
    private Position position;
    private static Board board;
    private BoardUtil boardUtil;
    private boolean isWhite;


    public PawnMovingStr(){
        this.board = getBoard();
        boardUtil = getBoardUtil();
        position = getPosition();

    }

    public void AttackingMove(Board board,int columnPos,int rowPos) {
        Tile tile = board.getTile()[columnPos][rowPos];

        for (int i = 0; i < 2; i++) {
            SetCanPos(columnPos,rowPos,i);
            if (boardUtil.isValidMove(canColumnPos, canRowPos)) {
                Tile canTile = board.getTile()[canColumnPos][canRowPos];
                    if ((canTile.isOccupied()) && (boardUtil.isEnemy(canTile.getPiece(), tile.getPiece()))) {
                        boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                    }
            }

        }
    }


    public void SetCanPos(int columnPos, int rowPos, int i){
        if(this.isWhite){
            canColumnPos = WhitePawnMove[i][0]+columnPos;
            canRowPos = WhitePawnMove[i][1]+rowPos;
        }
        else{
            canColumnPos = BlackPawnMove[i][0]+columnPos;
            canRowPos = BlackPawnMove[i][1]+rowPos;
        }

    }

    public void CommonMove(Board board, int columnPos ,int rowPos) {

        Tile tile = board.getTile()[columnPos][rowPos];
            for (int i = 2; i < 4; i++) {
                SetCanPos(columnPos,rowPos,i);
                if (boardUtil.isValidMove(canColumnPos, canRowPos)) {
                    Tile canTile = board.getTile()[canColumnPos][canRowPos];
                    if (canTile.isOccupied()) {
                        continue;
                    } else if (i == 2) {
                        boardUtil.saveCandidate(columnPos, rowPos, canColumnPos, canRowPos);
                    } else {

                        if (tile.getPiece().getFirstMove()) {
                            boardUtil.saveCandidate(columnPos, rowPos, canColumnPos, canRowPos);
                        } else {
                            continue;
                        }
                    }
                }

            }

        }

    public void Move(Board board,int columnPos,int rowPos){

        this.isWhite = boardUtil.isWhiteAliance(board,columnPos,rowPos);
        AttackingMove(board,columnPos,rowPos);
        CommonMove(board,columnPos,rowPos);

        }
    }

