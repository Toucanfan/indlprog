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
	static Complex CenterDot = new Complex();
	static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		initialization();
		drawFractal();
		navigation();
	}

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
		
		CenterDot = CenterDot.setRe(askUserDouble("Input the real part of the center coordinate (if unsure, use 0): "));
		CenterDot = CenterDot.setIm(askUserDouble("Input the imaginary part of the center coordinate (if unsure, use 0): "));
		width = Math.abs(askUserDouble("Input the width of the coordinate system (if unsure, use 2): "));
		gridSize = Math.abs(askUserDouble("Input desired grid size (use low grid size for smooth navigation): "));
	}

	static double askUserDouble(String message) {
		System.out.print(message);
		while (!console.hasNextDouble()) {
			System.out.print("Invalid input, try again: ");
			console.nextLine();
		}
		return console.nextDouble();
	}
	
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
	
	static void drawFractal() {
		double topX = CenterDot.getRe() + width/2;
		double topY = CenterDot.getIm() + width/2;
		double bottomX = CenterDot.getRe() - width/2;
		double bottomY = CenterDot.getIm() - width/2;
		
		StdDraw.show(0);
		StdDraw.clear();
		StdDraw.setXscale(bottomX, topX);
        StdDraw.setYscale(bottomY,topY);
        StdDraw.setPenRadius(PENSIZEFACTOR/(gridSize-1));

        Complex tempZ;
        for(int i=0; i<gridSize; i++)
			for(int j=0; j<gridSize; j++) {
				tempZ = new Complex(bottomX+i*width/(gridSize-1),bottomY+j*width/(gridSize-1));
				StdDraw.setPenColor(palette[iterate(tempZ)]);
				StdDraw.point(tempZ.getRe(),tempZ.getIm());
			}

        StdDraw.show(0);
	}

	static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		for (int i = 0; i<MAX; i++) {
			if (z.abs()>2.0)
				return i;
			z = z.times(z).plus(z0);
		}
		return MAX;
	}
	
	static void navigation() {
		while(true) {
			if (StdDraw.hasNextKeyTyped()) {			
				char keyTyped = StdDraw.nextKeyTyped();
				switch(keyTyped) {
					case 'w': CenterDot = CenterDot.addIm(MOVEFACTOR*width);
							  break;
					case 'a': CenterDot = CenterDot.addRe(-MOVEFACTOR*width);
					  		  break;
					case 'd': CenterDot = CenterDot.addRe(MOVEFACTOR*width);
							  break;
					case 's': CenterDot = CenterDot.addIm(-MOVEFACTOR*width);
							  break;
					case 'q': width *= ZOOMFACTOR;  
				        	  break;
					case 'e': width /= ZOOMFACTOR;
			  		          break;
				}
				drawFractal();
			}
		}
	}
	
}


