package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class MachineAppTest {

	@Test
	public void testMain() {
		MachineApp.main(new String[0]);
		assertTrue(true);
	}

	@Test
	public void testRunWithGoodProgShouldReturnTrue()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		boolean done = turingMachine1.run("010000110101#010000110101", false);
		assertTrue(done);
	}

	@Test
	public void testRunWithBadProgShouldReturnFalse()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		boolean done = turingMachine1.run("010000110101#010000110100", false);
		assertFalse(done);
	}

	@Test
	public void testAcceptStateWithKnownStateShouldReturnTrue()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String state = "q1";
		boolean current = turingMachine1.setAcceptState(state);
		assertTrue(current);
	}

	@Test
	public void testAcceptStateWithKnownStateTwiceShouldReturnTrue()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String state = "q1";
		turingMachine1.setAcceptState(state);
		boolean current = turingMachine1.setAcceptState(state);
		assertTrue(current);
	}

	@Test
	public void testAcceptStateWithUnknownStateShouldReturnFalse()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String state = "..";
		boolean current = turingMachine1.setAcceptState(state);
		assertFalse(current);
	}

	@Test
	public void testRejectStateWithKnownStateShouldReturnTrue()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String state = "q1";
		boolean current = turingMachine1.setRejectState(state);
		assertTrue(current);
	}

	@Test
	public void testRejectStateWithKnownStateTwiceShouldReturnTrue()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String state = "q1";
		turingMachine1.setRejectState(state);
		boolean current = turingMachine1.setRejectState(state);
		assertTrue(current);
	}

	@Test
	public void testRejectStateWithUnknownStateShouldReturnFalse()
	{
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String state = "..";
		boolean current = turingMachine1.setRejectState(state);
		assertFalse(current);
	}
}
