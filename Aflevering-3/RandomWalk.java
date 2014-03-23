public class RandomWalk {
    public static void main(String[] args) {
    	randomWalk(20);
    }
    
    public static void randomWalk(int gridSize) {
    	int xPos = 0;
    	int yPos = 0;
    	int stepCount = 0;
    	
        StdDraw.setXscale(-gridSize, gridSize);
        StdDraw.setYscale(-gridSize, gridSize);
        StdDraw.setPenRadius(1/(gridSize * 3.));

        while (((xPos < gridSize)&&(xPos > -gridSize))&&
        	   ((yPos < gridSize)&&(yPos > -gridSize))) {
            StdDraw.point(xPos, yPos);
            
            printPosition(xPos, yPos);
            
            double step = Math.random();
            
            // The distribution is even and fair because all comparisons are
            // less-than-or-equal.
            if(step <= 0.25) {
            	xPos++;
            } else if (step <= 0.5) {
            	xPos--;
            } else if (step <= 0.75) {
            	yPos++;
            } else {
            	yPos--;
            }
            
            stepCount++;
        }

        printPosition(xPos, yPos);
        
        System.out.println(stepCount);
	}
    
    public static void printPosition(int x, int y) {
        System.out.print("Position = (");
        System.out.print(x);
        System.out.print(",");
        System.out.print(y);
        System.out.println(")");
	}
}
