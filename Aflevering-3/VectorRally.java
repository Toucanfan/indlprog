public class VectorRally {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        
        drawTrackBox(0, 0, 50, 50);
        drawObstacleBox(12, 12, 25, 25);
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
}