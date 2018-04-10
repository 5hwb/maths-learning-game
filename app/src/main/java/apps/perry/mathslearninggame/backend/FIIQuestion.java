package apps.perry.mathslearninggame.backend;

/**
 * Implementation of a 'fill-it-in' question
 * @author perry
 *
 */
public class FIIQuestion extends Question {

	public FIIQuestion(QuestionType qt) { super(qt); }

	@Override
	public void generateQuestion() {
		System.out.printf("=========FILL IT IN===========\n"
		        + "Question: %s\n"
				+ "Correct answer: %s\n", qg.question(), qg.answer().split(qg.SPLIT)[0]);
	}
	
}