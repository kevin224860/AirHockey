import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Puck extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;

    Puck(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();

        int randomXDirection =random.nextInt(2);

        if(randomXDirection == 0){
            randomXDirection--;
        }

        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection =random.nextInt(2);

        if(randomYDirection == 0){
            randomYDirection--;
        }

        setYDirection(randomYDirection*initialSpeed);
    }

    public void setXDirection(int randomX){

        xVelocity = randomX;
    }

    public void setYDirection(int randomY){

        yVelocity = randomY;
    }

    public void move(){
        x = x + xVelocity;
        y = y + yVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x, y,height, width);
    }
}
