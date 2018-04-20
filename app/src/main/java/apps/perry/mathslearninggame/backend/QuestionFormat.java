package apps.perry.mathslearninggame.backend;

/**
 * A QuestionFormat is a container representing a particular type of question format, such as
 * multiple choice. Any QuestionGenerator can be used to generate a specific class of questions
 * and answers (e.g. polynomial factorisation).
 * @author perry
 *
 */
public abstract class QuestionFormat {
	QuestionGenerator qg;
	
	public QuestionFormat(QuestionType qt) {
		switch (qt) {
			case POLYNOMIAL: qg = new PolynomialQuestionGenerator(); break;
			case BASIC:      qg = new BasicQuestionGenerator(); break;
			case SURD:       qg = new SurdQuestionGenerator(); break;
			default: System.out.println("Error - invalid question type");
		}
	}
	
	public abstract Question generateQuestion();
}