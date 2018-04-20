package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/**
 * A QuestionManager manages a list of questions
 * @author perry
 *
 */
public class QuestionManager {

    // List of question formatters
	private ArrayList<QuestionFormat> questions;

	// Current question index
	int currQuestionIndex = -1;

	// Singleton instance of this QuestionManager
	private static QuestionManager qmInstance;

	private QuestionManager() {
        // TODO put this information in an external file
        questions = new ArrayList<QuestionFormat>();
        questions.add(new MCQuestionFormat(QuestionType.POLYNOMIAL));
        questions.add(new MCQuestionFormat(QuestionType.BASIC));
        questions.add(new MCQuestionFormat(QuestionType.SURD));
        questions.add(new FIIQuestionFormat(QuestionType.POLYNOMIAL));
        questions.add(new FIIQuestionFormat(QuestionType.BASIC));
        questions.add(new FIIQuestionFormat(QuestionType.SURD));
    }

    /**
     * Get the singleton instance of this QuestionManager
     * @return
     */
    public static QuestionManager getInstance() {
        if (qmInstance == null) {
            qmInstance = new QuestionManager();
        }
        return qmInstance;
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
