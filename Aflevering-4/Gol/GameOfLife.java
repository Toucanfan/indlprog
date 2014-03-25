public class GameOfLife {
	int[][] world;
	int size;

	public GameOfLife(int n) {
		world = new int[n][n];
		size = n-1;
	}

	public GameOfLife(int[][] world) {
		boolean err = false;

		for (int[] row : world) {
			if(row.length != world.length) {
				err = true;
			}
		}
		if(!err) {
			this.world = world;
			size = world.length - 1;
		}
	}

	public void nextState() {
		int[][] newWorld = new int[size+1][size+1];

		for (int i = 0; i < size+1; i++) {
			for (int j = 0; j < size+1; j++) {
				int liveNeighb = liveNeighbours(i, j);
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

			if(x2 > size) {
				x2 = 0;
			} else if(x2 < 0) {
				x2 = size;
			} 

			if(y2 > size) {
				y2 = 0;
			} else if(y2 < 0) {
				y2 = size;
			} 

			sum += world[x2][y2];
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