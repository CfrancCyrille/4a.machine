package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class TuringMachineTest {

    // machine de turing pour les tests
    TuringMachine turingMachine = MachinesLibrary.buildEqualBinaryWords();

    /**
     * test run
     */
    @Test
    public void testRun_valideData_true(){
        boolean attendu = true;
        boolean resultat;
        resultat = turingMachine.run("010000110101#010000110101", false);
        assertEquals(attendu, resultat);
    }

    /**
     * test run
     */
    @Test
    public void testRun_InvalidData_false(){
        boolean attendu = false;
        boolean resultat;
        resultat = turingMachine.run("010d0101#010000110101", false);
        assertEquals(attendu, resultat);
    }

    /**
     * test setAcceptState
     * Avec acceptSate != rejectState et stateSpace contient le nouveau acceptState
     */
    @Test
    public void testSetAcceptSate_acceptStateDifferentrejectStateEt_true(){
        boolean attendu = true;
        boolean resultat;
        resultat = turingMachine.setAcceptState("qa");
        assertEquals(attendu, resultat);
    }

    /**
     * test de setRejectState
     * Avec stateSpace.contains(newRejectState) && !acceptState.equals(newRejectState)
     */
    @Test
    public void testSetRejectState__true(){
        boolean attendu = true;
        boolean resultat;
        resultat = turingMachine.setRejectState("qr");
        assertEquals(attendu, resultat);
    }

}
