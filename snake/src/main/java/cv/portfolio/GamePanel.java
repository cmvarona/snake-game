package cv.portfolio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Public class GamePanel contains design and layout for game board and methods for game play
 */
public class GamePanel extends JPanel implements ActionListener {

    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;
    public static final int SPEED = 10;
    public static final int SCALE = 32;

    private Timer timer = new Timer(1000/SPEED, this);
    private Snake snake = new Snake(10, 10, 9, 10);
    private Point point = new Point((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
    private Obstacle obstacle = new Obstacle((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));

    private boolean running = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE + 7, HEIGHT * SCALE + 20));
        this.addKeyListener(new Keyboard());
        this.setFocusable(true);
        startGame();
    }

    public void startGame() {
        running = true;
        point.setNewPointPosition();
        obstacle.setNewPosition();
        timer.start();
    }

    // Color game board
    public void paint(Graphics graphics) {
        if (running) {
            graphics.setColor(color(0, 0, 155));
            graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
            // obstacles are black squares
            graphics.setColor(color(0, 0, 0));
            graphics.fillRect(obstacle.xPosition * SCALE + 1, obstacle.yPosition * SCALE + 1,
                    SCALE - 1, SCALE - 1);
            // Points are gold squares
            graphics.setColor(color(255, 204, 51));
            graphics.fillOval(point.xPosition * SCALE + 1, point.yPosition * SCALE + 1, SCALE - 1,
                    SCALE - 1);
            // Snake is green
            for (int i = 0; i < snake.length; i++) {
                graphics.setColor(color(0, 202, 0));
                graphics.fillOval(snake.snakeX[i] * SCALE + 1, snake.snakeY[i] * SCALE + 1, SCALE - 1,
                        SCALE - 1);
            }
        }
        else {
            gameOver(graphics);
        }
    }
    // helper method to set colors
    public Color color(int r, int g, int b) {
        return new Color (r,g,b);
    }

    // Check if player has run into point, if so, generate a new point that is not in the space already
    // occupied by snake, then add onto snake length
    public void checkForPoint() {
        if (snake.snakeX[0] == point.xPosition && snake.snakeY[0] == point.yPosition) {
            point.setNewPointPosition();
            snake.length ++;
            obstacle.setNewPosition();
            point.count++;

        }
        for (int i = 0; i < snake.length; i++) {
            if (snake.snakeX[i] == point.xPosition && snake.snakeY[i] == point.yPosition) {
                point.setNewPointPosition();
            }
        }
        // Make sure obstacle does not appear in same position as point
        if (point.yPosition == obstacle.yPosition && point.xPosition == obstacle.xPosition) {
            obstacle.setNewPosition();
        }
    }

    // If snake runs into obstacle, game ends
    public void checkForObstacle() {
        if (snake.snakeX[0] == obstacle.xPosition && snake.snakeY[0] == obstacle.yPosition) {
            for (int i = 0; i < snake.length; i++) {
                // obstacle should not appear somewhere the snake already is
                if (snake.snakeX[i] == obstacle.xPosition && snake.snakeY[i] == obstacle.yPosition) {
                    obstacle.setNewPosition();
                }
                else {
                    running = false;
                }
            }
        }
    }
    // If snake runs into itself, game ends
    public void checkForCollision() {
        for (int i = 0; i < snake.length; i++) {
            if (i > 4) {
                if (snake.snakeX[0] == snake.snakeX[i] && snake.snakeY[0] == snake.snakeY[i]) {
                    running = false;
                }
            }
        }
    }

    // If the player loses, score is displayed and player is given option to play again
    public void gameOver(Graphics graphics) {
        // display game over text
        graphics.setColor(color(255,0,0));
        graphics.setFont(new Font("Monospaced", Font.BOLD, 40));
        graphics.drawString("GAME OVER", 200, 300);
        // display score
        graphics.setColor(color(255,0,0));
        graphics.setFont(new Font("Monospaced", Font.BOLD, 30));
        graphics.drawString("SCORE: " + point.count, 200,
                200);
        // display Play Again text
        graphics.setColor(color(255, 255, 255));
        graphics.setFont(new Font("Monospaced", Font.BOLD, 20));
        graphics.drawString("Press Enter To Play Again", 150, 400);
        // display game rules
        graphics.setColor(color(255,0,0));
        graphics.setFont(new Font("Monospaced", Font.BOLD, 20));
        graphics.drawString("collect gold coins and avoid black squares", 90, 500);
    }

    public void actionPerformed(ActionEvent event) {
        if (running) {
            snake.move();
            checkForPoint();
            checkForObstacle();
            checkForCollision();
        }
        repaint();
    }

    // Keyboard class to manage snake direction and play again
    private class Keyboard extends KeyAdapter {
        public void keyPressed(KeyEvent keyEvent) {
            int key = keyEvent.getKeyCode();
            if (key == KeyEvent.VK_RIGHT && snake.direction != 2 ||
                    key == KeyEvent.VK_D && snake.direction != 2) {
                snake.direction = 0;
            }
            if (key == KeyEvent.VK_DOWN && snake.direction != 3 ||
                    key == KeyEvent.VK_S && snake.direction != 3) {
                snake.direction = 1;
            }
            if (key == KeyEvent.VK_LEFT && snake.direction != 0 ||
                    key == KeyEvent.VK_A && snake.direction != 0) {
                snake.direction = 2;
            }
            if (key == KeyEvent.VK_UP && snake.direction != 1 ||
                    key == KeyEvent.VK_W && snake.direction != 1) {
                snake.direction = 3;
            }
            if (key == KeyEvent.VK_ENTER && !running) {
                startGame();
            }
        }
    }
}

