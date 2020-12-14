package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class TuringMachineTest {
    
    @Test
    public void testrunTrue() {
		Boolean expected = true;
		Boolean result;
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "010000110101#010000110101";
		result = turingMachine1.run( donnee, false);
		assertEquals(expected, result);
    }
    

    @Test
    public void testrunFalse() {
		Boolean expected = false;
		Boolean result;
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "d#010000110101";
		result = turingMachine1.run( donnee, false);
		assertEquals(expected, result);
    }
    
    @Test
    public void testsetAcceptStateTrue() {
		Boolean expected = true;
		Boolean result;
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "qa";
		result = turingMachine1.setAcceptState( donnee);
		assertEquals(expected, result);
    }
    
    @Test
    public void testsetAcceptStateFalse() {
		Boolean expected = false;
		Boolean result;
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "qqsfqsb";
		result = turingMachine1.setAcceptState( donnee);
		assertEquals(expected, result);
    }
    
    @Test
    public void testsetRejectStateTrue() {
		Boolean expected = true;
		Boolean result;
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "qr";
		result = turingMachine1.setRejectState( donnee);
		assertEquals(expected, result);
    }
    
    @Test
    public void testsetRejectStateFalse() {
		Boolean expected = false;
		Boolean result;
		TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
		String donnee = "qqsfqsb";
		result = turingMachine1.setRejectState( donnee);
		assertEquals(expected, result);
    }

    @Test
    public void testaddtransitionConflictFalse(){
        Boolean expected = false;
        Boolean result;
        TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
        result=turingMachine1.addTransition("q1", '1', "q3", 'x', true);
        assertEquals(expected, result);
    }

    @Test
    public void testaddtransitionFalse(){
        Boolean expected = false;
        Boolean result;
        TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords(); 
        result=turingMachine1.addTransition("q1", '#', "q3", '#', true);
        assertEquals(expected, result);
    }

    @Test
    public void testaddtransitionTrue(){
        Boolean expected = true;
        Boolean result;
        TuringMachine turingMachine1 = MachinesLibrary.buildEqualBinaryWords();
        result=turingMachine1.addTransition("qa", '1', "q1", 'x', true);
        assertEquals(expected, result);
    }

}
