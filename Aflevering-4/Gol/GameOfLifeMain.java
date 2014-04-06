import java.util.Scanner;
import java.io.*;
import java.text.ParseException;

public class GameOfLifeMain {
	public static GameOfLife gol;

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		int[][] world = loadWorld("testworld");

		gol = new GameOfLife(world);

		StdDraw.setXscale(0,gol.size);
		StdDraw.setYscale(0,gol.size);
        StdDraw.setPenRadius(1/(gol.size * 1.));

        while(true) {
        	StdDraw.show(100);
        	render();
        	gol.nextState();
        }
	}

	public static int[][] loadWorld(String path)
	throws FileNotFoundException, ParseException {
		int lineCount = lineCounter(path);
		int[][] world = new int[lineCount][];

		Scanner worldData = new Scanner(new File(path));
		int j = 0;
		while(worldData.hasNextLine()) {
			String line = worldData.nextLine();

			int[] row = new int[line.length()];

			for (int i = 0; i < line.length(); i++) {
				row[i] = Integer.parseInt("" + line.charAt(i));
			}

			world[j] = row; j++;
		}

		return world;
	}

	public static int lineCounter(String path)
	throws FileNotFoundException, ParseException {
		int lineCount = 0;

		Scanner worldData = new Scanner(new File(path));
		while(worldData.hasNextLine()) {
			worldData.nextLine();
			lineCount++;
		}

		return lineCount;
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