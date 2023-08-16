package game.Models;

import static game.Utilities.DrawingHelpers.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;
import static game.Params.*;
public class Snake {
    private Field field;
    private int dx,dy;
    private final ArrayList<Point> body = new ArrayList<>();
    private Color colorAlive;
    private Color colorDead;
    private int headID;
    private boolean IsDead = true;
    private int score;
    public Snake(int x, int y, String direction, Field field, int length){
        this.setDirection(direction);
        headID = 0;
        for(int i = 0 ; i<length;i++){
            body.add(new Point(x,y));
        }
        this.colorAlive = Color.RED;
        this.colorDead = Color.GRAY;
        IsDead=false;
        score = 0;
        this.field = field;

    }
    public void setPos(int x,int y,int length){
        for(int i = 0 ; i<length;i++){
            body.add(new Point(x,y));
        }
    }
    public void setField(Field field){
        this.field = field;
    }
    public boolean isDead(){
        return IsDead;
    }
    public void setColorAlive(Color color){
        colorAlive = color;
    }
    public void setColorDead(Color color){
        colorDead = color;
    }
    public ArrayList<Point> getBody(){
        return body;
    }
    public Point getHead(){
        return body.get(headID);
    }
    public int getScore(){
        return score;
    }
    public Color getColor(){
        if(isDead())
            return colorDead;
        return colorAlive;
    }
    public void move(){
        var head = getHead();
        int nextX = head.x+dx;
        int nextY = head.y+dy;
        if(IsDead)
            return;
        if(!field.isCords(nextX,nextY) || areCords(nextX,nextY)) {
            IsDead = true;
            return;
        }
        if(field.getApple().getCol()==nextX && field.getApple().getRow()==nextY){
            score++;
            body.add(new Point(nextX,nextY));
            headID++;
            head = getHead();
            head.x = nextX;
            head.y = nextY;
            field.getApple().changeLocation();
        }
        else{
            headID = (headID+1)%(body.size());
            head = getHead();
            head.x = nextX;
            head.y = nextY;
        }

    }
    public void setDirection(String newDirection){
        if(!newDirection.equals("right") && !newDirection.equals("left") && !newDirection.equals("up") && !newDirection.equals("down")){
            throw new IllegalArgumentException("Wrong direction name");
        }
        else{
            switch (newDirection) {
                case "left" -> {
                    if (dx==1 && dy==0) throw new IllegalStateException("Cannot change direction this way");
                    else{
                        dx = -1;
                        dy = 0;
                    }
                }
                case "right" -> {
                    if (dx==-1 && dy==0) throw new IllegalStateException("Cannot change direction this way");
                    else {
                        dx = 1;
                        dy = 0;
                    }
                }
                case "up" -> {
                    if (dx==0 && dy==1) throw new IllegalStateException("Cannot change direction this way");
                    else {
                        dx = 0;
                        dy = -1;
                    }
                }
                case "down" -> {
                    if (dx==0 && dy==-1) throw new IllegalStateException("Cannot change direction this way");
                    else {
                        dx = 0;
                        dy = 1;
                    }
                }
            }
        }
    }
    public void setDirection(int KeyCode){
        switch (KeyCode) {
            case VK_LEFT -> setDirection("left");
            case VK_RIGHT -> setDirection("right");
            case VK_UP -> setDirection("up");
            case VK_DOWN -> setDirection("down");
        }

    }
    private boolean areCords(int x,int y){
        for(var point : body){
            if(point.x==x && point.y==y){
                return true;
            }
        }
        return false;
    }

}
