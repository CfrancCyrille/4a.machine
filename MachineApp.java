package machine;
/** Classe principale de la création de la machine Enigma
 * 
 * @author thomas
 *
 */

public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}
	
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		/** Permet de lancer la machine de turing avec le string donné en paramètre, avec sillentMode à False
		 * 
		 */
		return runMachine(turingMachine1, prog, false);
	}

	public static String runMachine(TuringMachine turingMachine1, String prog, boolean sillentMode) {
		/** Permet de lancer la machine de turing avec le string donné en paramètre, et de façon silencieuse ou non
		 * 
		 */
		String res="";
		boolean done = turingMachine1.run(prog, sillentMode);
		if (done == true) {	
			res = "The input was accepted.";
		} 
		else {
			res = "The input was rejected.";
		}
		return res;
	}

}
