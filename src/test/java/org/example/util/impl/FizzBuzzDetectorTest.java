package org.example.util.impl;

import static org.junit.Assert.assertEquals;

import org.example.util.FizzBuzz;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Unit test for org.example.util.impl.FizzBuzzDetector.
 *
 */
public class FizzBuzzDetectorTest {
	
	private FizzBuzz fizzBuzz;
	
	@Before
	public void init() {
		fizzBuzz = new FizzBuzzDetector();
	}
	
	@Test
	public void checkCoincidencesCounter() {
		String input = 
				"Mary had a little lamb\r\n"
    			+ "Little lamb, little lamb\r\n"
    			+ "Mary had a little lamb";
		int expectedCoincidencesCounter = 6;
		
		assertEquals(expectedCoincidencesCounter, fizzBuzz.getOverlappings(input).getCoincidencesCounter());
	}
	
	@Test
	public void checkOutputStringOneRow() {
		String input = 
				"Mary had a little lamb Little lamb, little lamb Mary had a little lamb It's fleece was white as snow";
		String expectedOutput = 
				"Mary had Fizz little Buzz Fizz lamb, little Fizz Buzz had Fizz little lamb FizzBuzz fleece was Fizz as Buzz";
		assertEquals(expectedOutput, fizzBuzz.getOverlappings(input).getOutputString());
	}
	
	@Test
	public void checkOutputStringWithoutNotAlphanumericWords() {
		String input = 
				"Mary had a little lamb\r\n"
    			+ "Little lamb, little lamb\r\n"
    			+ "Mary had a little lamb";
		
		String expectedOutput = 
				"Mary had Fizz little Buzz\r\n"
    			+ "Fizz lamb, little Fizz\r\n"
    			+ "Buzz had Fizz little lamb";
		assertEquals(expectedOutput, fizzBuzz.getOverlappings(input).getOutputString());
	}
	
	@Test
	public void checkOutputStringWithNotAlphanumericWords() {
		String input = 
				"Mary - had - a - little - lamb\r\n"
    			+ "Little lamb, little lamb\r\n"
    			+ "Mary had - a little lamb";
		
		String expectedOutput = 
				"Mary - had - Fizz - little - Buzz\r\n"
    			+ "Fizz lamb, little Fizz\r\n"
    			+ "Buzz had - Fizz little lamb";
		assertEquals(expectedOutput, fizzBuzz.getOverlappings(input).getOutputString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkInputAsNull() {
		fizzBuzz.getOverlappings(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkEmptyInput() {
		fizzBuzz.getOverlappings(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkInputLengthLessThen7() {
		fizzBuzz.getOverlappings("Mary h");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkInputLengthMoreThen100() {
		String input = 
				"Mary had a little lamb\r\n"
    			+ "Little lamb, little lamb\r\n"
    			+ "Mary had a little lamb\r\n"
    			+ "It's fleece was white as snow\r\n"
    			+ "Mary had a little lamb\r\n"
    			+ "Little lamb, little lamb\r\n"
    			+ "Mary had a little lamb\r\n"
    			+ "It's fleece was white as snow\r\n"
    			+ "";
		fizzBuzz.getOverlappings(input);
	}

}
