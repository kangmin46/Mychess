package com.MyChess.Board;

import com.MyChess.Piece.Piece;

import java.awt.*;

public class Tile {


    int rowPos;
    int columnPos;
    private boolean isOccupied = false;
    private boolean candidateTile = false;
    private Piece tileOnPiece;
    String emptyTIle = "*";
    Color tileColor;

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

    public Tile(int columnPos,int rowPos){
        this.rowPos = rowPos;
        this.columnPos = columnPos;
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
