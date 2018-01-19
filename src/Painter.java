import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class Painter {
    // paint the whole board on graphic context, including food, snake
    static void paint(GraphicsContext gc, Board board) {
        // fill the whole board, board from previous game cycle will be covered
        gc.setFill(Board.COLOR);
        gc.fillRect(0,0,board.getWidth(),board.getHeight());
        // paint food
        gc.setFill(Board.FOODCOLOR);
        paint(gc, board.getFood(), board.SIZE);
        // paint snake
        gc.setFill(Board.SNAKECOLOR);
        paint(gc, board.getSnake().getHead(), board.SIZE);
        for (Point p : board.getSnake().getJoints()) {
            paint(gc, p, board.SIZE);
        }
        if(board.isGameOver()) {
            gc.setFill(Color.RED); // paint head to red if game is over
            paint(gc,board.getSnake().getHead(), board.SIZE);
        }
        displayScore(gc,board); // display score information
    }
    // paint a point, the size of the point is specified as input
    static void paint(GraphicsContext gc, Point point, int size) {
        gc.fillRect(point.getX()*size, point.getY()*size, size, size);
    }
    static void displayScore(GraphicsContext gc, Board board) {
        gc.setFont(new Font(30));
        gc.setFill(Color.BLUE);
        gc.fillText("Score: " + 10*board.getSnake().getJoints().size(), 10, board.getHeight()-10);
    }
    static void restartMessage(GraphicsContext gc, Board board){
        gc.setFont(new Font(30));
        gc.setFill(Color.RED);
        gc.fillText("Hit Enter To Restart", 10, board.getHeight()/2);
    }
}
