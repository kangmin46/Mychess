package com.MyChess.Board;

import javax.sql.PooledConnection;
import java.util.ArrayList;
import java.util.List;

public class Position {
    private static Position position;
    static List<Integer> row_PosList=null;
    static List<Integer> column_PosList=null;
    static List<Integer> virtualRow_PosList =null;
    static List<Integer> virtualColumn_PosList =null;
    public Position(){
        this.row_PosList = new ArrayList<>();
        this.column_PosList = new ArrayList<>();
        this.virtualColumn_PosList = new ArrayList<>();
        this.virtualRow_PosList = new ArrayList<>();
    }

    public static Position getPosition(){
        if(position==null){
            position = new Position();
        }
        return position;
    }

    public static List<Integer> getVirtualRow_PosList() {
        return virtualRow_PosList;
    }

    public static List<Integer> getVirtualColumn_PosList() {
        return virtualColumn_PosList;
    }

    public void setVirtualPosition(int columnPos,int rowPos){
        this.virtualColumn_PosList.add(columnPos);
        this.virtualRow_PosList.add(rowPos);

    }

    public void setPosition(int columnPos, int rowPos){
        this.column_PosList.add(columnPos);
        this.row_PosList.add(rowPos);
    }

    public List<Integer> getRow_PosList() {
        return row_PosList;
    }

    public List<Integer> getColumn_PosList() {
        return  column_PosList;
    }
}
