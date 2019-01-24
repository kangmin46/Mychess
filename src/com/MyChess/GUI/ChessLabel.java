package com.MyChess.GUI;

import com.MyChess.Board.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.MyChess.Board.BoardUtil.getBoardUtil;

public class ChessLabel extends JLabel {

    private int rowPos;
    private int columnPos;

    BoardUtil boardUtil = getBoardUtil();


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
