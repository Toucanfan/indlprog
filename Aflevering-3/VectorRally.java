import java.awt.Point;

public class VectorRally {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);

        drawTrackBox(0, 0, 50, 50);
        drawObstacleBox(12, 12, 25, 25);
        drawGoalLine(25, 37, 25, 50);

        System.out.println(parallel(new Point(1,1), new Point(-1, -1)));
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

    /*public static boolean intersects() {
        if(parallel( ... )) {
            return false
        }

        // Solve linear equations
    }*/

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