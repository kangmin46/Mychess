package com.MyChess.Board;

import com.MyChess.MoveStrategy.*;
import com.MyChess.Piece.Knight;
import com.MyChess.Piece.Pawn;
import com.MyChess.Piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.MyChess.Board.VirtualBoard.getVirtualBoard;
import static com.MyChess.GUI.MyChess.jlabel;

public class BoardUtil {

    private static Position position= Position.getPosition();
    private static BoardUtil boardUtil = new BoardUtil();
    private List<Integer> rowPosList = position.getRow_PosList();
    private List<Integer> columnPosList = position.getColumn_PosList();
    private MovingStrategy movingStrategy;
    private JLabel pastLabel;
    private JLabel presentLabel;
    private Tile pastTile;
    private Tile presentTile;
    boolean isPieceClicked = false;
    private Board board;
    private Tile[][] tile;

    private boolean isPawnMove =true;
    private VirtualBoard virtualBoard = getVirtualBoard();
    private Tile[][] virtualTile =virtualBoard.getTile();

    public void setTileLabel(int pastCol,int pastRow,int preCol,int preRow ){

        this.pastLabel = jlabel[pastCol][pastRow];
        this.presentLabel = jlabel[preCol][preRow];
        this.pastTile = board.getTile()[pastCol][pastRow];
        this.presentTile = board.getTile()[preCol][preRow];
    }
    /*public boolean isFutureMove(Piece piece, int futureCol,int futureRow){
        //true 일때 오렌지색 표시
        virtualBoard.getTile()[futureCol][futureRow]

    }*/

    private static int[] selectedPiece = new int[2];
    private Color color;
    BoardUtil(){
        this.board = Board.getBoard();
        this.tile= board.getTile();
        System.out.println("BoardUtil 생성");
    }
    public static BoardUtil getBoardUtil(){
        return boardUtil;
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void RequestCandidate(int columnPosition, int rowPosition){

        String pieceName = tile[columnPosition][rowPosition].getTileOnPiece().getPieceName();

        switch (pieceName.charAt(1)){
            case 'P':
                setMovingStrategy(new PawnMovingStr());
                break;
            case 'N':
                setMovingStrategy(new KnightMovingStr());
                break;
            case 'K':
                setMovingStrategy(new KingMovingStr());
                break;
            case 'Q':
                setMovingStrategy(new QueenMovingStr());
                break;
            case 'R':
                setMovingStrategy(new RookMovingStr());
                break;
            case 'B':
                setMovingStrategy(new BishopMovingStr());
                break;
        }
        this.movingStrategy.Move(columnPosition,rowPosition);
    }
    public void ClearTile(){
        if(rowPosList!=null && columnPosList!=null) {
            for (int i = 0; i < rowPosList.size(); i++) {
                color = tile[columnPosList.get(i)][rowPosList.get(i)].getTileColor();
                jlabel[columnPosList.get(i)][rowPosList.get(i)].setBackground(color);
                tile[columnPosList.get(i)][rowPosList.get(i)].setCandidateTile(false);
            }
            rowPosList.clear();
            columnPosList.clear();
        }

    }
    public void InitializeTemMove(){
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                virtualTile[j][i].setbTemMove(false);
                virtualTile[j][i].setwTemMove(false);
            }
        }
    }

    public void SelectedPiece(int columnPos,int rowPos){
        selectedPiece[0] = columnPos;
        selectedPiece[1] = rowPos;
    }

    public boolean isValidMove( int columnDestination,int rowDestination){

        if( (rowDestination>=0 && rowDestination<8) &&(columnDestination>=0 && columnDestination<8 )){
            return true;
        }
        else{
            return false;
        }
    }

    public void ShowCandidateTile(int columnPos,int rowPos){
       
        this.setPieceClicked(true);
        this.SelectedPiece(columnPos,rowPos);
        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        if(!rowPosList.isEmpty() && !columnPosList.isEmpty()) {
            this.ClearTile();
        }
        this.isPawnMove=false;
        this.RequestCandidate(columnPos,rowPos);
            for(int i=0;i<rowPosList.size();i++){
                Piece piece = tile[columnPos][rowPos].getPiece();
                    jlabel[columnPosList.get(i)]
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


    public void deleteTemMove(Tile presentTile,int columnPos,int rowPos){
        this.RequestCandidate(columnPos,rowPos);
            if (presentTile.getPiece().getPieceName().charAt(0) == 'W') {
                for (int i = 0; i < columnPosList.size(); i++) {
                    tile[columnPosList.get(i)][rowPosList.get(i)].setwTemMove(false);
                }
            } else {
                for (int i = 0; i < columnPosList.size(); i++) {
                    tile[columnPosList.get(i)][rowPosList.get(i)].setbTemMove(false);
                }
            }
    }

    public boolean isPawnMove() {
        return isPawnMove;
    }

    public void settingTemMove(){

        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(tile[j][i].isOccupied()){
                    this.RequestCandidate(j,i);
                    if(tile[j][i].getPiece().getPieceName().charAt(0)=='W') {
                        for (int k = 0; k < columnPosList.size(); k++) {
                            tile[columnPosList.get(k)][rowPosList.get(k)].setwTemMove(true);
                        }
                    }
                    else{
                        for (int k = 0; k < columnPosList.size(); k++) {
                        tile[columnPosList.get(k)][rowPosList.get(k)].setbTemMove(true);
                    }
                    }
                }
                this.ClearTile();
            }
        }
    }

    public void ActiveMove(int columnPos,int rowPos){

        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        deleteTemMove(pastTile,selectedPiece[0],selectedPiece[1]);
        if(presentTile.isOccupied()){
            deleteTemMove(presentTile,columnPos,rowPos);
        }
            Icon icon = pastLabel.getIcon();
                pastLabel.setIcon(null);
                presentTile.removePiece();
                presentLabel.setIcon(null);
                if(pastTile.getPiece().getPieceName().equals("BP")||
                        pastTile.getPiece().getPieceName().equals("WP")){
                    ((Pawn)pastTile.getPiece()).setFirstMove(false);
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
            this.ClearTile();
            this.InitializeTemMove();
            this.isPawnMove=true;
            this.settingTemMove();
            this.Check(columnPos,rowPos);

    }
    public void Check(int columnPos,int rowPos){
        if(this.isCheck(columnPos,rowPos)){
            System.out.println("!!!!!Check Event !!!!");
        }
    }
    public void SwapTurn(){

    }
    public boolean isEnemy(Piece piece ,Piece piece2 ){
        if(piece.getAliance() != piece2.getAliance()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isCheck(int columnPos,int rowPos){
        this.RequestCandidate(columnPos,rowPos);
        for(int i=0;i<columnPosList.size();i++){
            int colPos = columnPosList.get(i);
            int rPos = rowPosList.get(i);
            if(tile[colPos][rPos].isOccupied()){
                if(tile[colPos][rPos].getPiece().getPieceName().charAt(1) =='K'){
                    return true;
                }
            }
        }
        return false;
    }
}
