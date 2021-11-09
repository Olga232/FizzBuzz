package org.example.util.impl;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import org.example.model.OverlappedResult;
import org.example.util.FizzBuzz;

/**
 * 
 * Replaces every third word in the string to Fizz, 
 * and every fifth word in the string to Buzz
 *
 */

public class FizzBuzzDetector implements FizzBuzz {
	
	private static final String FIZZ = "Fizz";
	private static final String BUZZ = "Buzz";
	private static final String FIZZ_BUZZ = "FizzBuzz";
	
	private static final String ROW_DELIMETER_REGEX = "\\n";
	private static final String END_OF_WORD_REGEX = "[^a-zA-Z]";
	private static final String NOT_ALPHANUMERIC_REGEX = "\\W";
	
	private static final Logger LOG = Logger.getLogger(FizzBuzzDetector.class.getName());
	private static final String EMPTY_INPUT_LOG = "Empty input";
	private static final String INVALID_INPUT_LENGTH_LOG = "Invalid input length: %d";
	private static final String GET_INPUT_LOG = "Get input";
	private static final String OVERLAPPED_SUCCESSFULLY_LOG = "Input overlapped successfully";
	private static FileHandler fileHandler;
	
	static {
        setUpLoggerFileHandler();
    }
	
	/**
	 * 
	 * @param	input	input string to be replaced
	 * @return	org.example.model.OverlappedResult	an object that contains output string 
	 * with replaced words and number of coincidences 
	 * of Fizz, Buzz and FizzBuzz words within the output string
	 * @throws	java.lang.IllegalArgumentException	if the input string is null, empty, 
	 * input string length is less then 7, or more then 100
	 */
	@Override
	public OverlappedResult getOverlappings(String input) throws IllegalArgumentException {
		checkInputConstraints(input);
		
		LOG.info(GET_INPUT_LOG);
		int coincidencesCounter = 0;
		int totalWordCounter = 1;
		
		String[] rowsArray = input.split(ROW_DELIMETER_REGEX);
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < rowsArray.length; i++) {
			String[] wordsFromOneRow = rowsArray[i].trim().split(" ");
			
			for (int j = 0; j < wordsFromOneRow.length; j++) {
				if (!Pattern.matches(NOT_ALPHANUMERIC_REGEX, wordsFromOneRow[j])) {
					if (totalWordCounter%3 == 0 && totalWordCounter%5 == 0) {
						coincidencesCounter = 
								overlap(sb, wordsFromOneRow[j], FIZZ_BUZZ, coincidencesCounter);
					} else if (totalWordCounter%3 == 0) {
						coincidencesCounter = 
								overlap(sb, wordsFromOneRow[j], FIZZ, coincidencesCounter);
					} else if (totalWordCounter%5 == 0) {
						coincidencesCounter = 
								overlap(sb, wordsFromOneRow[j], BUZZ, coincidencesCounter);
					} else {
						sb.append(wordsFromOneRow[j]);
					}
					totalWordCounter++;
				} else {
					sb.append(wordsFromOneRow[j]);
				}
				if (j != wordsFromOneRow.length-1) {
					sb.append(" ");
				}
			}
			sb.append("\r\n");
		}
		LOG.info(OVERLAPPED_SUCCESSFULLY_LOG);
		
		return OverlappedResult.builder()
				.outputString(sb.toString().trim())
				.coincidencesCounter(coincidencesCounter)
				.build();
	}
	
	private static void setUpLoggerFileHandler() {
		try {
        	fileHandler = new FileHandler("FizzBuzzDetector.log", true);
        	fileHandler.setFormatter(new SimpleFormatter());
        	LOG.setUseParentHandlers(false);
			LOG.addHandler(fileHandler);
        } catch (IOException | SecurityException e) {
        	throw new RuntimeException(e.getMessage());
        }
	}
	
	private void checkInputConstraints(String input) throws IllegalArgumentException {
		if (input == null || input.isBlank()) {
			LOG.severe(EMPTY_INPUT_LOG);
			throw new IllegalArgumentException(EMPTY_INPUT_LOG);
		} else if (input.length()<7 || input.length()>100) {
			LOG.severe(String.format(INVALID_INPUT_LENGTH_LOG, input.length()));
			throw new IllegalArgumentException(String.format(INVALID_INPUT_LENGTH_LOG, input.length()));
		}
	}
	
	/**
	 * Replaces a word and increases coincidences counter.
	 * @param sb StringBuilder
	 * @param wordToUpdate the input word to be replaced
	 * @param newWord a new word to replace
	 * @param coincidencesCounter
	 * @return	int increased coincidences counter
	 */
	private int overlap(StringBuilder sb, String wordToUpdate, String newWord, int coincidencesCounter) {
		sb.append(newWord);
		if (Pattern.matches(END_OF_WORD_REGEX, wordToUpdate.substring(wordToUpdate.length()-1))) {
			sb.append(wordToUpdate.substring(wordToUpdate.length()-1));
		}
		return ++coincidencesCounter;
	}
	

}
