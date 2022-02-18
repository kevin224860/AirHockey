import javax.swing.*;
import java.awt.*;

public class Game_Frame extends JFrame {
    Music music;
    Game_Panel panel;
    Game_Frame(){

        panel = new Game_Panel();
        this.add(panel);
        this.setTitle("Air Hockey");
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }


}
