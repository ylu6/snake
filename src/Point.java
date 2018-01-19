
public class Point {
    private int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other){
        if(this==other) return true;
        if (!(other instanceof Point))
            return false;
        Point p = (Point) other;
        return x==p.getX() && y==p.getY();
    }
    // translate a point by dx, dy and return the new point
    Point translate(int dx, int dy) {
        return new Point(x+dx, y+dy);
    }
}
