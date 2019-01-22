package com.MyChess.Board;



import java.util.HashMap;
import java.util.Map;
import com.MyChess.Piece.*;

import static com.MyChess.Board.Aliance.B;
import static com.MyChess.Board.Aliance.W;


public class Board {
    private static Board board = null;
    private Tile[][] tile = new Tile[8][8];
    private Board() {

        CreateEmptyTile();
        CreateHeroPiece();
        CreatePawnPiece();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(tile[i][j].isOccupied()) {
                        System.out.print(tile[i][j].getTileOnPiece().getPieceName()+" ");
                    }
                    else{
                        System.out.print(tile[i][j].getEmptyTIle()+" ");
                    }
                }
                System.out.println();
            }
    }

    public void CreateEmptyTile() {
        for(int j=0;j<8;j++) {
            for (int i = 0; i < 8; i++) {
                tile[j][i] = new Tile(j,i);
            }
        }
    }
    public void CreateHeroPiece(){

        tile[0][0].setTileOnPiece(new Rook(0,0,B));
        tile[0][7].setTileOnPiece( new Rook(0,7,B));
        tile[0][1].setTileOnPiece(new Knight(0,1,B));
        tile[0][6].setTileOnPiece(new Knight(0,6,B));
        tile[0][2].setTileOnPiece(new Bishop(0,2,B));
        tile[0][5].setTileOnPiece( new Bishop(0,5,B));
        tile[0][3].setTileOnPiece( new Queen(0,3,B));
        tile[0][4].setTileOnPiece(new King(0,4,B));
        // BLACK
        tile[7][0].setTileOnPiece(new Rook(7,0,W));
        tile[7][7].setTileOnPiece(new Rook(7,7,W));
        tile[7][1].setTileOnPiece(new Knight(7,1,W));
        tile[7][6].setTileOnPiece(new Knight(7,6,W));
        tile[7][2].setTileOnPiece(new Bishop(7,2,W));
        tile[7][5].setTileOnPiece( new Bishop(7,5,W));
        tile[7][3].setTileOnPiece(new Queen(7,3,W));
        tile[7][4].setTileOnPiece(new King(7,4,W));
        //WHITE
        for(int i = 0; i<8; i++){
            tile[0][i].setOccupied(true);
            tile[7][i].setOccupied(true);}
        //W
    }
    public void CreatePawnPiece(){
        for(int i = 0; i<8; i++){
            tile[1][i].setTileOnPiece(new Pawn(1,i,B));
            tile[1][i].setOccupied(true);
            tile[6][i].setTileOnPiece( new Pawn(6,i,W));
            tile[6][i].setOccupied(true);
        }
    }

    public static Board getBoard(){
        if(board == null){

           board = new Board();

        }
        return board;
    }

    public Tile[][] getTile() {
        return tile;
    }

}
