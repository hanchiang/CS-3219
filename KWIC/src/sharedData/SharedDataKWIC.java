// Implemented using solution 1, where the main program calls the respective
// functions, and the data is shared between the components.
package sharedData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SharedDataKWIC {
	private static final String instruction = "Key in movies title and press enter.\n"
			+ "Press enter twice to display the result.";
	private static List<String> movieTitles = new ArrayList<String>();
	private static List<String> output = new ArrayList<String>();
	private static  String[] ignore = {"is","the","of","and","as","a","after"};
	private static List<String> wordsToIgnore = Arrays.asList(ignore);
	// All data are shared and accessible within the class when this method is run.
	public static void run() {
		readInput();
		circularShift();
		alphabetize();
		printResult();
	}
	//method that read in Input.
	public static void readInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println(instruction);
		while (true) {
			System.out.print("Enter movie titles: ");
			 String input = sc.nextLine();
			if (input.equals("")) {
				break;
			}
		
			movieTitles.add(input);
			
	}
		
		
		sc.close();
	}
	//method that rearrange the order of words in the title based on the wordsToIgnore list.
	public static void circularShift() {
		try {
		String shifted;
		for(String movie : movieTitles) {
			shifted = movie;
			int numOfWords = movie.split(" ").length;
			for (int i = 0; i < numOfWords; i++) {
				int firstWordEndIndex = shifted.indexOf(" ");
				String firstWord = shifted.substring(0, firstWordEndIndex).toLowerCase();
				if (isKeywordInIgnoreList(firstWord)) {
					// Do nothing
				} else {
					output.add(shifted);
				}
				// Shift first word to the end of the sentence
				shifted = shifted.substring(firstWordEndIndex + 1) + " " + firstWord;
			}
		}
		} catch (Exception e) {
			  System.out.println("Error! Only titles that can be KWIC are allowed.\n" + 
			"Run the program to try again");
			}
	}
	// method that arrange items in output in alphabetical order
	public static void alphabetize() {
		Collections.sort(output);
	}
	/**
	 * method that check if the keyword is in ignore list
	 * 
	 * @param word
	 *	return boolean
	 */
	public static boolean isKeywordInIgnoreList(String word) {
		return wordsToIgnore.contains(word);
	}
	// print result with 1st letter of the title in upper case.
	public static void printResult() {
		for (String line : output) {
			System.out.println(line.substring(0, 1).toUpperCase() +
					line.substring(1));
		}
	}
}
