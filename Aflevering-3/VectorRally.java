public class VectorRally {
    public static void main(String[] args) {
        drawTrackBox(20, 20, 50, 50);
    }

    public static void drawTrackBox(int x, int y, int width, int height) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenRadius(1/500.);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);

        for (int i = 0; i < width; i++) {
            StdDraw.line(x + i, y, x + i, y + height);
        }


        for (int i = 0; i < height; i++) {
            StdDraw.line(x, y + i, x + width, y + i);
        }
    }
}