import java.util.LinkedList;
import java.util.List;

class Snake {
    private int xDir=1, yDir=0;
    private List<Point> joints; // the body points of the snake, head not included, first element is tail of the snake
    private Point head, nextHead;
    private boolean isAlive;

    Snake(Point initialHead) {
        joints = new LinkedList<>();
        head = initialHead;
        nextHead = head.translate(xDir, yDir);
        isAlive = true;
    }
    // set snake's moving direction
    void setDir(int xDir, int yDir) {
        if(this.xDir==-xDir || this.yDir==-yDir)
            return; // new direction is the reverse of previous direction
        this.xDir = xDir;
        this.yDir = yDir;
        nextHead = head.translate(xDir, yDir);
    }
    // move head position, remove tail
    void move() {
        joints.add(head);
        head = nextHead;
        nextHead = head.translate(xDir, yDir);
        joints.remove(0);
    }
    // there is food on next head's spot, extend head, but don't remove tail
    void eat() {
        joints.add(head);
        head = nextHead;
        nextHead = head.translate(xDir, yDir);
    }
    // return a list of points on the snake body excluding head
    List<Point> getJoints() {
        return joints;
    }
    // return head of the current snake
    Point getHead() {
        return head;
    }
    // return tail of the snake, return head if snake contains only one point
    Point getTail() {
        return joints.isEmpty() ? head : joints.get(0);
    }
    // return next head point if snake moving in current direction (xDir, yDir)
    Point getNextHead(){
        return nextHead;
    }
}
