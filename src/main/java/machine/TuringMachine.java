package machine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * On créer la classe qui correspond à la machine d'Enigme
 * 
 * @author Mikrail
 *
 */
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
	 * 
	 * On créer une classe transition que l'on utilisera à l'intérieur de la classe de décodage
	 * 
	 * @author Mikrail
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
	 * Constructeur de la machine de turing
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
	 * 
	 * Cette fonction permet de lancer le décodage de l'entrée "input" 
	 * 
	 * @param input
	 * @param silentmode
	 * @return
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
	 * On ajoute un etat à décoder 
	 * 
	 * @param newState
	 * @return
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
	 * On change le premier etat
	 * 
	 * @param newStartState
	 * @return
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
	 * Permet de changer d'état si la transition est possible
	 * 
	 * @param newAcceptState
	 * @return
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
	 * 
	 * @param newRejectState
	 * @return
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
	 * 
	 * Permet de passer d'ajouter une transition entre les etats
	 * 
	 * @param rState
	 * @param rSymbol
	 * @param wState
	 * @param wSymbol
	 * @param mDirection
	 * @return
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
