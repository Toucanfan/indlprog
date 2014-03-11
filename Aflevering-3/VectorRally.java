import java.awt.Point;

public class VectorRally {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);

        drawTrackBox(0, 0, 50, 50);
        drawObstacleBox(12, 12, 25, 25);
        drawGoalLine(25, 37, 25, 50);

        Point p1 = new Point(0,0);
        Point p2 = new Point(1,1);
        Point q1 = new Point(0,1);
        Point q2 = new Point(1,0);

        Point p = new Point(25,45);
        Point prev = new Point(0,0);

        moveCar(p, prev, new Point(1,0));
        moveCar(p, prev, new Point(1,-1));
        moveCar(p, prev, new Point(1,0));
        moveCar(p, prev, new Point(-1,-1));
        moveCar(p, prev, new Point(-1,-1));
        moveCar(p, prev, new Point(-1,-1));
        moveCar(p, prev, new Point(0,-1));
        moveCar(p, prev, new Point(0,-1));
        moveCar(p, prev, new Point(0,-1));
        moveCar(p, prev, new Point(0,-1));
        moveCar(p, prev, new Point(0,-1));
        moveCar(p, prev, new Point(0,-1));
    }

    public static void moveCar(Point cur, Point prevVec, Point nextVec) {
        /*
         * Draws and moves car
         */

        boolean inter = intersects(cur, new Point(cur.x + prevVec.x + nextVec.x, cur.y + prevVec.y + nextVec.y),
            new Point(12,12),
            new Point(37,12));

        if(inter) {
            StdDraw.setPenColor(StdDraw.RED);
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }

        StdDraw.setPenRadius(1/100.);
        StdDraw.line(cur.x, cur.y, cur.x + prevVec.x + nextVec.x, cur.y + prevVec.y + nextVec.y);

        cur.translate(prevVec.x + nextVec.x, prevVec.y + nextVec.y);
        prevVec.translate(nextVec.x, nextVec.y);
    }

    public static void drawTrackBox(int x, int y, int width, int height) {
        /*
         * Draws a track (drivable) background.
         */

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(x + (width / 2.), y + (height / 2.), width / 2., height / 2.);

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.setPenRadius(1/250.);
        StdDraw.rectangle(x + (width / 2.), y + (height / 2.), width / 2., height / 2.);

        StdDraw.setPenRadius(1/500.);
        StdDraw.setPenColor(StdDraw.GRAY);

        for (int i = 0; i < width; i++) {
            StdDraw.line(x + i, y, x + i, y + height);
        }


        for (int i = 0; i < height; i++) {
            StdDraw.line(x, y + i, x + width, y + i);
        }
    }

    public static void drawObstacleBox(int x, int y, int width, int height) {
        /*
         * Draws an obstacle (not drivable) background.
         */
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(x + (width / 2.), y + (height / 2.), width / 2., height / 2.);

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.setPenRadius(1/250.);
        StdDraw.rectangle(x + (width / 2.), y + (height / 2.), width / 2., height / 2.);
    }

    public static void drawGoalLine(int x, int y, int x2, int y2) {
        /*
         * Draws the goal line.
         */
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(1/100.);
        StdDraw.line(x, y, x2, y2);
    }

    /*
     * Linear Algebra methods
     */

    public static boolean intersects(Point p1, Point p2, Point q1, Point q2) {
        /*
         * Determines if the line segment defined by the points p1-p2
         * intersects with the line segment defined by the points q1-q2
         */

        Point v1 = new Point(p2.x - p1.x, p2.y - p1.y);
        Point v2 = new Point(q2.x - q1.x, q2.y - q1.y);

        if(parallel(v1, v2)) {
            return false;
        }

        // Solve linear equations

        int x = p1.x;
        int y = p1.y;
        int a = q1.x;
        int b = q1.y;
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        int da = q2.x - q1.x;
        int db = q2.y - q1.y;

        double t1 = ((- b * da) + y * da + a * db - x * db)/(1.0 * db * dx - da * dy);
        double t2 = ((- b * dx) + y * dx + a * dy - x * dy)/(1.0 * db * dx - da * dy);

        if((t1<=1)&&(t1<=1)&&(t1>0)&&(t2>0)){
            return true;
        }

        return false;
    }

    public static boolean parallel(Point p1, Point p2) {
        return dotProduct(p1, orthorgonal(p2)) == 0.0;
    }

    public static Point orthorgonal(Point p_in) {
        Point p = new Point(-p_in.y, p_in.x);
        return p;
    } 

    public static double dotProduct(Point p1, Point p2) {
        return p1.x * p2.x + p1.y * p2.y;
    }
}