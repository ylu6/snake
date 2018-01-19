import javafx.scene.canvas.GraphicsContext;

public class GameLoop implements Runnable {
    private Board board;
    private GraphicsContext context;
    private boolean keyPressed;

    GameLoop(Board board, GraphicsContext context) {
        this.board = board;
        this.context = context;
        keyPressed = false;
    }
    @Override
    public void run() {
        while(true) {
            board.update();
            Painter.paint(context, board);
            if (board.isGameOver()) {
                Painter.restartMessage(context, board);
                break;
            }
            keyPressed = false;
            try{
                Thread.sleep(200);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    boolean isKeyPressed() {
        return keyPressed;
    }
    void setKeyPressed() {
        keyPressed = true;
    }
}
