// Implemented using solution 2, where Abstract data types are used.
// Each module provides an interface that allow other components to access data
// by invoking its methods. 
package ADT; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.*;
// class that contains methods for reading inputs
class InputReader {
	private static final String instruction = "Key in movies title and press enter.\n"
			+ "Press enter twice to move on to ignore words.";
	private static List<String> movieTitles = new ArrayList<String>();
//	private static  String[] ignore = {"is","the","of","and","as","a","after"};
	private static List<String> wordsToIgnore = new ArrayList<String>();
	//method that read in Input.
		public  void readInput() {
			Scanner sc = new Scanner(System.in);
			System.out.println(instruction);
			System.out.println("or enter \"read file\" to read file");
			String input = sc.nextLine();
			if (input.equals("read file")) {
				System.out.print("Enter the filename containing movies title:");
				input = sc.nextLine();
				movieTitles = readFile(input);
				System.out.print("Enter the filename containing ignore words:");
				input = sc.nextLine();
				wordsToIgnore = readFile(input);
				
			} else {
				movieTitles.add(input);
			while (true) {
				System.out.print("Enter movie titles: ");
				  input = sc.nextLine();
				if (input.equals("")) {
					System.out.println("Enter ignore word followed by enter.\n" +
							"Press enter twice to display the result.");
					while (true) {
						System.out.print("Enter ignore word: ");
						input = sc.nextLine();
						if (input.equals("")) {
							break;
						}
						wordsToIgnore.add(input);
					} break;
				}
			
				movieTitles.add(input);
			}
			}
			sc.close();
		}
	
	public List<String> getMovieTitles() {
		return movieTitles;
	}
	
	public List<String> getWordsToIgnore() {
		return wordsToIgnore;
	}
	/**
	 * Read.txt file and return the arrayList containing lines stored in .txt file
	 * 
	 * @param fileName
	 * @return ArrayList<String>
	 */
	private static  ArrayList<String> readFile(String name){
        String line = "";
        ArrayList<String> inputList = new ArrayList<String>();
        try {
        	
            FileReader fileReader = new FileReader(name);

            BufferedReader bufferedReader = new BufferedReader(fileReader);				
				while((line = bufferedReader.readLine()) != null) {
					inputList.add(line);  
			}			
            bufferedReader.close();   
            
        }catch(FileNotFoundException e) {
            System.out.println("File does not exists");                
        }catch(IOException e) {
            System.out.println("Error reading file");                   
        }

       
        return inputList;
	}
}
//class containing methods for circular shift
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
		try {
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
		}} catch (Exception e) {
			System.out.println("Error! Only titles that can be KWIC are allowed.\n" + 
					"Run the program to try again");
			System.exit(0);
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
// class containing methods to run ADT KWIC
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
		System.out.println("*****************RESULT*******************");
		for (String line : result) {
			System.out.println(line.substring(0, 1).toUpperCase() +
					line.substring(1));
		}
	}

}
