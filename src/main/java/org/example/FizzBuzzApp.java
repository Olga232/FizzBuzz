package org.example;

import org.example.util.FizzBuzz;
import org.example.util.impl.FizzBuzzDetector;

/**
 * 
 * FizzBuzz Application
 *
 */

public class FizzBuzzApp {
	
	private static final String DEFAULT_INPUT = 
			"Mary had a little lamb\n"
			+ "Little lamb, little lamb\n"
			+ "Mary had a little lamb\n"
			+ "It's fleece was white as snow";
	
    public static void main (String[] args) {
    	try{
    		FizzBuzz fizzBuzz = new FizzBuzzDetector();
    		System.out.println(fizzBuzz.getOverlappings(DEFAULT_INPUT));
    	} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
}
