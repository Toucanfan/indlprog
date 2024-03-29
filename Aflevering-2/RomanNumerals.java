import java.util.Scanner;

public class RomanNumerals {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("Indtast et tal: ");
		int input = console.nextInt();
		System.out.println(toRomanNumerals(input));
	}

	public static String toRomanNumerals(int number) {
		if(number <= 0) {
			throw new IllegalArgumentException("Error: Input must be above zero.");
		}

		String romanNumeral = "";

		// First all of the thousands (M) are added. Notice that integer
		// division is used.

		int digit1000 = (number / 1000);

		for (int i = 0; i < digit1000; i++){
			romanNumeral += "M";
		}

		// For the first, second and third to last digit we generate the
		// representing roman numeral.

		romanNumeral += generalNumeral(number, 100, "C", "D", "M");

		romanNumeral += generalNumeral(number, 10, "X", "L", "C");

		romanNumeral += generalNumeral(number, 1, "I", "V", "X");

		return romanNumeral;
	}

	public static String generalNumeral(int number, int digit_place, String oner, String fiver, String tenner) {
		// This method implements the composition of roman numerals.
		//
		// No matter what power of ten a roman numeral represents it
		// will still follow a common pattern:
		//  - There is a seperate symbol for 1, 5 and 10 times the digit
		//    (e.g. I, V and X)
		//  - The counting pattern is the same: I, II, III, IV is in principle
		//    done for all digit places

		int digit = (number / digit_place) % 10;

		// There are two specials cases; we use a switch statement to differentiate
		switch (digit) {
			// The first special case is for '4'. Here we need to prepend the
			// symbol for one to the symbol for five.
			//
			// We return the result immediately
			case 4:
				return oner + fiver;
			// The special case for 9 is similar
			case 9:
				return oner + tenner;
			default:
				String _tmp = "";

				// Prepending 'five'
				if(digit >= 5) {
					_tmp += fiver;
					digit -= 5;
				}

				// Because 5 is subtracted when needed, we just need to insert
				// ones.
				for (int i = 0; i < digit; i++){
					 _tmp += oner;
				}

				return _tmp;
		}
	}
}