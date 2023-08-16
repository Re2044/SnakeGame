package game.Models;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import static game.Utilities.DrawingHelpers.*;
import static game.Params.*;
public class Apple {
    private int row;
    private int col;
    private Random generator;
    private final Color color;
    private final Field field;

    public Apple(int seed, Color color, Field field){

        generator = new Random(seed);

        this.color = color;
        this.field = field;
        changeLocation();
    }
    public void changeLocation(){
        this.row = generator.nextInt(0,field.getCol());
        this.col = generator.nextInt(0,field.getRow());
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    public Color getColor(){
        return this.color;
    }

}
