package org.example.model;

/**
 * 
 * Model that contains string with replaced words 
 * and number of coincidences of Fizz, Buzz and FizzBuzz words within the output string
 *
 */

public class OverlappedResult {
	
	private String outputString;
	private int coincidencesCounter;
	
	public OverlappedResult(String outputString, int coincidencesCounter) {
		this.outputString = outputString;
		this.coincidencesCounter = coincidencesCounter;
	}

	public static OverlappedResultBuilder builder() {
		return new OverlappedResultBuilder();
	}
	
	public static class OverlappedResultBuilder {
		private String outputString;
		private int coincidencesCounter;
		
		public OverlappedResultBuilder outputString(String outputString) {
			this.outputString = outputString;
			return this;
		}
		
		public OverlappedResultBuilder coincidencesCounter(int coincidencesCounter) {
			this.coincidencesCounter = coincidencesCounter;
			return this;
		}
		
		public OverlappedResult build() {
			return new OverlappedResult(outputString, coincidencesCounter);
		}
	}
	
	public String getOutputString() {
		return outputString;
	}

	public int getCoincidencesCounter() {
		return coincidencesCounter;
	}

	@Override
	public String toString() {
		return "output string:\n" + outputString + "\n\n"
				+ "count: " + coincidencesCounter;
	}
	
	
}
