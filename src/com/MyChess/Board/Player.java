package com.MyChess.Board;

public class Player {

    private final String playerColor;
    private boolean isTurn;
    private Player player;

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public boolean isTurn() {
        return isTurn;
    }

    Player(String playerColor, boolean isTurn){
        this.playerColor = playerColor;
        this.isTurn =isTurn;
        player=this;
    }

    public Player getPlayer() {
        return player;
    }
}
