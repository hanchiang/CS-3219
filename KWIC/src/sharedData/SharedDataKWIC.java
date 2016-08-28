// Implemented using solution 1, where the main program calls the respective
// functions, and the data is shared between the components.
package sharedData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SharedDataKWIC {
	private static final String instruction = "\nKey in movies title and press enter.\n"
			+ "Press enter twice to move on to ignore words.";
	private static List<String> movieTitles = new ArrayList<String>();
	private static List<String> output = new ArrayList<String>();
	
	private static List<String> wordsToIgnore = new ArrayList<String>();

	// All data are shared and accessible within the class when this method is
	// run.
	public static void run() {
		readInput();
		circularShift();
		alphabetize();
		writeToFile();
		System.out.println("Results are written to output.txt!");
	}

	// method that read in Input.
	public static void readInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println(instruction);
		System.out.print("or enter \"read file\" to read file: ");
		String input = sc.nextLine();
		if (input.equals("read file")) {
			System.out.print("Enter the filename containing movies title: ");
			input = sc.nextLine();
			movieTitles = readFile(input);
			System.out.print("Enter the filename containing ignore words: ");
			input = sc.nextLine();
			wordsToIgnore = readFile(input);

		} else {
			movieTitles.add(input);
			while (true) {
				System.out.print("Enter movie titles: ");
				input = sc.nextLine();
				if (input.equals("")) {
					System.out.println(
							"Enter ignore word followed by enter.\n" + "Press enter twice to display the result.");
					while (true) {
						System.out.print("Enter ignore word: ");
						input = sc.nextLine();
						if (input.equals("")) {
							break;
						}
						wordsToIgnore.add(input);
					}
					break;
				}

				movieTitles.add(input);
			}
		}
		sc.close();
	}

	// method that rearrange the order of words in the title based on the
	// wordsToIgnore list.
	public static void circularShift() {
		try {
			String shifted;
			for (String movie : movieTitles) {
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
			System.out.println("Error! Only titles that can be KWIC are allowed.\n" + "Run the program to try again");
			System.exit(0);
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
	 *            return boolean
	 */
	public static boolean isKeywordInIgnoreList(String word) {
		return wordsToIgnore.contains(word);
	}

	// print result with 1st letter of the title in upper case.
	public static void writeToFile() {
		BufferedWriter bw = null;

		try {
			File file = new File("output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (String movie : output) {
				bw.write(movie.substring(0, 1).toUpperCase() + movie.substring(1));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
