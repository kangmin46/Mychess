package com.MyChess.MoveStrategy;

import com.MyChess.Board.Board;

public interface MovingStrategy {
    public void Move(Board board,int columnPos,int rowPos);
}
