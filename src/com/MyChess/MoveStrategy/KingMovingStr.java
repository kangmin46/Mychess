package com.MyChess.MoveStrategy;

import com.MyChess.Board.*;
import com.MyChess.Piece.Piece;
import com.MyChess.Piece.Queen;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;
import static com.MyChess.Board.Position.getPosition;

public class KingMovingStr implements MovingStrategy {

    private static final int[][] kingMove = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
    private int canColumnPos;
    private int canRowPos;
    private static Position position= Position.getPosition();
    private boolean isQueenSideCastling = false;
    private boolean isKingSideCastling = false;
    private BoardUtil boardUtil;
    private Piece kingPiece;
    public KingMovingStr(){
        boardUtil = getBoardUtil();
    }
    public void CheckCastling(Board board,int columnPos,int rowPos){ //Castling 판단
        if(rowPos ==4) {
            Piece kingSideRookPiece = board.getTile()[columnPos][rowPos + 3].getPiece();
            Piece queenSideRookPiece = board.getTile()[columnPos][rowPos - 4].getPiece();
            if (!boardUtil.isCheckState()) {   //가능한지 체크
                if (kingSideRookPiece.isFirstMove()) {
                    isJudgeValidCastling(board, kingSideRookPiece);
                }
                if (queenSideRookPiece.isFirstMove()) {
                    isJudgeValidCastling(board, queenSideRookPiece);
                }
            }
        }
    }


    public void isJudgeValidCastling(Board board,Piece rookPiece){ //Castling을 판단하기위한 로직중 하나

        System.out.println("isJudgeValidCastling!!");
        if(rookPiece.getRowPos()==0){
            this.isQueenSideCastling = isValidQueenSideCastling(board,rookPiece);
        }
        else{
           this.isKingSideCastling = isValidKingSideCastling(board,rookPiece);
            //kingside
        }
    }
    public boolean isValidKingSideCastling(Board board,Piece rookPiece){
        int emptyNumber=0;
        for(int i=5;i<7;i++){
            Tile tile =board.getTile()[rookPiece.getColumnPos()][i];
            if(rookPiece.getAliance()== Aliance.W){
                if(!tile.isOccupied() && !tile.isbTemMove()){
                    emptyNumber++;
                }
            }
            else{
                if(!tile.isOccupied() && !tile.iswTemMove()){
                    emptyNumber++;
                }
            }
        }
        if(emptyNumber==2){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean isValidQueenSideCastling(Board board,Piece rookPiece){
        int emptyNumber=0;
        for(int i=1;i<4;i++){
            Tile tile =board.getTile()[rookPiece.getColumnPos()][i];
            if(rookPiece.getAliance()== Aliance.W){
                if(!tile.isOccupied() && !tile.isbTemMove()){
                    emptyNumber++;
                }
            }
            else{
                if(!tile.isOccupied() && !tile.iswTemMove()){
                    emptyNumber++;
                }
            }
        }
        if(emptyNumber==3){
            return true;
        }
        else{
            return false;
        }

    }

    public void ShowCastlingTile(int columnPos,int rowPos){
        System.out.println("ShowCastling!!");
        if(this.isQueenSideCastling){
            position.setPosition(columnPos,rowPos-2);
        }
        if(this.isKingSideCastling){
            position.setPosition(columnPos,rowPos+2);
        }

    }


    public void Move(Board board,int columnPos,int rowPos){
        System.out.println("!!!!!!!!!!!!KIngMove!!!!!!!!!!");
        this.kingPiece =board.getTile()[columnPos][rowPos].getPiece();
        if(boardUtil.isFirstRequest()) {
            if (kingPiece.isFirstMove()) {
                CheckCastling(board, columnPos, rowPos);
            }
            ShowCastlingTile(columnPos, rowPos);
        }
            for (int i = 0; i < 8; i++) {
                canColumnPos = columnPos + kingMove[i][0];
                canRowPos = rowPos + kingMove[i][1];
                if (boardUtil.isValidMove(canColumnPos, canRowPos)) {
                    Tile canTile = board.getTile()[canColumnPos][canRowPos];
                    if(kingPiece.getPieceName().charAt(0)=='W'){
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
                        if (boardUtil.isEnemy(kingPiece, canTile.getPiece())) {
                            boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                        }
                    } else {
                        boardUtil.saveCandidate(columnPos,rowPos,canColumnPos,canRowPos);
                    }
                }
            }
    }

    public void setQueenSideCastling(boolean queenSideCastling) {
        isQueenSideCastling = queenSideCastling;
    }

    public void setKingSideCastling(boolean kingSideCastling) {
        isKingSideCastling = kingSideCastling;
    }

    public boolean isQueenSideCastling() {
        return isQueenSideCastling;
    }

    public boolean isKingSideCastling() {
        return isKingSideCastling;
    }
}
