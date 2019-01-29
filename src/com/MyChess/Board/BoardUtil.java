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
    private List<Integer> virtualColumnPos = position.getVirtualColumn_PosList();
    private List<Integer> virtualRowPos = position.getVirtualRow_PosList();
    private boolean isFirstRequest =true;
    private MovingStrategy movingStrategy;
    private JLabel pastLabel;
    private JLabel presentLabel;
    private Tile pastTile;
    private Tile presentTile;
    boolean isPieceClicked = false;
    private Board board;
    private Tile[][] tile;

    private boolean isPawnMove =true;
    private VirtualBoard virtualBoard;
    private Tile[][] virtualTile;

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
        this.virtualBoard = getVirtualBoard();
        this.virtualTile =virtualBoard.getTile();
    }


    public static BoardUtil getBoardUtil(){
        return boardUtil;
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void RequestCandidate(Board board,int columnPosition, int rowPosition,boolean isFirstRequest){

        String pieceName = board.getTile()[columnPosition][rowPosition].getTileOnPiece().getPieceName();
        this.isFirstRequest = isFirstRequest;
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
        if(isFirstRequest){
            movingStrategy.Move(board,columnPosition,rowPosition);
            System.out.println("Board 인자로 move");
        }
        else{
            movingStrategy.Move(virtualBoard,columnPosition,rowPosition);
            System.out.println("virtualBoard인자로 Move");
        }
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
    public void InitializeTemMove(Board board){
        Tile tile[][] = board.getTile();
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                tile[j][i].setbTemMove(false);
                tile[j][i].setwTemMove(false);
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

    public void setFirstRequest(boolean firstRequest) {
        isFirstRequest = firstRequest;
    }

    public void ShowCandidateTile(int columnPos, int rowPos){
       
        this.setPieceClicked(true);
        this.SelectedPiece(columnPos,rowPos);
        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        if(!rowPosList.isEmpty() && !columnPosList.isEmpty()) {
            this.ClearTile();
        }
        this.isPawnMove=false;
        this.RequestCandidate(board,columnPos,rowPos,true);
            for(int i=0;i<rowPosList.size();i++){
                    jlabel[columnPosList.get(i)]
                            [rowPosList.get(i)].setBackground(Color.orange);
                    tile[columnPosList.get(i)]
                            [rowPosList.get(i)].setCandidateTile(true);
            }
            setFirstRequest(true);
    }

    public void setPieceClicked(boolean pieceClicked) {
        isPieceClicked = pieceClicked;
    }

    public boolean isPieceClicked() {
        return isPieceClicked;
    }


    public void deleteTemMove(Tile presentTile,int columnPos,int rowPos){
        this.RequestCandidate(virtualBoard,columnPos,rowPos,false);
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

    public void ClearList(){
        virtualColumnPos.clear();
        virtualRowPos.clear();
    }

    public void settingTemMove(Board board){
        int L=0;
        this.InitializeTemMove(board);
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                Tile tile[][] =board.getTile();
                if(tile[j][i].isOccupied()){
                    this.RequestCandidate(board,j,i,false);
                    if(tile[j][i].getPiece().getPieceName().charAt(0)=='W') {
                        for (int k = 0; k < virtualColumnPos.size(); k++) {
                            tile[virtualColumnPos.get(k)][virtualRowPos.get(k)].setwTemMove(true);
                         //   System.out.println(virtualColumnPos.get(k)+" / "+virtualRowPos.get(k)+": white ->True");
                        }
                    }
                    else{
                        for (int k = 0; k < virtualColumnPos.size(); k++) {
                            tile[virtualColumnPos.get(k)][virtualRowPos.get(k)].setbTemMove(true);
                            System.out.println(virtualColumnPos.get(k)+" / "+virtualRowPos.get(k)+": Black ->True");
                    }
                    }
                }
                this.ClearList();
                L++;
            }
        }
        this.isFirstRequest =true;
        System.out.println("SettingMove 끝");
    }

    public void ActiveMove(int columnPos,int rowPos){

        this.setTileLabel(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
        //deleteTemMove(pastTile,selectedPiece[0],selectedPiece[1]);
        /*if(presentTile.isOccupied()){
            deleteTemMove(presentTile,columnPos,rowPos);
        }*/
            Icon icon = pastLabel.getIcon();
            pastLabel.setIcon(null);
            presentTile.removePiece();
            presentLabel.setIcon(null);
            if(pastTile.getPiece().getPieceName().equals("BP")||
                        pastTile.getPiece().getPieceName().equals("WP")){
                    ((Pawn)pastTile.getPiece()).setFirstMove(false);
                    virtualBoard.getTile()[selectedPiece[0]][selectedPiece[1]].getPiece().setFirstMove(false);
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
        System.out.println("---Active하고 Setting TemMove 시작 -- ");
            this.VirtualActiveMove(selectedPiece[0],selectedPiece[1],columnPos,rowPos);
            this.settingTemMove(virtualBoard);
            this.Check(columnPos,rowPos);
            virtualBoard.printBoard();


    }
    public void VirtualActiveMove(int pastCol,int pastRow,int columnPos,int rowPos){

        Tile pastTile= virtualBoard.getTile()[pastCol][pastRow];
        Tile presentTile= virtualBoard.getTile()[columnPos][rowPos];
        presentTile.removePiece();
        presentTile.setPiece(pastTile.getPiece());
        presentTile.getPiece().setcolumnPos(columnPos);
        presentTile.getPiece().setrowPos(rowPos);
        pastTile.setOccupied(false);
        pastTile.removePiece();

    }
    public void Check(int columnPos,int rowPos){
        if(this.isCheck(columnPos,rowPos)){
            System.out.println("!!!!!Check Event !!!!");
        }
    }
    public boolean isEnemy(Piece piece ,Piece piece2 ){
        if(piece.getAliance() != piece2.getAliance()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isVirtualCheck(VirtualBoard virtualBoard,char color){
        Tile tile;

        if(color=='W'){
            tile = this.FindKing(virtualBoard,'W');
            if(tile.isbTemMove()){
                return true;
            }
        }
        else{
            tile = this.FindKing(virtualBoard,'B');
            if(tile.iswTemMove()){
                return true;
            }
        }
        return false;
    }

    public Tile FindKing(VirtualBoard virtualBoard , char color){
        String king;
        Tile kingTile =null;
        if(color =='W') {
            king ="WK";
           }
        else{
            king ="BK";
        }
           Loop: for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if(virtualBoard.getTile()[j][i].isOccupied()) {
                        if (virtualBoard.getTile()[j][i].getPiece().getPieceName().equals(king)) {
                            kingTile = virtualBoard.getTile()[j][i];
                            break Loop;
                        }
                    }
                }
            }
        return kingTile;
    }
    public boolean isCheck(int columnPos,int rowPos){
        System.out.println("isCheck");
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

    public void saveCandidate(int columnPos, int rowPos, int canColumnPos, int canRowPos){
        //

        if (this.isFirstRequest){
            if(VirtualBoard.isVirtualMove(columnPos,rowPos,canColumnPos,canRowPos)){
                position.setPosition(canColumnPos,canRowPos);
                this.isFirstRequest = true;
            }
        }
        else{
            position.setVirtualPosition(canColumnPos,canRowPos);
            System.out.println("VirtualPositon 저장!!");
        }

    }
}
