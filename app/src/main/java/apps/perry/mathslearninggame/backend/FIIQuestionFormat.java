package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/**
 * QuestionFormat - 'fill-it-in' implementation
 * @author perry
 *
 */
public class FIIQuestionFormat extends QuestionFormat {

	public FIIQuestionFormat(QuestionType qt) { super(qt); }

	@Override
	public Question generateQuestion() {
		String question = qg.question();
		ArrayList<String> answer = new ArrayList<String>();
		answer.add(qg.answers().get(0));
		return new Question(question, answer, QuestionFormatType.FILL_IT_IN);
	}
	
}