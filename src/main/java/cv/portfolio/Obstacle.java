package cv.portfolio;

/**
 *  Public class Obstacle represents an obstacle on the game board, if the player hits the obstacle, the game ends.
 */
public class Obstacle {

    GamePanel p;
    public int xPosition;
    public int yPosition;

    public Obstacle(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // Method to randomly generate obstacle on the board
    public void setNewPosition() {
        xPosition = (int) (Math.random() * p.WIDTH);
        yPosition = (int) (Math.random() * p.HEIGHT);
    }

}
