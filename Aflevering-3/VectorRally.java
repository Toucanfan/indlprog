public class VectorRally {
    public static void main(String[] args) {
        drawTrackBox(20, 20);
    }

    public static void drawTrackBox(int width, int height) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenRadius(1/500.);

        for (int i = 0; i < width; i++) {
            StdDraw.line(0 + i, 0, 0 + i, height);
        }


        for (int i = 0; i < height; i++) {
            StdDraw.line(0, 0 + i, width, 0 + i);
        }
    }
}