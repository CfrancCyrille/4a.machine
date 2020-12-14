package machine;

public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}
	
	/**
	 * 
	 * @param turingMachine1 la machine de turing à lancer
	 * @param prog une string qui correspond à l'input
	 * @return lance la fonction runMachine contenant une machine de Turing, un String et un boolean
	 */
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

	/**
	 * Permet de vérifier que le lancement de la machine de Turing à bien été fait selon les règles, et déclenche le lancement de la machine
	 * @param turingMachine1 La machine de Turing que l'on souhaite lancer
	 * @param prog l'input de la machine de Turing
	 * @param sillentMode 
	 * @return res , une String qui indique si l'input a été accepté après lancement de la machine
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
