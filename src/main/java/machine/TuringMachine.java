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

	class Transition {
		String readState;
		char readSymbol;
		String writeState;
		char writeSymbol;
		boolean moveDirection; // true is right, false is left
                
                /**
                 * Permet de tester un conflit 
                 * @param state String -> etat à tester
                 * @param symbol Char -> symbole à tester
                 * @return Boolean -> s'il y a une conflit
                 */
		boolean isConflicting(String state, char symbol) {
			if (state.equals(readState) && symbol == readSymbol) {
				return true;
			} else {
				return false;
			}
		}
	}

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
         * Permet de lancer la TuringMachine
         * @param input String -> correspond au message à encoder
         * @param silentmode Boolean -> permet d'afficher plus d'information si l'on souhaite
         * @return Boolean -> permet d'indiquer si ça a marcher
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
         * Permet d'ajouter un etat à la classe TuringMachine
         * @param newState String -> etat à ajouter
         * @return Boolean -> indique si l'etat n'existe pas déjà
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
         * Permet d'initailiser l'etat début de la TuringMachine
         * @param newStartState String -> etat de start
         * @return Boolean -> indique si l'état existe
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
         * Permet de mettre à jour l'etat accept
         * @param newAcceptState String -> de mettre à jour l'etat accept
         * @return Boolean -> indique si l'état existe et n'est pas refuser 
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
         * Permet de mettre à jour l'etat reject
         * @param newRejectState objet String -> de mettre à jour l'etat rejeter
         * @return Boolean -> indique si l'état existe et n'est pas refuser 
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
         * Permet d'ajouter une transition
         * @param rState String -> etat à lire
         * @param rSymbol char -> symbole à lire
         * @param wState String -> etat à écrire
         * @param wSymbol  char -> symbole à écrire
         * @param mDirection Boolean -> indique la direction de la transition, true is right, false is left
         * @return Boolean -> indique s'il n'y a pas de conflit
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
