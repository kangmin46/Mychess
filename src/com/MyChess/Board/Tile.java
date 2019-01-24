package com.MyChess.Board;

import com.MyChess.Piece.Piece;

import java.awt.*;

public class Tile {


    int rowPos;
    int columnPos;
    private boolean isOccupied;
    private boolean candidateTile;
    private Piece tileOnPiece;
    private boolean wTemMove;
    private boolean bTemMove;
    String emptyTIle = "*";
    Color tileColor;

    public Tile(int columnPos,int rowPos){
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.isOccupied =false;
        this.candidateTile =false;
        this.wTemMove =false;
        this.bTemMove =false;
    }


    public void setwTemMove(boolean wTemMove) {
        this.wTemMove = wTemMove;
    }

    public void setbTemMove(boolean bTemMove) {
        this.bTemMove = bTemMove;
    }

    public boolean iswTemMove() {
        return wTemMove;
    }

    public boolean isbTemMove() {
        return bTemMove;
    }



    public void setCandidateTile(boolean candidateTile) {
        this.candidateTile = candidateTile;
    }
    public void removePiece(){
        if(this.isOccupied){
            this.tileOnPiece = null;
        }
    }

    public boolean isCandidateTile() {
        return candidateTile;
    }

    public Piece getTileOnPiece() {
        return tileOnPiece;
    }
    public int getRowPos() {
        return rowPos;
    }

    public int getColumnPos() {
        return columnPos;
    }

    public void setTileOnPiece(Piece tileOnPiece) {
        this.tileOnPiece = tileOnPiece;
    }

    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public String getEmptyTIle() {
        return emptyTIle;
    }



    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

    public boolean isOccupied(){
        return  this.isOccupied;
    }

    public void setPiece(Piece piece){
        this.tileOnPiece = piece;
        this.isOccupied = true;
    }

    public Piece getPiece(){
        return tileOnPiece;
    }
}
