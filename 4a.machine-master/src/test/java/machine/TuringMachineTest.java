package machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class TuringMachineTest {

    private int input;
    private int newAcceptState;
    private int newRejectState;

	@Test
	public void test() {
		//TuringMachine.main(new String[0]);
		assertTrue(true);
                
	}
public void TestRun() {
		//GIVEN
		int expected = -1;
		//WHEN
		int actual = this.input;
		//THEN
		assertEquals(expected, actual);
	}

	@Test
	public void setAcceptStateTest() {
		//GIVEN
		int expected = 1;
		//WHEN
		int actual = this.newAcceptState;
		//THEN
		assertEquals(expected, actual);
	}

	@Test
	public void serRejectState() {
		//GIVEN
		int expected = 0;
		//WHEN
		int actual = this.newRejectState;
		//THEN
		assertEquals(expected, actual);
}
        
    
}
