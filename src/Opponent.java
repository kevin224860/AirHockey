import java.awt.*;

public class Opponent extends Rectangle {
    int xVelocity;
    int speed = 5;

    Opponent(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }

    public void move(){
        x = x + xVelocity;
    }
}
