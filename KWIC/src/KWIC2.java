// Implemented using solution 2, where Abstract data types are used.
// Each module provides an interface that allow other components to access data
// by invoking its methods. 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class InputReader {
	private List<String> movieTitles = new ArrayList<String>();
	private HashSet<String> wordsToIgnore = new HashSet<String>();
	
	public void readInput() {
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
	
	public List<String> getMovieTitles() {
		return movieTitles;
	}
	
	public HashSet<String> getWordsToIgnore() {
		return wordsToIgnore;
	}
}

class CircularShift {
	private List<String> movieTitles;
	private HashSet<String> wordsToIgnore;
	private List<String> shiftedList;
	
	public CircularShift(InputReader ir) {
		movieTitles = ir.getMovieTitles();
		wordsToIgnore = ir.getWordsToIgnore();
		shiftedList = new ArrayList<String>();
	}
	
	public void shift() {
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
					shiftedList.add(shifted);
				}
				// Shift first word to the end of the sentence
				shifted = shifted.substring(firstWordEndIndex + 1) + " " + firstWord;
			}
		}
	}
	
	public boolean isKeywordInIgnoreList(String word) {
		return wordsToIgnore.contains(word);
	}
	
	public List<String> getShiftedList() {
		return shiftedList;
	}
}

class Output {
	private List<String> output;
	
	public Output(CircularShift cs) {
		output = cs.getShiftedList();
	}
	
	public void alphabetize() {
		Collections.sort(output);
	}
	
	public List<String> getOutput() {
		return output;
	}
}

public class KWIC2 {
	private List<String> result;
	
	public static void main(String[] args) {
		KWIC2 kwic = new KWIC2();
		
		InputReader ir = new InputReader();
		ir.readInput();
		CircularShift cs = new CircularShift(ir);
		cs.shift();
		Output out = new Output(cs);
		out.alphabetize();
		
		kwic.result = out.getOutput();
		kwic.printResult();
	}
	
	
	public void printResult() {
		for (String line : result) {
			System.out.println(line);
		}
	}
}
