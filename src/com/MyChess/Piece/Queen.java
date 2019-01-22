package com.MyChess.Piece;

import com.MyChess.Board.Aliance;

public class Queen extends Piece {

    String pieceName = "Q";
    private static int[][][] queenMove = {{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}},
                                      {{-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7}},
                                        {{1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5}, {6, -6}, {7, -7}},
                                      {{-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}, {-6, -6}, {-7, -7}},
                                           {{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}},
                                         {{0,-1},{0,-2},{0,-3},{0,-4},{0,-5},{0,-6},{0,-7}},
                                            {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0}},
                                         {{-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{-6,0},{-7,0}}};
    public Queen(int columnPos,int rowPos, Aliance aliance){

        super(columnPos,rowPos,aliance);
        this.pieceName= this.getAliance().name() + this.pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    @Override
    public void ProvisionalMove(){
        this.isqueenMove = true;
        KQB_Move(queenMove);
    };

    public void  pieceMove(int piecePosition, int destination){

    };
}