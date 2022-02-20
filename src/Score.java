import java.awt.*;

public class Score {

    static int SCREEN_WIDTH = 480;
    static int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * 1.75);
    int playerScore = 0;
    int opponentScore = 0;

    Score(int GAME_WIDTH,int GAME_HEIGHT){
        Score.SCREEN_WIDTH = SCREEN_WIDTH;
        Score.SCREEN_HEIGHT = SCREEN_HEIGHT;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("Aria", Font.BOLD, 60));

        g.drawString(String.valueOf(playerScore), SCREEN_WIDTH - 50, SCREEN_HEIGHT -5);
        g.drawString(String.valueOf(opponentScore), SCREEN_WIDTH - 50, 50);

    }
}
