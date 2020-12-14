package machine;

public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}
	
	/** runMachine mais sans silentMode  */
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

	/** Fonction pour demarrer la machine */
	public static String runMachine(TuringMachine turingMachine1, String prog, boolean silentMode) {
		String res="";
		boolean done = turingMachine1.run(prog, silentMode);
		if (done == true) {
			res = "The input was accepted.";
		} 
		else {
			res = "The input was rejected.";
		}
		return res;
	}

}
