package game.Models;

import java.awt.*;

public class Field {
    private Color color;
    private int rowCount;
    private int columnCount;
    private Apple apple;

    public Field(int rowCount,int columnCount,Color color){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.color = color;
    }
    public void setRowCount(int rowCount){
        this.rowCount = rowCount;
    }
    public void setColumnCount(int columnCount){
        this.columnCount = columnCount;
    }
    public void setColor(Color color){this.color = color;}
    public int getRow(){
        return rowCount;
    }
    public int getCol(){
        return columnCount;
    }
    public Color getColor(){
        return color;
    }
    public Apple getApple(){return apple;}
    public void setApple(Apple apple){this.apple = apple;}
    public boolean isCords(int x,int y) {
        if(x >= 0 && x < this.getCol() && y >= 0 && y < this.getRow()) return true;
        else return false;
    }
}

