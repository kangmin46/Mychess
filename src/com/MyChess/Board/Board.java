package com.MyChess.Board;

import com.MyChess.Piece.*;

import static com.MyChess.Board.Aliance.B;
import static com.MyChess.Board.Aliance.W;


public class Board {
    private static Board board;
    private Tile[][] tile = new Tile[8][8];
    private Board() {
        System.out.println("Board생성");
        new Player("W",true);
        CreateEmptyTile(tile);
        CreateHeroPiece(tile);
        CreatePawnPiece(tile);


    }

    public void CreateEmptyTile(Tile tile[][]) {
        for(int j=0;j<8;j++) {
            for (int i = 0; i < 8; i++) {
                tile[j][i] = new Tile(j,i);
            }
        }
    }
    public void CreateHeroPiece(Tile tile[][]){

        tile[0][0].setTileOnPiece(new Rook(0,0,B,"R"));
        tile[0][7].setTileOnPiece( new Rook(0,7,B,"R"));
        tile[0][1].setTileOnPiece(new Knight(0,1,B,"N"));
        tile[0][6].setTileOnPiece(new Knight(0,6,B,"N"));
        tile[0][2].setTileOnPiece(new Bishop(0,2,B,"B"));
        tile[0][5].setTileOnPiece( new Bishop(0,5,B,"B"));
        tile[0][3].setTileOnPiece( new Queen(0,3,B,"Q"));
        tile[0][4].setTileOnPiece(new King(0,4,B,"K"));
        // BLACK
        tile[7][0].setTileOnPiece(new Rook(7,0,W,"R"));
        tile[7][7].setTileOnPiece(new Rook(7,7,W,"R"));
        tile[7][1].setTileOnPiece(new Knight(7,1,W,"N"));
        tile[7][6].setTileOnPiece(new Knight(7,6,W,"N"));
        tile[7][2].setTileOnPiece(new Bishop(7,2,W,"B"));
        tile[7][5].setTileOnPiece( new Bishop(7,5,W,"B"));
        tile[7][3].setTileOnPiece(new Queen(7,3,W,"Q"));
        tile[7][4].setTileOnPiece(new King(7,4,W,"K"));
        //WHITE
        for(int i = 0; i<8; i++){
            tile[0][i].setOccupied(true);
            tile[7][i].setOccupied(true);}
        //W
    }
    public void CreatePawnPiece(Tile tile[][]){
        for(int i = 0; i<8; i++){
            tile[1][i].setTileOnPiece(new Pawn(1,i,B,"P"));
            tile[1][i].setOccupied(true);
            tile[6][i].setTileOnPiece( new Pawn(6,i,W,"P"));
            tile[6][i].setOccupied(true);
        }
    }

    public static Board getBoard(){
        if ( board==null ){

            board=new Board();
        }
        return board;
    }

    public Tile[][] getTile() {
        return tile;
    }

}
