package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class MachineAppTest {
	
	TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
	String prog = "010000110101#010000110101";
	String prog2 = "d#010000110101";
	boolean sillentMode = false;

	@Test
	public void test() {
		MachineApp.main(new String[0]);
		assertTrue(true);
	}
	
	@Test
	public void RunTrueTest() {
		boolean expected = true;
		boolean result;
		result = turingMachine1.run(prog, false);
		assertEquals(expected, result);
	}
	
	@Test
	public void RunFalseTest() {
		boolean expected = false;
		boolean result;
		result = turingMachine1.run(prog2, false);
		assertEquals(expected, result);
	}
	
	
	
	

}
