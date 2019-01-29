package com.MyChess.Board;

public class Player {


    private boolean isWhiteTurn;

    public void setWhiteTurn(boolean whiteTurn) {
        isWhiteTurn = whiteTurn;
    }

    private static Player player;

    public void setTurn(boolean turn) {
        isWhiteTurn = turn;
    }


    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    Player (boolean isWhiteTurn){

        this.isWhiteTurn =isWhiteTurn;
        player=this;
    }

    public static Player getPlayer() {
        if ( player==null ){

            player=new Player(true);
        }
        return player;
    }
}
