package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class MachineAppTest {

	@Test
	/**
	 * Test de runMachine 
	 */
	public void testrunMachineTrue() {
		String expected = "The input was accepted.";
		String result = "";
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "010000110101#010000110101";
		result = MachineApp.runMachine(turingMachine1, donnee,false);
		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test de runMachine 
	 */
	public void testrunMachineFalse() {
		String expected = "The input was rejected.";
		String result = "";
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "d#010000110101";
		result = MachineApp.runMachine(turingMachine1, donnee,false);
		assertEquals(expected, result);
	}

}
