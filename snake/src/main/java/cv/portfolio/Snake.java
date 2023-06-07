package cv.portfolio;

/**
 * Public class Snake represents snake object to be directed by the user during application.
 */
public class Snake {

    public GamePanel p;
    // Starting size and direction of snake at app start
    public int length = 2;
    public int direction = 0;
    // snake is represented by an arrays that indicate x and y positions on game board
    public int snakeX [] = new int[p.WIDTH * p.HEIGHT];
    public int snakeY [] = new int[p.WIDTH * p.HEIGHT];

    public Snake(int x0, int y0, int x1, int y1) {
        snakeX[0] = x0;
        snakeY[0] = y0;
        snakeX[1] = x1;
        snakeY[1] = y1;
    }


    public void move() {
        for (int i = length; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        if (direction == 0) {
            snakeX[0]++;
        }
        if (direction == 1) {
            snakeY[0]++;
        }
        if (direction == 2) {
            snakeX[0]--;
        }
        if (direction == 3) {
            snakeY[0]--;
        }

        if (snakeX[0] > p.WIDTH) {
            snakeX[0] = 0;
        }
        if (snakeX[0] < 0) {
            snakeX[0] = p.WIDTH - 1;
        }
        if (snakeY[0] > p.HEIGHT) {
            snakeY[0] = 0;
        }
        if (snakeY[0] < 0) {
            snakeY[0] = p.HEIGHT -1;
        }
        if (length < 2) {
            length = 2;
        }
    }
}
