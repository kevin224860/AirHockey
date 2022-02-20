import javax.swing.*;
import java.awt.*;

import java.awt.event.*;


public class Game_Panel extends JPanel implements Runnable {
    static final int SCREEN_WIDTH = 480;
    static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * 1.75);
    static final int PLAYER_WIDTH = 100;
    static final int PLAYER_HEIGHT = 25;
    static final int puckDiameter = 20;
    int timer = 0;
    Image image;
    Player player;
    Opponent opponent;
    Puck puck;
    Score score;
    Graphics graphics;
    Thread gameThread;
    

    Game_Panel(){

        newPlayer();
        newOpponent();
        newPuck();
        score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new AL());

        gameThread = new Thread(this);
        gameThread.start();
        new Music("Music/background.wav").playBackground();
    }

    public void newPlayer(){

        player = new Player((SCREEN_WIDTH/2) - (PLAYER_WIDTH/2), SCREEN_HEIGHT - 50, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void newOpponent(){

        opponent = new Opponent( (SCREEN_WIDTH/2) - (PLAYER_WIDTH/2), 25, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void newPuck(){
        puck = new Puck((SCREEN_WIDTH/2) - (puckDiameter/2), (SCREEN_HEIGHT/2) - (puckDiameter/2), puckDiameter, puckDiameter);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g){
        player.draw(g);
        opponent.draw(g);
        puck.draw(g);
        score.draw(g);


    }


    public void move(){
        player.move();
        puck.move();
        opponent.move();

        timer();
        if(timer== 5){
            if(puck.y < 228){
                checkPuckX();
            }else{
                opponent.setXDirection(0);
            }
            timer = 0;
        }


    }

    public void timer(){
        timer = timer +1;
    }

    public void checkPuckX(){
        if(puck.x - 50 < opponent.x){
            opponent.setXDirection(-opponent.speed);

        }
        if(puck.x - 50 > opponent.x){
            opponent.setXDirection(opponent.speed);
        }

        if(puck.x + 10 == opponent.x){
            opponent.setXDirection(0);

        }

    }

    public void checkCollision() throws InterruptedException {
        // prevents the player from going off the screen
        if(player.x <=0){
            player.x =0;
        }

        if(player.x >= SCREEN_WIDTH - PLAYER_WIDTH){

            player.x =SCREEN_WIDTH - PLAYER_WIDTH;
        }

        // prevents the opponent from going off the screen
        if(opponent.x <= 0){
            opponent.x = 0;
        }

        if(opponent.x >= SCREEN_WIDTH - PLAYER_WIDTH){
            opponent.x = SCREEN_WIDTH - PLAYER_WIDTH;

        }

        // prevents puck from going out of bounds
        if(puck.x <=0 || puck.x >= SCREEN_WIDTH - puckDiameter){
            new Music("Music/collide.wav").playBallCollision();
            puck.setXDirection(-puck.xVelocity);
        }


        // bounce puck off player
        if(puck.intersects(player)){
            new Music("Music/collide.wav").playBallCollision();
            puck.yVelocity = -puck.yVelocity;
            if(puck.xVelocity>0){
                puck.xVelocity++;
            }else{
                puck.xVelocity--;
            }
            puck.setXDirection(puck.xVelocity);
            puck.setYDirection(puck.yVelocity);
        }

        // bounce puck off opponent
        if(puck.intersects(opponent)){
            new Music("Music/collide.wav").playBallCollision();
            puck.yVelocity = -puck.yVelocity;
            if(puck.xVelocity>0){
                puck.xVelocity++;
            }else{
                puck.xVelocity--;
            }
            puck.setXDirection(puck.xVelocity);
            puck.setYDirection(puck.yVelocity);
        }

        // scores
        if(puck.y >= SCREEN_HEIGHT - puckDiameter){
            new Music("Music/score.wav").playBallCollision();
            score.opponentScore++;
            newPuck();
        }
        if(puck.y <= 0){
            new Music("Music/score.wav").playBallCollision();
            score.playerScore++;
            newPuck();
        }
    }


    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta = delta + (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                try {
                    checkCollision();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
                delta--;

            }

        }

    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
    }
}
