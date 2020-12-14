package machine;

/**
 * 
 * Classe principal de la machine de Turing
 * Elle va servir à initialiser notre machine de Turing et à passer les paramètres
 * 
 * @author Mikrail
 *
 */
public class MachineApp {

	/**
	 * Nous initialisons notre machine de Turing et on essaie de décoder le code suivant 010000110101#010000110101
	 * @param args
	*/
	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}
	
	/**
	 * Cette fonction permet d'encoder le code qu'on lui passe en paramètre à la machine passer en paramètre
	 * On passe la machine en mode silance 
	 * @param turingMachine1
	 * @param prog
	 * @return
	*/
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

	/**
	 * 
	 * 
	 * 
	 * @param turingMachine1
	 * @param prog
	 * @param sillentMode
	 * @return
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
