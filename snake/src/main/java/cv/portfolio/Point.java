package cv.portfolio;


/**
 * Public class Point represents points earned during gameplay and randomly positions points along the board.
 */

public class Point {

    GamePanel p;
    public int xPosition;
    public int yPosition;
    public int count;

    public Point(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // Point is randomly placed along board
    public void setNewPointPosition() {
        xPosition = (int) (Math.random() * p.WIDTH);
        yPosition = (int) (Math.random() * p.HEIGHT);
    }
}
