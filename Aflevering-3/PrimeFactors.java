import java.util.*;
	
public class PrimeFactors {

public static String primeList = "2"; //We initialize the list to 2 in order to simplify the code
public static long maxNumCheckedIfPrime = 2;
	
public static void main(String[] args) {
	Scanner console = new Scanner(System.in);
	while(true) {
		System.out.print("Enter an integer greater than 1: ");
		while (!console.hasNextLong()) {
			System.out.print("Invalid input, please try again: ");
			console.nextLine();
		}
		printPrimeFactors(console.nextLong());
	}	
}

	public static void expandPrimeList(long expandTarget) {
		Scanner primeLookup = null;
		boolean isPrime;
		for (long potentialPrime=maxNumCheckedIfPrime+1; potentialPrime<=expandTarget; potentialPrime++) {
			isPrime = true; // Assumed until disproven
			primeLookup = new Scanner(primeList);
			while (primeLookup.hasNext())
				if (potentialPrime%primeLookup.nextLong() == 0) {
					isPrime = false;
					break;
					}
			if (isPrime)
					primeList += " " + potentialPrime;
		}
		maxNumCheckedIfPrime = expandTarget;
	}

	public static void printPrimeFactors(long toBeFactored) {
		if (toBeFactored>maxNumCheckedIfPrime)
			expandPrimeList(toBeFactored);
		Scanner primeLookup = new Scanner(primeList);
		String primeFactors = "";
		long potentialFactor = 0;
		while (potentialFactor<=toBeFactored) {
			potentialFactor = primeLookup.nextLong();
			while (toBeFactored%potentialFactor == 0) {
				primeFactors += potentialFactor + ", ";
				toBeFactored /= potentialFactor;
			}
		}
		// Eclipse does not handle the escape command "\b", despite it being a standardized part of java. Test by some other means
		System.out.println("The prime factors are: [" + primeFactors + "\b\b]\n"); 
	}
}
