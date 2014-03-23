import java.awt.Point;
import java.util.Scanner;
import java.io.*;
import java.text.ParseException;

public class VectorRally {
    public static Point[][] boundries = new Point[1024][2];
    public static int nextIndex = 0;
    public static Point[] goalLine = new Point[2];
	public static boolean wrong_way = false;

    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);

        Point cur = new Point(0,0);
        Point prev = new Point(0,0);

        drawTrackBox(0, 0, 50, 50);
		/* Load obstacles and starting point from .map file */
		try {
			loadMap(args[0], cur);
		} catch (FileNotFoundException e) {
			System.out.printf("Error opening %s: File doesn't exist", args[0]);
			return;
		} catch (ParseException e) {
			System.out.printf("Error parsing %s: %s\n", args[0], e.getMessage());
		}
        addBoundaryBox(0, 0, 50, 50);


		/* Process keyboard events */
        while (true) {
			/* We are busy looping since it is simple,
			   and because we can use a large sleep interval
			   due to the slow reaction time of user */
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
			if (!StdDraw.hasNextKeyTyped()) {
				continue;
			}
            int input = StdDraw.nextKeyTyped(); //Returns ASCII char
            Point nextVec = handleInput(input);
            moveCar(cur, prev, nextVec);
			if (wrong_way)
				System.out.println("Wrong way");
        }
    }

    public static Point handleInput(int input) {
        int x, y;

        switch (input) {
            case '1':
                x = -1; y =  -1; break;
            case '2':
                x =  0; y =  -1; break;
            case '3':
                x =  1; y =  -1; break;
            case '4':
                x = -1; y =  0; break;
            case '5':
                x =  0; y =  0; break;
            case '6':
                x =  1; y =  0; break;
            case '7':
                x = -1; y = 1; break;
            case '8':
                x =  0; y = 1; break;
            case '9':
                x =  1; y = 1; break;
            default:
                x =  0; y =  0; break;
        }

        return new Point(x, y);
    }

	public static void loadMap(String path, Point startingPoint)
	throws FileNotFoundException, ParseException {
		/*
		 * Reads file given by path, and constructs map therefrom.
		 */
		Scanner mapdata = new Scanner(new File(path));
		String action = "drawObstacleBox";
		while (mapdata.hasNextLine()) {
			String line = mapdata.nextLine();
			/* Empty line in .map file == we should intrepret next line differently */
			if (line.equals("")) {
				switch(action) {
					case "drawObstacleBox":
						action = "drawGoalLine";
						break;
					case "drawGoalLine":
						action = "setStartingPoint";
						break;
					case "setStartingPoint":
						/* Max 2 empty lines in each .map file */
						throw new ParseException("Encountered more than 2 blank lines", 0);
					default:
						throw new RuntimeException();
				}
				continue;
			}
			int[] geomData = new int[4];
			for (int i = 0; i < line.split(" ").length; i++) {
				geomData[i] = Integer.parseInt(line.split(" ")[i]);
			}
			
			switch(action) {
				case "drawObstacleBox":
					drawObstacleBox(geomData[0], geomData[1], geomData[2], geomData[3]);
					break;
				case "drawGoalLine":
					drawGoalLine(geomData[0], geomData[1], geomData[2], geomData[3]);
					break;
				case "setStartingPoint":
					startingPoint.setLocation(geomData[0], geomData[1]);
					break;
				default:
					throw new RuntimeException();
			}
		}
	}

    public static void moveCar(Point cur, Point prevVec, Point nextVec) {
        /*
         * Draws and moves car
         */

        boolean inter = testIntersections(cur, new Point(prevVec.x + nextVec.x, prevVec.y + nextVec.y));

        if(inter) {
            StdDraw.setPenColor(StdDraw.RED);
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }

        boolean interGoal = intersects(cur, new Point(cur.x + prevVec.x + nextVec.x, cur.y + prevVec.y + nextVec.y), goalLine[0], goalLine[1]);

        if (interGoal) {
			/* The below cond. is true if we cross the goal line from the right direction */
			if (dotProduct(addVector(prevVec, nextVec), orthorgonal(subVector(goalLine[0], goalLine[1])))>0) {
				if (wrong_way)
					wrong_way = false;
				else
					System.out.println("You're winner!");
			} else {
				wrong_way = true;
			}
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

        addBoundaryBox(x, y, width, height);
    }

        public static void addBoundaryBox(int x, int y, int width, int height) {
        /*
         * Adds an (invisible) boundary box.
         */

        int[][] comb = {
            {x, y, x + width, y},
            {x, y + height, x + width, y + height},
            {x, y, x, y + height},
            {x + width, y, x + width, y + height},
        };

        for (int i = 0; i < 4; i++) {
            Point p1 = new Point(comb[i][0], comb[i][1]);
            Point p2 = new Point(comb[i][2], comb[i][3]);

            boundries[nextIndex][0] = p1;
            boundries[nextIndex++][1] = p2;
        }

    }

    public static void drawGoalLine(int x, int y, int x2, int y2) {
        /*
         * Draws the goal line.
         */
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(1/100.);
        StdDraw.line(x, y, x2, y2);

        goalLine[0] = new Point(x, y);
        goalLine[1] = new Point(x2, y2);
    }

    public static boolean testIntersections(Point cur, Point nextVec) {
        for (int i = 0; i < boundries.length; i++) {
            if(boundries[i][0] == null) {
                return false;
            }
            boolean inter = intersects(cur, new Point(cur.x + nextVec.x, cur.y + nextVec.y),
                boundries[i][0],
                boundries[i][1]);

            if (inter) {
                return true;
            }

            /*
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(1/250.);
            StdDraw.line(boundries[i][0].x, boundries[i][0].y, boundries[i][1].x, boundries[i][1].y);
            //*/
        }

        return false;
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

        if((t1<=1)&&(t2<=1)&&(t1>0)&&(t2>0)){
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

	public static Point addVector(Point p1, Point p2) {
		return new Point(p1.x + p2.x, p1.y + p2.y);
	}

	public static Point subVector(Point p1, Point p2) {
		return new Point(p1.x - p2.x, p1.y - p2.y);
	}
}
