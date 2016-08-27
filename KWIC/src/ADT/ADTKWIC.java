// Implemented using solution 2, where Abstract data types are used.
// Each module provides an interface that allow other components to access data
// by invoking its methods. 
package ADT; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class InputReader {
	private static final String instruction = "Key in movies title and press enter.\n"
			+ "Press enter twice to display the result.";
	private static List<String> movieTitles = new ArrayList<String>();
	private static  String[] ignore = {"is","the","of","and","as","a","after"};
	private static List<String> wordsToIgnore = Arrays.asList(ignore);
	//method that read in Input.
		public  void readInput() {
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
	
	public List<String> getMovieTitles() {
		return movieTitles;
	}
	
	public List<String> getWordsToIgnore() {
		return wordsToIgnore;
	}
}

class CircularShift {
	private List<String> movieTitles;
	private List<String> wordsToIgnore;
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
				String firstWord = shifted.substring(0, firstWordEndIndex).toLowerCase();
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

public class ADTKWIC {
	private List<String> result;
	
	public static void run() {
		ADTKWIC kwic = new ADTKWIC();
		
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
			System.out.println(line.substring(0, 1).toUpperCase() +
					line.substring(1));
		}
	}
}
