package game.Views;

import game.Models.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static game.Params.GAP_PX;
import static game.Utilities.DrawingHelpers.*;
import static game.Utilities.DrawingHelpers.canvasSize;

public class FieldView {
    public static void draw(Graphics2D g, Field field, Component panel){
        float canvasWidth = panel.getWidth()/(float)field.getCol();
        float canvasHeight = panel.getHeight()/(float)field.getRow();
        canvasSize = Math.min(canvasHeight,canvasWidth)*2/3;
        float fieldWidth = canvasSize * field.getCol();
        float fieldHeight = canvasSize * field.getRow();
        horShift = (panel.getWidth() - fieldWidth)*0.5f;
        verShift = (panel.getHeight() - fieldHeight)*0.5f;
        for(int i = 0;i<field.getRow();i++){
            for(int j = 0;j<field.getCol();j++){
                float posX = horShift + i*canvasSize;
                float posY =verShift + j*canvasSize;
                g.setColor(field.getColor());
                g.fill(new Rectangle2D.Float(posX,posY,canvasSize-GAP_PX,canvasSize-GAP_PX) );
            }
        }
    }
}
