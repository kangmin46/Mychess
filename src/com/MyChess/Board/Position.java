package com.MyChess.Board;

import javax.sql.PooledConnection;
import java.util.ArrayList;
import java.util.List;

public class Position {
    private static Position position;
    static List<Integer> row_PosList=null;
    static List<Integer> column_PosList=null;
    public Position(){
        this.row_PosList = new ArrayList<>();
        this.column_PosList = new ArrayList<>();
    }

    public static Position getPosition(){
        if(position==null){
            position = new Position();
        }
        return position;
    }

    public void setRow_Position(int rowPos) {
        this.row_PosList.add(rowPos);
    }

    public void setColumn_Position(int columnPos) {
        this. column_PosList.add(columnPos);
    }

    public List<Integer> getRow_PosList() {
        return row_PosList;
    }

    public List<Integer> getColumn_PosList() {
        return  column_PosList;
    }
}
