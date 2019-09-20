package comb.gui;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import comb.gui.CombinationsWithGUI;

public class CombinationsWithGUITest {

	@Test
	public void testSolutionValidInput()
	{
		String input0 = "326";
		String input1 = "A ash3Be2 C6D";
		String input2 = "F1E2R3 1";
		String[] validTestStrings = {input0, input1, input2};
		
		String sol0 = "632,623,362,326,263,236";
		String sol1 = "632,623,362,326,263,236";
		String sol2 = "3211,3121,3112,2311,2131,2113,1321,1312,1231,1213,1132,1123";
		String[] validSolutionStrings = {sol0, sol1, sol2};
		
		// assert if returns valid string with valid input
		for (int i = 0; i < validTestStrings.length; i++)
		{
			assertThat(CombinationsWithGUI.computeCombinations(validTestStrings[i]), instanceOf(String.class));
			assertEquals(validSolutionStrings[i], CombinationsWithGUI.computeCombinations(validTestStrings[i]));
		} // for
	} // testSolutionValidInput()

	@Test(expected = NumberFormatException.class)
	public void testSolutionInvalidInput()
	{
		CombinationsWithGUI.computeCombinations("invalid string");
	} // testSolutionInalidInput()

} // class CombinationsWithGUITest
