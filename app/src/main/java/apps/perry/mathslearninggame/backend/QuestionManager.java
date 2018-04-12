package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/**
 * A QuestionManager manages a list of questions
 * @author perry
 *
 */
public class QuestionManager {

	private ArrayList<QuestionFormat> questions;
	int currQuestionIndex = -1;
	
	public QuestionManager() {
		questions = new ArrayList<QuestionFormat>();
    	questions.add(new MCQuestionFormat(QuestionType.POLYNOMIAL));
    	questions.add(new MCQuestionFormat(QuestionType.BASIC));
    	questions.add(new FIIQuestionFormat(QuestionType.POLYNOMIAL));
    	questions.add(new FIIQuestionFormat(QuestionType.BASIC));
	}
	
	/**
	 * Get a Question from this QuestionManager.
	 * @return A Question
	 */
	public Question giveMeAQuestion() {
		currQuestionIndex = (currQuestionIndex + 1) % questions.size();
		return questions.get(currQuestionIndex).generateQuestion();
	}
}
