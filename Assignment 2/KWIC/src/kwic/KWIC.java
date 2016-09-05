// Implemented using solution 2, where Abstract data types are used.
// Each module provides an interface that allow other components to access data
// by invoking its methods. 
package kwic;

// class containing methods to run ADT KWIC
public class KWIC {
	public static void main(String args[]) {
		InputReader ir = new InputReader();
		ir.readInput();
		CircularShift cs = new CircularShift(ir);
		cs.shift();
		Output out = new Output(cs);
		out.alphabetize();
		out.writeToFile();
		out.printResult();
		
		System.out.println("Results are written to output.txt!");
	}

}
