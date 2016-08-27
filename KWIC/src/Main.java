import java.util.*;
import ADT.ADTKWIC;
import sharedData.SharedDataKWIC;
//class that provide interface for user to choose their preferred way of for implementation
//of KWIC to run
public class Main {
	private static final String instruction = "Run either 1.shared data KWIC or 2.ADT KWIC \nby pressing"
			+ " its corresponding index no. and press enter\n";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Boolean isValid = false;
		while (!isValid) {
		System.out.print(instruction);	
		String input = sc.nextLine();
		if (input.equals("1")){
			isValid = true;
		SharedDataKWIC.run();
		} else if (input.equals("2")){
			isValid = true;
		ADTKWIC.run();
		} else {	
			System.out.println("Please enter a valid option!");
		} 
		}
		sc.close();
	} 

	

}
