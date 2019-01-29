package com.MyChess.Board;

import com.MyChess.Piece.Piece;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.BoardUtil.getBoardUtil;

public class VirtualBoard extends Board {
    private Tile[][] tile = new Tile[8][8];
    private static VirtualBoard virtualBoard;
    private static BoardUtil boardutil = getBoardUtil();

    VirtualBoard(){
        super.CreateEmptyTile(tile);
        super.CreateHeroPiece(tile);
        super.CreatePawnPiece(tile);
        printBoard();
    }

    public void printBoard(){

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(tile[j][i].isbTemMove()){
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

    public static boolean isVirtualMove(int columnPos,int rowPos,int canColumnPos,int canRowPos){

        //VirtualMove 구현
        Tile tile =virtualBoard.getTile()[columnPos][rowPos];
        Tile Cantile = virtualBoard.getTile()[canColumnPos][canRowPos];
        boolean isVirtualMove=true;
        boolean CanOccupied =false;
        Piece tempiece=null;

        if(Cantile.isOccupied()){
            tempiece = virtualBoard.getTile()[canColumnPos][canRowPos].getPiece();
            CanOccupied =true;
        }

        Cantile.setPiece(tile.getPiece());
        Cantile.getPiece().setcolumnPos(canColumnPos);
        Cantile.getPiece().setcolumnPos(canRowPos); //말을 가상으로옮김
        tile.setOccupied(false);
        tile.removePiece();

        boardutil.settingTemMove(virtualBoard);

        if(Cantile.getPiece().getPieceName().charAt(0)=='W') {
            if(boardutil.isVirtualCheck(virtualBoard, 'W')){
               isVirtualMove=false;
            }
        }
        else {
            if (boardutil.isVirtualCheck(virtualBoard, 'B')) {
                isVirtualMove = false;
            }
        }                                 //움직인 이후 체크상황이면 움직이면 안되므로 False반환

        tile.setOccupied(true);
        tile.setPiece(Cantile.getPiece());
        tile.getPiece().setcolumnPos(columnPos); //말을 원상복구함
        tile.getPiece().setcolumnPos(rowPos);
        Cantile.removePiece();
        if(CanOccupied){
            Cantile.setPiece(tempiece);
        }
        else{
            Cantile.setOccupied(false);
        }
        virtualBoard.printBoard();
        return isVirtualMove;
    }
}
