package com.MyChess.Piece;

import com.MyChess.Board.Aliance;

public class Bishop extends Piece {

    String pieceName = "B";
    public static int[][][] bishopMove = {{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}},
            {{-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7}},
            {{1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5}, {6, -6}, {7, -7}},
            {{-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}, {-6, -6}, {-7, -7}}};
    public Bishop(int columnPos,int rowPos, Aliance aliance){

        super(columnPos,rowPos,aliance);
        this.pieceName= this.getAliance().name() + this.pieceName;
    }
    @Override
    public void ProvisionalMove(){
        KQB_Move(bishopMove);
    };

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void  pieceMove(int piecePosition, int destination){

    };
}