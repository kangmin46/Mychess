package com.MyChess.GUI;

import com.MyChess.Board.Board;
import com.MyChess.Board.VirtualBoard;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static com.MyChess.Board.Board.getBoard;
import static com.MyChess.Board.VirtualBoard.getVirtualBoard;

public class MyChess extends JFrame {

    public static ChessLabel[][] jlabel = new ChessLabel[8][8];
    BevelBorder border = new BevelBorder(BevelBorder.RAISED);

    MyChess(){
        JFrame jframe = new JFrame("MyChess Game");
        jframe.setSize(800,800);
        JPanel jPanel = new JPanel();
        Board board = getBoard();
        VirtualBoard virtualboard = getVirtualBoard();


        try {
            jframe.add(jframe, BorderLayout.CENTER);
        }
        catch (IllegalArgumentException e){
        }
        jPanel.setLayout(new GridLayout(8,8));
        for(int j=0;j<8;j++) {  //Create Chess board
            for (int i = 0; i < 8; i++) {
                if(board.getTile()[j][i].isOccupied()){
                    ImageIcon image = new ImageIcon("images/"
                            + board.getTile()[j][i].getTileOnPiece().getPieceName() + ".gif");
                    jlabel[j][i] = new ChessLabel(j,i);
                    jlabel[j][i].setIcon(image);
                    jlabel[j][i].setHorizontalAlignment(JLabel.CENTER);
                }
                else{
                    jlabel[j][i] = new ChessLabel(j,i);
                }
                if (j % 2 == 0) {
                    if(i%2 ==0){
                        board.getTile()[j][i].setTileColor(Color.CYAN);
                        jlabel[j][i].setBackground(Color.CYAN);
                    }
                    else{
                        board.getTile()[j][i].setTileColor(Color.WHITE);
                        jlabel[j][i].setBackground(Color.WHITE);
                    }
                } else {
                    if(i%2 == 0){
                        board.getTile()[j][i].setTileColor(Color.WHITE);
                        jlabel[j][i].setBackground(Color.WHITE);
                    }
                    else{
                        board.getTile()[j][i].setTileColor(Color.CYAN);
                        jlabel[j][i].setBackground(Color.CYAN);
                    }
                }
                jlabel[j][i].setOpaque(true);
                jlabel[j][i].setBorder(border);
                jPanel.add(jlabel[j][i]);
            }
        }
        jframe.add(jPanel);
        jframe.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MyChess();
    }
}