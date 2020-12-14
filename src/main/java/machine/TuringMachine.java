package machine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TuringMachine {

	private Set<String> stateSpace;
	private Set<Transition> transitionSpace;
	private String startState; //etat de départ
	private String acceptState;
	private String rejectState;

	private String tape;
	private String currentState;
	private int currentSymbol;

	class Transition {
		String readState; //lecture de l'etat
		char readSymbol;
		String writeState; //lecture de l'etat
		char writeSymbol;
		boolean moveDirection; // true is right, false is left

		boolean isConflicting(String state, char symbol) {
			if (state.equals(readState) && symbol == readSymbol) {
				return true;
			} else {
				return false;
			}
		}
	}
		/**
		 * Constructeur de la machine
		 */
	public TuringMachine() {
		stateSpace = new HashSet<String>(); //Hashage
		transitionSpace = new HashSet<Transition>(); //Hashage
		startState = new String("");
		acceptState = new String("");
		rejectState = new String("");
		tape = new String("");
		currentState = new String("");
		currentSymbol = 0;
	}
	/**
	 * Fonction principale
	 * @param input a chiffrer
	 * @param silentmode active les retour dans la console
	 * @return true si la fonction c'est bien déroulé ou false sinon
	 */
	public boolean run(String input, boolean silentmode) {
		currentState = startState;
		tape = input;

		while (!currentState.equals(acceptState) && !currentState.equals(rejectState)) { // tant que l'etat actuel est different de acceptState et que l'etat actuel est different de rejectState
			boolean foundTransition = false; //bool pour un arret
			Transition CurrentTransition = null;

			if (silentmode == false) { //ecriture sur la console
				if (currentSymbol > 0) {
					System.out.println(tape.substring(0, currentSymbol) + " " + currentState + " "
							+ tape.substring(currentSymbol));
				} else {
					System.out.println(" " + currentState + " " + tape.substring(currentSymbol));
				}
			}

			Iterator<Transition> TransitionsIterator = transitionSpace.iterator();
			while (TransitionsIterator.hasNext() && foundTransition == false) { 
				Transition nextTransition = TransitionsIterator.next();
				if (nextTransition.readState.equals(currentState)
						&& nextTransition.readSymbol == tape.charAt(currentSymbol)) {
					foundTransition = true;
					CurrentTransition = nextTransition;
				}
			}

			if (foundTransition == false) {
				System.err.println("There is no valid transition for this phase! (state=" + currentState + ", symbol="
						+ tape.charAt(currentSymbol) + ")");
				return false;
			} else {
				currentState = CurrentTransition.writeState;
				char[] tempTape = tape.toCharArray();
				tempTape[currentSymbol] = CurrentTransition.writeSymbol;
				tape = new String(tempTape);
				if (CurrentTransition.moveDirection == true) {
					currentSymbol++;
				} else {
					currentSymbol--;
				}

				if (currentSymbol < 0)
					currentSymbol = 0;

				while (tape.length() <= currentSymbol) {
					tape = tape.concat("_");
				}

			}

		}

		if (currentState.equals(acceptState)) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * Fonciton pour ajouter un etat
	 * @param newState ajout d'un etat
	 * @return renvoi true si l'etat n'existait pas, false sinon
	 */
	public boolean addState(String newState) {
		if (stateSpace.contains(newState)) {
			return false;
		} else {
			stateSpace.add(newState);
			return true;
		}
	}

	/**
	 * Fonction pour definir l'etat de depart
	 * @param newStartState ajout d'un etat de depart
	 * @return renvoi true si l'etat n'existait pas, false sinon
	 */
	public boolean setStartState(String newStartState) {
		if (stateSpace.contains(newStartState)) {
			startState = newStartState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Defini AcceptState
	 * @param newAcceptState etat accepter
	 * @return renvoi true si l'etat n'existait pas, false sinon
	 */
	public boolean setAcceptState(String newAcceptState) {
		if (stateSpace.contains(newAcceptState) && !rejectState.equals(newAcceptState)) {
			acceptState = newAcceptState;
			return true;
		} else {
			return false;
		}

	}
	/**
	 * Defini un etat de rejet
	 * @param newRejectState Etat
	 * @return renvoi true si l'etat n'existait pas, false sinon
	 */
	public boolean setRejectState(String newRejectState) {
		if (stateSpace.contains(newRejectState) && !acceptState.equals(newRejectState)) {
			rejectState = newRejectState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * permet de faire une transition d'etat
	 * @param rState etat pour lire
	 * @param rSymbol symbol pour lire
	 * @param wState etat pour ecrire
	 * @param wSymbol symbol pour ecrire
	 * @param mDirection direction true ou false
	 * @return True si c'est bon , false sinon
	 */
	public boolean addTransition(String rState, char rSymbol, String wState, char wSymbol, boolean mDirection) {
		if (!stateSpace.contains(rState) || !stateSpace.contains(wState)) {
			return false;
		}

		boolean conflict = false;
		Iterator<Transition> TransitionsIterator = transitionSpace.iterator();
		while (TransitionsIterator.hasNext() && conflict == false) {
			Transition nextTransition = TransitionsIterator.next();
			if (nextTransition.isConflicting(rState, rSymbol)) {
				conflict = true;
			}

		}
		if (conflict == true) {
			return false;
		} else {
			Transition newTransition = new Transition();
			newTransition.readState = rState;
			newTransition.readSymbol = rSymbol;
			newTransition.writeState = wState;
			newTransition.writeSymbol = wSymbol;
			newTransition.moveDirection = mDirection;
			transitionSpace.add(newTransition);
			return true;
		}
	}

}
