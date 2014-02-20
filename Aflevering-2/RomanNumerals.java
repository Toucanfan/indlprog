public class RomanNumerals {
	public static void main(String[] args) {
		test("", toRomanNumerals(0));
		test("I", toRomanNumerals(1));
		test("II", toRomanNumerals(2));
		test("V", toRomanNumerals(5));
		test("IV", toRomanNumerals(4));
		test("VI", toRomanNumerals(6));
		test("X", toRomanNumerals(10));
	}

	public static String toRomanNumerals(int number) {
		String romanNumeral = "";

		int digit = number % 10;

		switch (digit) {
			case 5:
				romanNumeral += "V";
				break;
			case 4:
				romanNumeral += "IV";
				break;
			case 9:
				romanNumeral += "IX";
				break;
			default:
				for (int i = 0; i < digit; i++){
					romanNumeral += "I";
				}
				break;
		}

		return romanNumeral;
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