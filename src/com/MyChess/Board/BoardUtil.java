package com.MyChess.Board;

import com.MyChess.GUI.MyChess;
import com.MyChess.Piece.Pawn;
import com.MyChess.Piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.Position.getPosition;

public class BoardUtil {

    private static Position position= Position.getPosition();
    private static BoardUtil boardUtil = BoardUtil.getBoardUtil();
    private List<Integer> rowPosList = position.getRow_PosList();
    private List<Integer> columnPosList = position.getColumn_PosList();
    private JLabel pastLabel;
    private JLabel presentLabel;
    private Tile pastTile;
    private Tile presentTile;
    boolean isPieceClicked = false;
    private Board board = Board.getBoard();
    private Tile[][] tile = board.getTile();

    public void setTileLabel(int pastCol,int pastRow,int preCol,int preRow ){

        this.pastLabel = MyChess.jlabel[pastCol][pastRow];
        this.presentLabel = MyChess.jlabel[preCol][preRow];
        this.pastTile = board.getTile()[pastCol][pastRow];
        this.presentTile = board.getTile()[preCol][preRow];
    }
    public static int[] getSelectedPiece() {
        return selectedPiece;
    }

    private static int[] selectedPiece = new int[2];
    private Color color;
    BoardUtil(){
    }
    public void getPosition(){
        this.position = Position.getPosition();
    }
    public static BoardUtil getBoardUtil(){
        if(boardUtil==null){
            boardUtil= new BoardUtil();
        }
        return boardUtil;
    }
    public void RequestCandidate(int columnPosition,int rowPosition){

        tile[columnPosition][rowPosition].getTileOnPiece().ProvisionalMove();
    }
    public void ClearTile(){
        if(rowPosList!=null && columnPosList!=null) {
            for (int i = 0; i < rowPosList.size(); i++) {
                color = tile[columnPosList.get(i)][rowPosList.get(i)].getTileColor();
                MyChess.jlabel[columnPosList.get(i)][rowPosList.get(i)].setBackground(color);
                tile[columnPosList.get(i)][rowPosList.get(i)].setCandidateTile(false);
            }
            rowPosList.clear();
            columnPosList.clear();
        }
    }
    public void SelectedPiece(int columnPos,int rowPos){
        selectedPiece[0] = columnPos;
        selectedPiece[1] = rowPos;
    }
    public void ShowCandidateTile(int columnPos,int rowPos){
       
        this.setPieceClicked(true);
        this.SelectedPiece(columnPos,rowPos);
        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        if(!rowPosList.isEmpty() && !columnPosList.isEmpty()) {
            this.ClearTile();
        }
        this.RequestCandidate(columnPos,rowPos);
            for(int i=0;i<rowPosList.size();i++){
                MyChess.jlabel[columnPosList.get(i)]
                        [rowPosList.get(i)].setBackground(Color.orange);
               tile[columnPosList.get(i)]
                        [rowPosList.get(i)].setCandidateTile(true);
            }
    }

    public void setPieceClicked(boolean pieceClicked) {
        isPieceClicked = pieceClicked;
    }

    public boolean isPieceClicked() {
        return isPieceClicked;
    }
    public boolean isEnemy(int columnPos,int rowPos){
        if(board.getTile()[selectedPiece[0]][selectedPiece[1]].getPiece().getAliance()!=
                board.getTile()[columnPos][rowPos].getPiece().getAliance()){
            return  true;
        }
        else{
            return false;
        }
    }
    public void ActiveMove(int columnPos,int rowPos){

        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        if(presentTile.isCandidateTile()){
            System.out.println("Active Move");
            Icon icon = pastLabel.getIcon();
            pastLabel.setIcon(null);
            if(presentTile.isOccupied()){
                presentTile.removePiece();
                presentLabel.setIcon(null);
                //ActiveAttackingMove
            }
            else {
                //ActiveNormalMove
                System.out.println("ActiveNormalMove");
                if(pastTile.getPiece().getPieceName().equals("BP")||
                        pastTile.getPiece().getPieceName().equals("WP")){
                    ((Pawn)pastTile.getPiece()).setFirstMove(false);
                }
            }
            presentTile.setTileOnPiece(pastTile.getTileOnPiece());
            presentLabel.setIcon(icon);
            presentLabel.setHorizontalAlignment(JLabel.CENTER);
            presentTile.setOccupied(true);
            presentTile.getPiece().setcolumnPos(columnPos);
            presentTile.getPiece().setrowPos(rowPos);
            pastTile.setOccupied(false);
            this.isPieceClicked = false;
            pastTile.removePiece();
        }

        else{
        }
        this.ClearTile();
    }

    public void swapTurn(){

    }
    public boolean isCheck(){
        return true;
    }
    public boolean isCheckMate(){
        return true;
    }
}
