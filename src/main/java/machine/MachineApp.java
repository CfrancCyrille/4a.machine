package machine;

/**
 * classe main principale pour la machine de Turing et Enigma
 * cette classe configure instancie une marchine de Turing grâce
 * a la méthode buildEqualBinaryWords() de MachinesLibrary
 */
public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}

	/**
	 * Lance la seconde méthode runMachine avec sillentMode d'office à false
	 * @param turingMachine1 notre machine de Turing
	 * @param prog la chaine de character que nous voulons decrypter
	 * @return un message selon si c'est une réussite ou non (méthode juste en dessous)
	 */
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

	/**
	 * Lance la méthode run de notre objet turingMachine1 placer en paramètre
	 * @param turingMachine1 notre machine de Turing
	 * @param prog la chaine de character que nous voulons decrypter
	 * @param sillentMode
	 * @return un message selon si c'est une réussite ou non
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
