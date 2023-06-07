package cv.portfolio;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;
    public static final int SCALE = 32;

    public GameFrame() {
        this.add(new GamePanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.setSize(WIDTH * SCALE + 7, HEIGHT * SCALE + 20);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
