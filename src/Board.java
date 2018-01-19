import javafx.scene.paint.Color;

public class Board {
    private Snake snake;
    private Point food, initialHead;
    private RandomizedSet foods; // a random set storing all possible food position
    static final Color COLOR = Color.gray(0.5);
    static final Color FOODCOLOR = Color.GREEN;
    static final Color SNAKECOLOR = Color.BLACK;
    final int SIZE;
    private int height, width, rows=1, cols=1; // number of rows and columns
    private boolean gameOver;

    Board(int width, int height, int size) {
        this.SIZE = size;
        this.height = height;
        this.width = width;
        rows = (int) height/ SIZE;
        cols = (int) width/ SIZE;
        gameOver = false;

        initialHead = new Point(15, 10);
        foods = getFoods();
        food = nextFood();
        snake = new Snake(initialHead);
    }
    // update the status of the game board for a new game cycle
    void update(){
        Point p = snake.getNextHead(); // head translated by (dx,dy)
        foods.remove(getIndex1D(p.getX(), p.getY())); // remove the new head from possible foods set
        if(food.equals(p)) { // there is food on the new head position
            snake.eat(); // eat the food
            food = nextFood(); // get next random food from all possible foods
        } else {
            Point tail = snake.getTail(); // get tail of current snake, before snake is moved
            snake.move();  // just move the snake in current direction
            if(!tail.equals(p)) // the new head and old tail is not same spot
                foods.insert(getIndex1D(tail.getX(), tail.getY())); // the previous tail is now available spot for food
            if (!isSnakeSafe()) // check status of snake, this step must after snake moves
                gameOver = true;
        }
    }
    // return snake object
    Snake getSnake(){
        return snake;
    }
    // return current food point on current board
    Point getFood() {
        return food;
    }
    // retrieve one food from the random food list
    Point nextFood() {
        int coord1D = foods.getRandom();
        foods.remove(coord1D);
        return indexToPoint(coord1D);
    }
    // return the Point represented by a 1D index of this Board
    Point indexToPoint (int idx) {
        int y = idx/cols;
        int x = idx%cols;
        return new Point(x, y);
    }
    // return 1D index of point on this Board, inputs are point's x and y coordinates
    private int getIndex1D(int x, int y){
        return y*cols + x;
    }
    // return height of the board
    int getHeight() {
        return height;
    }
    // return width of the board
    int getWidth() {
        return width;
    }
    // return true if game if over
    boolean isGameOver(){
        return gameOver;
    }
    // return true if snake is safe
    // snake dies if hit board edge or head hits snake body
    private boolean isSnakeSafe() {
        Point head = snake.getHead();
        if(snake.getJoints().contains(head) || head.getX()<0 || head.getX()>=cols || head.getY()<0 || head.getY()>=rows)
            return false;
        return true;
    }
    // initialize foods set, excluding initialHead on the board
    private RandomizedSet getFoods() {
        RandomizedSet set = new RandomizedSet();
        for (int i = 0; i < cols; i++) { // x
            for (int j = 0; j < rows; j++) { // y
                if (i == initialHead.getX() && j == initialHead.getY()) continue;
                set.insert(getIndex1D(i, j));
            }
        }
        return set;
    }
    // unit test for Board
    public static void main(String[] args) {
        Board b = new Board(400, 300, 20);
        System.out.println("rows=" + b.rows + " cols=" + b.cols);
        for(int i = 0; i < 10; i++) {
            Point p = b.nextFood();
            int x = p.getX(), y = p.getY();
            int idx = b.getIndex1D(x,y);
            Point p1 = b.indexToPoint(idx);
            System.out.println(x + ", " + y + " " + idx + " " + p1.getX() + "," + p1.getY());
        }
    }
}
