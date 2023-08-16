package game.Views;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import game.Models.Apple;

import static game.Params.*;
import static game.Utilities.DrawingHelpers.*;


public class AppleView {
    private AppleView(){}
    public static void draw(Graphics2D g,Apple apple){
        g.setColor(apple.getColor());
        g.fill(new Rectangle2D.Float(horShift+canvasSize*apple.getCol(),verShift+canvasSize*apple.getRow(),canvasSize-GAP_PX,canvasSize-GAP_PX));
    }
}
