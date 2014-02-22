import java.util.*;
import java.lang.*;
import java.io.*;
	
public class Palindrome {
	
	public static String removeNonLetters(String inputString){
		String outputString = "";
		for (int i=0; i<inputString.length(); i++)
			if (Character.isLetter(inputString.charAt(i))){
				outputString += inputString.charAt(i);
			}
		return outputString;
	}
		
		
	public static String reverseString(String inputString){
		String outputString = "";
		for (int i=inputString.length()-1; i>=0; i--)
			outputString+=inputString.charAt(i);
		return outputString;
	}
			
			
	public static void checkPalindrome(){
		Scanner console = new Scanner(System.in);
		System.out.print("Enter line to check: ");
		String inputLine = console.nextLine();
		//Sammenligner input strengen med den omvendte input streng. Alt andet end bogstaver ignoreres
		boolean isPalindrome = removeNonLetters(inputLine.toLowerCase()).equals(reverseString(removeNonLetters(inputLine.toLowerCase())));
		if (isPalindrome)
			System.out.println("Input is a palindrome!\n");
		else
			System.out.println("Input is NOT a palindrome!\n");
	}


	public static void main (String[] args){
	 while (true)	
		checkPalindrome();
 	}
}
