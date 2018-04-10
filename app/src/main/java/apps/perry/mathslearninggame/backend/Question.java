package apps.perry.mathslearninggame.backend;

/**
 * A Question is a container representing a particular type of question format, such as
 * multiple choice. Any QuestionGenerator can be used to generate the questions and answers.
 * @author perry
 *
 */
public abstract class Question {
	QuestionGenerator qg;
	
	public Question(QuestionType qt) {
		switch (qt) {
		case POLYNOMIAL: qg = new PolynomialQuestionGenerator(); break;
		case BASIC:      qg = new BasicQuestionGenerator(); break;
		default: System.out.println("Error - invalid question type");
		}
	}
	
	public abstract void generateQuestion();
}