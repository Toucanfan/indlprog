public class VectorRally {
	public static void main(String[] args) {
		drawTrack();
	}

	public static void drawTrack() {
		StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.setPenRadius(1/100.);

        StdDraw.line(0, 0,1,1);
	}
}