package ADT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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

	public void writeToFile() {
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

	// print first 20 items in the result
	public void printResult() {
		System.out.println("*****************RESULT*******************");
		int n = 1;
		for (String line : output) {
			if (n == 21) {
				System.out.println("Only first 20 items in the result in alphabetical order"
						+ " are shown.\nPlease refer to output.txt for full result");
				break;
			}
			System.out.println(n + ". " + line.substring(0, 1).toUpperCase() + line.substring(1));
			n++;
		}
	}
}