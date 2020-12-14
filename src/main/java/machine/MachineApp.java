package machine;

public class MachineApp {

	public static void main(String[] args) {
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();

		String res = runMachine(turingMachine1, "010000110101#010000110101");
		
		System.out.println(res);

	}
	
        /**
         * Permet de run la machine sans avoir à paramétré le paramètre sillentMode qui sera à FALSE
         * @param turingMachine1 objet TuringMachine
         * @param prog String -> message à encoder
         * @return String -> message encodé
         */
	public static String runMachine(TuringMachine turingMachine1, String prog) {
		return runMachine(turingMachine1, prog, false);
	}

        /**
         * Permet de run la machine et de paramétré le paramètre sillentMode
         * @param turingMachine1 objet TuringMachine
         * @param prog String -> message à encoder
         * @param sillentMode boolean qui permet d'afficher des informations suplémentire dans la consol
         * @return String -> message encodé
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
