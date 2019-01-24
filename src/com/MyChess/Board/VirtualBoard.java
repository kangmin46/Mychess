package com.MyChess.Board;

import static com.MyChess.Board.Board.getBoard;

public class VirtualBoard {
    private Tile[][] tile = new Tile[8][8];
    private static VirtualBoard virtualBoard;

    VirtualBoard(){
        Board board =getBoard();
        board.CreateEmptyTile(tile);
        board.CreateHeroPiece(tile);
        board.CreatePawnPiece(tile);

    }

    public void printBoard(){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(tile[i][j].isbTemMove()){
                    System.out.printf("T  ");
                }
                else{
                    System.out.printf("F  ");
                }
            }
            System.out.println();
        }

    }
    public static VirtualBoard getVirtualBoard(){
        if ( virtualBoard==null ){

            virtualBoard=new VirtualBoard();
        }
        return virtualBoard;
    }

    public Tile[][] getTile() {
        return tile;
    }
}
