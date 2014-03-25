public class GameOfLifeMain {
	public static GameOfLife gol;

	public static void main(String[] args) {
		int size = 100;

		gol = new GameOfLife(size);

		gol.world[5][5] = 1;
		gol.world[5][4] = 1;
		gol.world[5][3] = 1;
		gol.world[4][3] = 1;
		gol.world[3][4] = 1;

		gol.cosmicNoise(0.7);

		StdDraw.setXscale(0,size);
		StdDraw.setYscale(0,size);
        StdDraw.setPenRadius(1/(size * 1.));

        while(true) {
        	StdDraw.show(100);
        	render();
        	gol.nextState();
        }
	}

	public static void render(){
        StdDraw.clear();
		for (int i = 0; i < gol.world.length; i++) {
        	int[] row = gol.world[i];
        	for (int j = 0; j < row.length; j++) {
        		if(1==gol.world[i][j]) {
        			StdDraw.point(i, j);
        		}
        	}
        }
	}
}