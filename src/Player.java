import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {

    int xVelocity;
    int speed = 5;

    Player(int x, int y, int width, int height){
        super(x,  y, width, height);

    }


    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_D){
            setXDirection(speed);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            setXDirection(-speed);
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            setXDirection(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            setXDirection(0);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }

    public void move(){
        x = x +xVelocity;
    }
}
