public class GameOfLife {
	int[][] world;
	int sizeX;
	int sizeY;

	public GameOfLife(int n) {
		world = new int[n][n];
		sizeX = n-1;
		sizeY = n-1;
	}

	public GameOfLife(int[][] world) {
		boolean err = false;

		int stdLength = world[0].length;
		for (int[] row : world) {
			if(row.length != stdLength) {
				err = true;
			}
		}

		if(!err) {
			this.world = world;
			sizeY = world.length - 1;
			sizeX = stdLength - 1;
		}
	}

	public void cosmicNoise(double p) {
		int[][] newWorld = new int[sizeY+1][sizeX+1];

		for (int i = 0; i < sizeY+1; i++) {
			for (int j = 0; j < sizeX+1; j++) {
				if(Math.random() <= p) {
					newWorld[i][j] = (world[i][j] == 1) ? 0 : 1;
				} else {
					newWorld[i][j] = world[i][j];
				}
			}
		}

		world = newWorld;	
	}

	public void nextState() {
		int[][] newWorld = new int[sizeY+1][sizeX+1];

		for (int i = 0; i < sizeY+1; i++) {
			for (int j = 0; j < sizeX+1; j++) {
				int liveNeighb = liveNeighbours(j, i);
				if(2==liveNeighb) {
					newWorld[i][j] = world[i][j];
				} else if(3==liveNeighb) {
					newWorld[i][j] = 1;
				} else {
					newWorld[i][j] = 0;
				}
			}
		}

		world = newWorld;
	}

	public int liveNeighbours(int x, int y) {
		int[][] coords = {
			{x-1, y-1},
			{x  , y-1},
			{x+1, y-1},
			{x-1, y},

			{x+1, y},
			{x-1, y+1},
			{x  , y+1},
			{x+1, y+1}
		};

		int sum = 0;

		for (int[] coord : coords) {
			int x2 = coord[0];
			int y2 = coord[1];

			if(x2 > sizeX) {
				x2 = 0;
			} else if(x2 < 0) {
				x2 = sizeX;
			} 

			if(y2 > sizeY) {
				y2 = 0;
			} else if(y2 < 0) {
				y2 = sizeY;
			}

			sum += world[y2][x2];
		}

		return sum;
	}

	public String toString() {
		String output = "";

		for (int[] row : world) {
			for (int cell : row) {
				output += "" + cell;
			}
			output += "\n";
		}
		return output;
	}
}