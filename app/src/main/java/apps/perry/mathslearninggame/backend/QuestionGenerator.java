package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * A QuestionGenerator randomly generates a question and its corresponding answers for use in
 * a QuestionFormat using the java.util.Random class. Subclasses of QuestionGenerator specialise
 * on a certain type of questions (e.g. polynomial factorisation).
 * @author perry
 *
 */
public abstract class QuestionGenerator {
	
	/** Random number generator */
	protected Random r;
	
	/** Delimiter used in the generated answers to split them up later */
	protected final String SPLIT = "<SPLIT>";
	
	public QuestionGenerator() {
		r = new Random();
		generateNumbers();
	}
	
	protected abstract void generateNumbers();
	
	/**
	 * Generate the question
	 * @return The resulting question
	 */
	public abstract String question();
	
	/**
	 * Generate the answers.
	 * Note: results are undefined if question() has not been called yet!
	 * @return The resulting answer
	 */
	public abstract ArrayList<String> answers();
	
	/**
	 * Generate a random number in a range between a minimum and maximum value
	 * @param min Minimum value 
	 * @param max Maximum value
	 * @return A random number
	 */
	protected int randomInteger(int min, int max) {
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}