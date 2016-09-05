package kwic;

import java.util.ArrayList;
import java.util.List;

//class containing methods for circular shift
class CircularShift {
	private List<String> movieTitles;
	private List<String> wordsToIgnore;
	private List<String> shiftedList;
	private List<String> requiredWords;

	public CircularShift(InputReader ir) {
		movieTitles = ir.getMovieTitles();
		wordsToIgnore = ir.getWordsToIgnore();
		shiftedList = new ArrayList<String>();
		requiredWords = ir.getRequiredWords();
	}

	public void shift() {
		String shifted;

		for (String movie : movieTitles) {
			shifted = movie;
			int numOfWords = movie.split(" ").length;
			if (numOfWords == 1) {
				if (isKeywordInIgnoreList(shifted)) {
					// Do nothing
				} else {
					shiftedList.add(shifted);
				}
			} else {
				for (int i = 0; i < numOfWords; i++) {
					int firstWordEndIndex = shifted.indexOf(" ");
					String firstWord = shifted.substring(0, firstWordEndIndex).toLowerCase();
					if (isKeywordInRequiredList(firstWord)) {
						shiftedList.add(shifted);
					} else {
						// Do nothing
					}
					// Shift first word to the end of the sentence
					shifted = shifted.substring(firstWordEndIndex + 1) + " " + firstWord;
				}
			}
		}
	}

	public boolean isKeywordInIgnoreList(String word) {
		return wordsToIgnore.contains(word);
	}
	
	public boolean isKeywordInRequiredList(String word) {
		return requiredWords.contains(word);
	}

	public List<String> getShiftedList() {
		return shiftedList;
	}
}