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
		System.out.print(instruction);
		int num= sc.nextInt();		
		if (num ==1){
		SharedDataKWIC.run();
		} else if (num==2){
		ADTKWIC.run();
		} else {
			System.out.println("Please enter a valid option!");
		}
		sc.close();
	} 

	

}
