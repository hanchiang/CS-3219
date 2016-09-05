package ADT;

import java.util.ArrayList;
import java.util.List;

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
			for (String movie : movieTitles) {
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
		} catch (Exception e) {
			System.out.println("Error! Only titles that can be KWIC are allowed.\n" + "Run the program to try again");
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