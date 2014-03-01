import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
import java.lang.Math;

public class BuffonsNeedle {
	public static void main(String[] args) {
		System.out.printf("Welcome to BuffonsNeedle\n\n");
		System.out.printf("This program will compute pi by throwing Buffons Needle as many times as you want.\n");
		System.out.printf("How many times should we throw the needle? ");
		int nIterations = getPositiveIntegerInput();
		do {
			System.out.printf("Computing...\r");
			double apprxPi = computePi(nIterations);
			System.out.printf("Computed value of pi: %f\n\n", apprxPi);
			System.out.printf("How many times should we now throw the needle? ");
			nIterations = getPositiveIntegerInput();
		} while (true);
	}

	public static int getPositiveIntegerInput() {
		Scanner console = new Scanner(System.in);
		int input;
		while (true) {
			try {
				input = console.nextInt();
				if (input > 0)
					return input;
				else
					System.out.printf("Invalid input. Please type again: ");
			} catch (InputMismatchException e) {
				System.out.printf("Invalid input. Please type again: ");
				console.nextLine(); /* Invalid input has not been removed from input buffer - do that */
			}
		}
	}

	public static double computePi(int nIterations) {
		Random rnGen = new Random();
		int successCount = 0;
		double dist; /* Distance from needle bottom down to closest line */
		double angle; /* Angle between needle and line below it */
		for (int i = 0; i < nIterations; i++) {
			dist = 2 * rnGen.nextDouble(); 
			angle = Math.PI * rnGen.nextDouble();
			if (Math.sin(angle) + dist >= 2)
				successCount++;
		}
		return nIterations/(double)(successCount);
	}
}
