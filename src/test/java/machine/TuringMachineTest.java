/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sevrin
 */
public class TuringMachineTest {
    TuringMachine turing = MachinesLibrary.buildEqualBinaryWords();
    Boolean result;
    
    public TuringMachineTest() {
    }

    /**
     * Test of run method, of class TuringMachine.
     */
    @Test
    public void test_Run_False() {
        Boolean expected = false;
        String donnee ="qa";
        result = turing.run(donnee, true);
        assertEquals(expected, result);
    }
    
    /**
     * Test of run method, of class TuringMachine.
     */
    @Test
    public void test_Run_True() {
        Boolean expected = true;
        String donnee ="010000110101#010000110101";
        result = turing.run(donnee, true);
        assertEquals(expected, result);
    }

    /**
     * Test of setAcceptState method, of class TuringMachine.
     */
    @Test
    public void test_SetAcceptState_True() {
        Boolean expected = true;
        String donnee ="qa";
        result = turing.setAcceptState(donnee);
        assertEquals(expected, result);
    }

    /**
     * Test of setRejectState method, of class TuringMachine.
     */
    @Test
    public void test_SetRejectState_True() {
        Boolean expected = true;
        String donnee ="qr";
        result = turing.setRejectState(donnee);
        assertEquals(expected, result);
    }

    /**
     * Test of addTransition method, of class TuringMachine.
     */
    @Test
    public void test_AddTransition_true() {
        Boolean expected = true;
        
        turing.addState("q1");
        turing.addState("q2");
        turing.addState("q3");
        turing.addState("qa");
        turing.addState("qr");
        
        turing.setStartState("q1");
        turing.setAcceptState("qa");
        turing.setRejectState("qr");
        
        result = turing.addTransition("q1", '1', "q3", 'x', true);
        assertEquals(expected, result);
    }
    
}
