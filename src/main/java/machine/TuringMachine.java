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

		boolean isConflicting(String state, char symbol) { //fonction qui permet de savoir si l'etat et le symbol en parametres sont egaux à l'etat et au symbol actuel
			if (state.equals(readState) && symbol == readSymbol) {
				return true;
			} else {
				return false;
			}
		}
	}

	public TuringMachine() { //Constructeur
		stateSpace = new HashSet<String>();
		transitionSpace = new HashSet<Transition>();
		startState = new String("");
		acceptState = new String("");
		rejectState = new String("");
		tape = new String("");
		currentState = new String("");
		currentSymbol = 0;
	}

	public boolean run(String input, boolean silentmode) { //fonction de lancement de la machine
		currentState = startState;
		tape = input;

		while (!currentState.equals(acceptState) && !currentState.equals(rejectState)) { //Si l'etat actuel n'est ni le dernier ni l'etat interdit
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

	public boolean addState(String newState) { //fonction qui ajoute l'etat "newState" dans stateSpace, mais seulement si cet etat n'est pas dejà present, sinon il return false
		if (stateSpace.contains(newState)) {
			return false;
		} else {
			stateSpace.add(newState);
			return true;
		}
	}

	public boolean setStartState(String newStartState) { //fonction qui permet de definir l'etat de debut, si il est present dans stateSpace, sinon il return false
		if (stateSpace.contains(newStartState)) {
			startState = newStartState;
			return true;
		} else {
			return false;
		}
	}

	public boolean setAcceptState(String newAcceptState) { //fonction qui permet de definir l'etat de fin, si il est present dans stateSpace et si il est different de l'etat interdit
		if (stateSpace.contains(newAcceptState) && !rejectState.equals(newAcceptState)) {
			acceptState = newAcceptState;
			return true;
		} else {
			return false;
		}

	}

	public boolean setRejectState(String newRejectState) { //permet de definir l'etat interdit
		if (stateSpace.contains(newRejectState) && !acceptState.equals(newRejectState)) {
			rejectState = newRejectState;
			return true;
		} else {
			return false;
		}
	}

	public boolean addTransition(String rState, char rSymbol, String wState, char wSymbol, boolean mDirection) { //ajout d'une transition dans le hashSet "transitionSpace"
		if (!stateSpace.contains(rState) || !stateSpace.contains(wState)) { //Si la liste des etats ne continent pas d'etat de lecture ni d'ecriture, cette transition ne peut pas etre ajoutée
			return false;
		}

		boolean conflict = false;
		Iterator<Transition> TransitionsIterator = transitionSpace.iterator();
		while (TransitionsIterator.hasNext() && conflict == false) { //Si l'iterateur possede encore des elements aprés, et si l'element d'apres ne crée pas de conflits
			Transition nextTransition = TransitionsIterator.next();
			if (nextTransition.isConflicting(rState, rSymbol)) { 
				conflict = true;
			}

		}
		if (conflict == true) {
			return false;
		} else { //On ajoute la transition
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
