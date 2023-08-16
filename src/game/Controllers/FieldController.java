package game.Controllers;

import game.Models.Apple;
import game.Models.Field;
import game.Views.AppleView;
import game.Views.FieldView;

import java.awt.*;

import static game.Params.*;

public class FieldController {
    private Field field;
    public void setFieldColor(Color color){
        field.setColor(color);
    }
    public FieldController(Field field){
        this.field = field;
    }
    public void setFieldApple(Apple apple){
        field.setApple(apple);
    }
    public void drawField(Graphics2D g, Component component){
        FieldView.draw(g,field,component);
    }
    public void drawApple(Graphics2D g){
        AppleView.draw(g,field.getApple());
    }
}
