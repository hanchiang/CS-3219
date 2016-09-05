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
			+ "Press enter twice to move on to ignore words.";
	private static List<String> movieTitles = new ArrayList<String>();
	private static List<String> wordsToIgnore = new ArrayList<String>();

	// method that read in Input.
	public void readInput() {
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

	public List<String> getMovieTitles() {
		return movieTitles;
	}

	public List<String> getWordsToIgnore() {
		return wordsToIgnore;
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
