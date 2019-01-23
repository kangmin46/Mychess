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

    BoardUtil boardUtil = BoardUtil.getBoardUtil();


    public ChessLabel(int columnPos,int rowPos){
        this.rowPos = rowPos;
        this.columnPos = columnPos;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)   {
                Board board = Board.getBoard();
                if (boardUtil.isPieceClicked()) {
                    if(board.getTile()[columnPos][rowPos].isCandidateTile()){
                            boardUtil.ActiveMove(columnPos, rowPos);
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
}
