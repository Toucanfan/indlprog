import java.util.*;
import java.awt.Color;
import java.io.*;

public class Mandelbrot {
	
	static final int MAX = 255;
	static final double ZOOMFACTOR = 1.1;
	static final double MOVEFACTOR = 0.1;
	static final double PENSIZEFACTOR = 0.8;
  	static Color[] palette = new Color[MAX+1];
	static double gridSize;
	static double width;
	static Complex centerDot = new Complex();
	static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		initialization();
		drawFractal();
		navigation();
	}
	
	/**
	 * Sets all initial values
	 * @throws FileNotFoundException
	 */
	static void initialization() throws FileNotFoundException {
		boolean multiColor = false;
		boolean colorFromFile = false;
		
		System.out.println("Welcome to Mandelbrot navigator, use WASD for navigation and Q/E for zoom.");
		
		multiColor = askUserBoolean ("Multicolor? (y/n): ");
	
		if (multiColor)
			colorFromFile = askUserBoolean("Color from file? (y/n): ");
		
		if (colorFromFile) {
			System.out.print("Please enter file path of color file: ");
			File colorFile = new File(console.nextLine());
			while (!colorFile.canRead()) {
				System.out.print("Can't find file, please try again: ");
				colorFile = new File(console.nextLine());
			}
			Scanner colorFileScanner = new Scanner(colorFile);
			for (int i=0; i<palette.length; i++)
				palette[i] = new Color(colorFileScanner.nextInt(),colorFileScanner.nextInt(),colorFileScanner.nextInt());
		
		} else if (multiColor) { 
			for (int i=0, red, blue, green; i<palette.length; i++) {
				red   = (int)(Math.random()*palette.length);
				green = (int)(Math.random()*palette.length);    // fill up with random colors
				blue  = (int)(Math.random()*palette.length);
				palette[i] = new Color(red,green,blue);
			}
	
		} else {
			Arrays.fill(palette,new Color(255,255,255)); // fill with white
			palette[MAX] = new Color (255,0,0);          // set color of MAX to red
		}
		
		centerDot = centerDot.setRe(askUserDouble("Input the real part of the center coordinate (if unsure, use 0): "));
		centerDot = centerDot.setIm(askUserDouble("Input the imaginary part of the center coordinate (if unsure, use 0): "));
		width = Math.abs(askUserDouble("Input the width of the coordinate system (if unsure, use 2): "));
		gridSize = Math.abs(askUserDouble("Input desired grid size (use low grid size for smooth navigation): "));
	}

	/**
	 * Prompts the user for a double and returns it
	 * @param message - the message shown before prompt
	 * @return double input by user
	 */ 
	static double askUserDouble(String message) {
		System.out.print(message);
		while (!console.hasNextDouble()) {
			System.out.print("Invalid input, try again: ");
			console.nextLine();
		}
		return console.nextDouble();
	}
	
	/**
	 * Prompts the user for a boolean and returns it
	 * @param message - the message shown before prompt
	 * @return boolean input by user
	 */ 
	static boolean askUserBoolean(String message) {
		System.out.print(message);
		while(true) {
			String inputString = console.nextLine();
			switch(inputString) {
				 case "yes": return true;
				 case "y"  : return true;
				 case "no" : return false;
				 case "n"  : return false;
				 default   : System.out.print("Invalid input, try again: ");			    		 
			}
		}
	}
	
	/**
	 * Redraws the screen. Uses the global variables "CenterDot" and "width"
	 */
	static void drawFractal() {
		double topX = centerDot.getRe() + width/2;
		double topY = centerDot.getIm() + width/2;
		double bottomX = centerDot.getRe() - width/2;
		double bottomY = centerDot.getIm() - width/2;
		
		StdDraw.show(0); // hide all changes
		StdDraw.clear();
		StdDraw.setXscale(bottomX, topX);
        StdDraw.setYscale(bottomY,topY);
        StdDraw.setPenRadius(PENSIZEFACTOR/(gridSize-1));

        Complex tempZ;
        for(int i=0; i<gridSize; i++)
			for(int j=0; j<gridSize; j++) {
				tempZ = new Complex(bottomX+i*width/(gridSize-1),bottomY+j*width/(gridSize-1)); // sets the current point to draw
				StdDraw.setPenColor(palette[iterate(tempZ)]); // sets the color of the current point using the color palette
				StdDraw.point(tempZ.getRe(),tempZ.getIm()); // draws the point
			}
        
        StdDraw.show(0); // show all changes
	}
	
	/**
	 * @param z0 - the complex number to be iterated
	 * @return Amount of iterations before the length of the given complex number exceeds 2
	 */
	static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		for (int i = 0; i<MAX; i++) {
			if (z.abs()>2.0)
				return i;
			z = z.times(z).plus(z0);
		}
		return MAX;
	}
	
	/**
	 * Runs after the initialization and handles all things related to navigation of the Mandelbrot set
	 */
	static void navigation() {
		while(true) {
			if (StdDraw.hasNextKeyTyped()) {			
				char keyTyped = StdDraw.nextKeyTyped();
				switch(keyTyped) {
					case 'w': centerDot = centerDot.addIm(MOVEFACTOR*width);
							  break;
					case 'a': centerDot = centerDot.addRe(-MOVEFACTOR*width);
					  		  break;
					case 'd': centerDot = centerDot.addRe(MOVEFACTOR*width);
							  break;
					case 's': centerDot = centerDot.addIm(-MOVEFACTOR*width);
							  break;
					case 'q': width *= ZOOMFACTOR;  
				        	  break;
					case 'e': width /= ZOOMFACTOR;
			  		          break;
				}
				drawFractal(); // redraw after each movement
			}
		}
	}
	
}


