import java.util.Scanner;
import java.util.Random;
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
		/* FIXME: Validate user input */
		return console.nextInt();
	}

	public static double computePi(int nIterations) {
		Random rnGen = new Random();
		int successCount = 0;
		double dist;
		double angle;
		for (int i = 0; i < nIterations; i++) {
			dist = 2 * rnGen.nextDouble(); /*since nextDouble() returns a double between 0 and 1.*/
			angle = Math.PI * rnGen.nextDouble();
			if (Math.sin(angle) + dist >= 2)
				successCount++;
		}
		return nIterations/(double)(successCount);
	}
}
