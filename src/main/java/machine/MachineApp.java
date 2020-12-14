package machine;

//classe qui contient les fonctions pour le main
public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}

	/**
	 * Demare la machine avec un argument en moins, il n'y a pas d'ecriture dans la console
	 * @param turingMachine1 la machine a utiliser
	 * @param prog le texte a chiffrer
	 * @return un string chiffre
	 */
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

	/**
	 * Demare la machine avec tous les argument
	 * @param turingMachine1 la machine a utiliser
	 * @param prog le texte a chiffrer
	 * @param sillentMode Si on veut un retour sur la console
	 * @return Si le chiffrage c'est bien passe ou non
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
