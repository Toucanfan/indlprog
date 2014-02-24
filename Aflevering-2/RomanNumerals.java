public class RomanNumerals {
	public static void main(String[] args) {
		test("", toRomanNumerals(0));
		test("I", toRomanNumerals(1));
		test("II", toRomanNumerals(2));
		test("V", toRomanNumerals(5));
		test("IV", toRomanNumerals(4));
		test("VI", toRomanNumerals(6));
		test("IX", toRomanNumerals(9));

		test("X", toRomanNumerals(10));
		test("XX", toRomanNumerals(20));
		test("L", toRomanNumerals(50));
		test("XL", toRomanNumerals(40));
		test("LX", toRomanNumerals(60));
		test("XC", toRomanNumerals(90));

		test("C", toRomanNumerals(100));
		test("CC", toRomanNumerals(200));
		test("D", toRomanNumerals(500));

		test("MI", toRomanNumerals(1001));
		test("MM", toRomanNumerals(2000));

		test("MMXIV", toRomanNumerals(2014));
		test("MCMXCIX", toRomanNumerals(1999));
	}

	public static String toRomanNumerals(int number) {
		String romanNumeral = "";

		int digit1 = number % 10;

		romanNumeral = generalNumeral(digit1, "I", "V", "X") + romanNumeral;

		int digit10 = (number / 10) % 10;

		romanNumeral = generalNumeral(digit10, "X", "L", "C") + romanNumeral;

		int digit100 = (number / 100) % 10;

		romanNumeral = generalNumeral(digit100, "C", "D", "M") + romanNumeral;

		int digit1000 = (number / 1000);

		for (int i = 0; i < digit1000; i++){
			romanNumeral = "M" + romanNumeral;
		}

		return romanNumeral;
	}

	public static String generalNumeral(int digit, String oner, String fiver, String tenner) {
		// This method implements the composition of roman numerals.
		//
		// No matter what power of ten a roman numeral represents it
		// will still follow a common pattern:
		//  - There is a seperate symbol for 1, 5 and 10 times the digit
		//    (e.g. I, V and X)
		//  - The counting pattern is the same: I, II, III, IV is in principle
		//    done for all digit places
		//
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

				if(digit==5) {
					_tmp = fiver;
				} else if (digit > 5) {
					_tmp += fiver;
					for (int i = 0; i < (digit - 5); i++){
						 _tmp += oner;
					}
				} else {
					for (int i = 0; i < digit; i++){
						 _tmp += oner;
					}
				}

				return _tmp;
		}
	}

	public static void test(String target, String test) {
		if (!target.equals(test)) {
			System.out.println("Expected: " + target);
			System.out.println("Got: " + test);
		} else {
			System.out.println("Passed!");
		}

		System.out.println("");
	}
}