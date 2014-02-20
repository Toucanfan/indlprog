public class RomanNumerals {
	public static void main(String[] args) {
		test("", toRomanNumerals(0));
		test("I", toRomanNumerals(1));
		test("II", toRomanNumerals(2));
		test("V", toRomanNumerals(5));
		test("IV", toRomanNumerals(4));
		test("VI", toRomanNumerals(6));

		test("X", toRomanNumerals(10));
		test("XX", toRomanNumerals(20));
		test("L", toRomanNumerals(50));
		test("XL", toRomanNumerals(40));
		test("LX", toRomanNumerals(60));
		test("XC", toRomanNumerals(90));
	}

	public static String toRomanNumerals(int number) {
		String romanNumeral = "";

		int digit1 = number % 10;

		romanNumeral += generalNumeral(digit1, "I", "V", "X");

		int digit10 = (number / 10) % 10;

		romanNumeral += generalNumeral(digit10, "X", "L", "C");

		return romanNumeral;
	}

	public static String generalNumeral(int digit, String oner, String fiver, String tenner) {
		switch (digit) {
			case 5:
				return fiver;
			case 4:
				return oner + fiver;
			case 9:
				return oner + tenner;
			default:
				String _tmp = "";
				for (int i = 0; i < digit; i++){
					 _tmp += oner;
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