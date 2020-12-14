package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class MachineAppTest {
        TuringMachine turing = MachinesLibrary.buildEqualBinaryWords();
        String prog="010000110101#010000110101";
        boolean sillentMode =false;
        
	@Test
	public void MahineApp_main_true() {
		MachineApp.main(new String[0]);
		assertTrue(true);
	}
        
        @Test
	public void MahineApp_runMachine_True() {   
            Boolean expected = true;
            Boolean actual;
            actual = turing.run(prog, false);
            assertEquals(expected, actual); 
        }
        
        @Test
	public void MahineApp_runMachine_False() { 
            Boolean expected = false;
            Boolean actual;
            prog="d#010000110101";
            actual = turing.run(prog, false);
            assertEquals(expected, actual); 
	}

}
