package apps.perry.mathslearninggame.backend;

/**
 * Generate a complete question using a QuestionGenerator of your choice
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