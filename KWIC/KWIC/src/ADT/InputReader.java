package ADT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class that contains methods for reading inputs
class InputReader {
	private static final String instruction = "\nKey in movies title and press enter.\n"
			+ "Press enter twice to move on to ignore words, and enter twice again.";
	private static List<String> movieTitles = new ArrayList<String>();
	private static List<String> wordsToIgnore = new ArrayList<String>();
	private static List<String> requiredWords = new ArrayList<String>();

	// method that read in Input.
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println(instruction);
		System.out.print("or enter \"read file\" to read file: ");
		String input = sc.nextLine();
		if (input.equals("read file")) {
			System.out.print("Enter the file name containing movies title: ");
			input = sc.nextLine();
			movieTitles = readFile(input);
			
			System.out.print("Enter the file name containing ignore words: ");
			input = sc.nextLine();
			wordsToIgnore = readFile(input);
			
			System.out.print("Enter the file name contain required words: ");
			input = sc.nextLine();
			requiredWords = readFile(input);
		} else {
			readFromConsole(sc);
		}
		sc.close();
	}
	
	public void readRequiredWords(Scanner sc) {
		System.out.println("Enter required words followed by enter.\n" + "Press enter twice to enter finish.");
		String input;
		while (true) {
			System.out.print("Enter required word: ");
			input = sc.nextLine();
			if (input.equals("")) {
				break;
			}
			requiredWords.add(input);
		}	
	}

	public void readIgnoreWords(Scanner sc) {
		System.out.println("Enter ignore word followed by enter.\n" + "Press enter twice to enter required words.");
		String input;
		while (true) {
			System.out.print("Enter ignore word: ");
			input = sc.nextLine();
			if (input.equals("")) {
				readRequiredWords(sc);
				break;
			}
			wordsToIgnore.add(input);
		}	
	}

	public void readMovieTitles(Scanner sc) {
		String input;
		while (true) {
			System.out.print("Enter movie titles: ");
			input = sc.nextLine();
			if (input.equals("")) {
				readIgnoreWords(sc);
				break;
			} else {
				movieTitles.add(input);
			}
		}
	}

	public void readFromConsole(Scanner sc) {
		readMovieTitles(sc);
	}

	public List<String> getMovieTitles() {
		return movieTitles;
	}

	public List<String> getWordsToIgnore() {
		return wordsToIgnore;
	}
	
	public List<String> getRequiredWords() {
		return requiredWords;
	}

	/**
	 * Read.txt file and return the arrayList containing lines stored in .txt
	 * file
	 * 
	 * @param fileName
	 * @return ArrayList<String>
	 */
	private static ArrayList<String> readFile(String name) {
		String line = "";
		ArrayList<String> inputList = new ArrayList<String>();
		try {

			FileReader fileReader = new FileReader(name);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				inputList.add(line);
			}
			bufferedReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exists");
		} catch (IOException e) {
			System.out.println("Error reading file");
		}

		return inputList;

	}
}
