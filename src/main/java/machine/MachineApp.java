package machine;

/**
 * Main class for machine Enigma
 * This main class configure and run the TuringMachine according to the settings
 * provided by MachinesLibrary.buildEqualBinaryWords()
 */

public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}

	/**
	 *
	 * @param turingMachine1 the Machine Library containing all the settings
	 * @param prog the prog sentence needed to be check
	 * @return return the string sentence with the settings
	 */
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

	/**
	 *
	 * @param turingMachine1 The machine we will use
	 * @param prog the prog sentence to be check
	 * @param sillentMode boolean for the verbose
	 * @return return the final string information
	 */
	public static String runMachine(TuringMachine turingMachine1, String prog, boolean sillentMode) {
		String res="";
		boolean done = turingMachine1.run(prog, false);
		if (done == true) {
			res = "The input was accepted.";
		} 
		else {
			res = "The input was rejected.";
		}
		return res;
	}

}
