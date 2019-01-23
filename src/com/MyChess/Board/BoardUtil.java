package com.MyChess.Board;

import com.MyChess.MoveStrategy.*;
import com.MyChess.Piece.Knight;
import com.MyChess.Piece.Pawn;
import com.MyChess.Piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    public void setTileLabel(int pastCol,int pastRow,int preCol,int preRow ){

        this.pastLabel = jlabel[pastCol][pastRow];
        this.presentLabel = jlabel[preCol][preRow];
        this.pastTile = board.getTile()[pastCol][pastRow];
        this.presentTile = board.getTile()[preCol][preRow];
    }

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
        System.out.println(movingStrategy);
        this.movingStrategy = movingStrategy;
    }

    public void RequestCandidate(int columnPosition, int rowPosition){
        System.out.println("여기");
        String pieceName = tile[columnPosition][rowPosition].getTileOnPiece().getPieceName();
        System.out.println("여기까지");
        System.out.println(pieceName.charAt(1));
        switch (pieceName.charAt(1)){
            case 'P':
                setMovingStrategy(new PawnMovingStr());;
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
        System.out.println("Moving 끝");
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
        this.RequestCandidate(columnPos,rowPos);
            for(int i=0;i<rowPosList.size();i++){
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

    public void ActiveMove(int columnPos,int rowPos){

        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        if(presentTile.isCandidateTile()){
            Icon icon = pastLabel.getIcon();
            pastLabel.setIcon(null);
            if(presentTile.isOccupied()){
                presentTile.removePiece();
                presentLabel.setIcon(null);
                //ActiveAttackingMove
            }
            else {
                //ActiveNormalMove
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
    public boolean isEnemy(Piece piece ,Piece piece2 ){
        if(piece.getAliance() != piece2.getAliance()){
            return true;
        }
        else{
            return false;
        }
    }
}
