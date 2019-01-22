package com.MyChess.GUI;

import com.MyChess.Board.Board;
import com.MyChess.Board.BoardUtil;
import com.MyChess.Board.Position;
import com.MyChess.Board.Tile;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessLabel extends JLabel {

    private int rowPos;
    private int columnPos;
    private Color color;
    private int[] selectedPice;
    private Board board = Board.getBoard();
    BoardUtil boardUtil = BoardUtil.getBoardUtil();

    private boolean isColorOrange = false;


    public int getrowPos() {
        return rowPos;
    }

    public int getcolumnPos() {
        return columnPos;
    }

    public void setColorOrange(boolean colorOrange) {
        isColorOrange = colorOrange;
    }

    public boolean isColorOrange() {
        return isColorOrange;
    }

    public ChessLabel(int columnPos,int rowPos){
        this.rowPos = rowPos;
        this.columnPos = columnPos;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)   {
                if (boardUtil.isPieceClicked()) {
                    if(board.getTile()[columnPos][rowPos].isCandidateTile()){
                        if(board.getTile()[columnPos][rowPos].isOccupied()){
                            if(boardUtil.isEnemy(columnPos,rowPos)){
                                //Attacking Move
                                boardUtil.ActiveMove(columnPos, rowPos);
                            }
                            else{
                                    boardUtil.ShowCandidateTile(columnPos, rowPos);
                            }
                        }
                        else{
                            System.out.println("Normal Move");
                            boardUtil.ActiveMove(columnPos, rowPos);
                        }
                    }
                    else{
                        if(board.getTile()[columnPos][rowPos].isOccupied()) {
                            boardUtil.ShowCandidateTile(columnPos, rowPos);
                        }
                        else {
                           boardUtil.ClearTile();
                        }
                    }
                }
                else{
                    if(board.getTile()[columnPos][rowPos].isOccupied()) {
                        boardUtil.ShowCandidateTile(columnPos, rowPos);
                    }
                }
            }
        });

    }
    public void setColor(Color color) {
        this.color = color;
    }
}
