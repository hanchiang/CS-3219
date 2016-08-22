// Implemented using solution 1, where the main program calls the respective
// functions, and the data is shared between the components.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class KWIC {
	private static List<String> movieTitles = new ArrayList<String>();
	private static List<String> output = new ArrayList<String>();
	private static HashSet<String> wordsToIgnore = new HashSet<String>();

	public static void main(String[] args) {
		readInput();
		circularShift();
		alphabetize();
		printResult();
	}

	public static void readInput() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter movie titles, separated by a comma: ");
		String input = sc.nextLine();
		String[] movieTitlesInput = input.split(",");
		for (String movie : movieTitlesInput) {
			movieTitles.add(movie.trim());
		}
		
		System.out.print("Enter words to ignore, separated by a comma: ");
		input = sc.nextLine();
		String[] ignoreWords = input.split(",");
		for (String ignore : ignoreWords) {
			wordsToIgnore.add(ignore.trim());
		}
		
		sc.close();
	}
	
	public static void circularShift() {
		String shifted;
		for(String movie : movieTitles) {
			shifted = movie;
			int numOfWords = movie.split(" ").length;
			for (int i = 0; i < numOfWords; i++) {
				int firstWordEndIndex = shifted.indexOf(" ");
				String firstWord = shifted.substring(0, firstWordEndIndex);
				if (isKeywordInIgnoreList(firstWord)) {
					// Do nothing
				} else {
					output.add(shifted);
				}
				// Shift first word to the end of the sentence
				shifted = shifted.substring(firstWordEndIndex + 1) + " " + firstWord;
			}
		}
	}
	
	public static void alphabetize() {
		Collections.sort(output);
	}
	
	public static boolean isKeywordInIgnoreList(String word) {
		return wordsToIgnore.contains(word);
	}
	
	public static void printResult() {
		for (String line : output) {
			System.out.println(line.substring(0, 1).toUpperCase() +
					line.substring(1));
		}
	}
}
