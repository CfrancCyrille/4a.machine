package machine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TuringMachine {

	private Set<String> stateSpace;
	private Set<Transition> transitionSpace;
	private String startState;
	private String acceptState;
	private String rejectState;

	private String tape;
	private String currentState;
	private int currentSymbol;
	
	/**
	 * Une sous classe permettant de créer les transitions de la machine de Turing
	 * 
	 *
	 */
	class Transition {
		String readState;
		char readSymbol;
		String writeState;
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
	 * Un constructeur qui permet de créer une machine de Turing
	 */
	public TuringMachine() {
		stateSpace = new HashSet<String>();
		transitionSpace = new HashSet<Transition>();
		startState = new String("");
		acceptState = new String("");
		rejectState = new String("");
		tape = new String("");
		currentState = new String("");
		currentSymbol = 0;
	}

	/**
	 * Fonction de lancement de la machine de Turing
	 * @param input l'input au lancement de la machine
	 * @param silentmode
	 * @return Un boolean qui vérifie si tout s'est bien passé , si létat actuel est accepté ou non
	 */
	public boolean run(String input, boolean silentmode) {
		currentState = startState;
		tape = input;

		while (!currentState.equals(acceptState) && !currentState.equals(rejectState)) {
			boolean foundTransition = false;
			Transition CurrentTransition = null;

			if (silentmode == false) {
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
	 * 
	 * @param newState ajoute un état à la machine si celui-ci n'est pas déjà présent
	 * @return true si l'état a été ajouté car non présent , false sinon
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
	 * Définie l'état de départ sur l'état choisi si celui-ci existe
	 * @param newStartState
	 * @return true si l'état existe et peut etre défini comme état de départ, false sinon
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
	 * 
	 * @param newAcceptState
	 * @return true si l'état est accepté et false sinon
	 */
	public boolean setAcceptState(String newAcceptState) {
		if (stateSpace.contains(newAcceptState) && !rejectState.equals(newAcceptState)) {
			acceptState = newAcceptState;
			return true;
		} else {
			return false;
		}

	}

	public boolean setRejectState(String newRejectState) {
		if (stateSpace.contains(newRejectState) && !acceptState.equals(newRejectState)) {
			rejectState = newRejectState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Ajoute une transition entre deux états qui permet de crypter la lettre
	 * @param rState 
	 * @param rSymbol 
	 * @param wState
	 * @param wSymbol
	 * @param mDirection
	 * @return true si la transition a bien été crée , false sinon
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
