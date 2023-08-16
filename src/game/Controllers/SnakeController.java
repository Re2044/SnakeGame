package game.Controllers;

import game.Models.Field;
import game.Models.Snake;
import game.Views.SnakeView;

import java.awt.*;

import static game.Params.*;

public class SnakeController {
    Snake snake;
    public SnakeController(Snake snake){
        this.snake = snake;
    }
    public  void setSnakeColorAlive(Color color){
        snake.setColorAlive(color);
    }
    public  void setSnakeColorDead(Color color){
        snake.setColorDead(color);
    }
    public  void setSnakeDirection(String direction){
        snake.setDirection(direction);
    }
    public  void setSnakeDirection(int keyCode){
        snake.setDirection(keyCode);
    }
    public  boolean checkSnakeState(){return snake.isDead();}
    public int getSnakeScore(){return snake.getScore();}
    public void moveSnake(){snake.move();}
    public void drawSnake(Graphics2D g){
        SnakeView.draw(g,snake);
    }

}
