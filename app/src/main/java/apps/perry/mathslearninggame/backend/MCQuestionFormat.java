package apps.perry.mathslearninggame.backend;

/**
 * QuestionFormat - Multiple choice implementation
 * @author perry
 *
 */
public class MCQuestionFormat extends QuestionFormat {

	public MCQuestionFormat(QuestionType qt) { super(qt); }

	@Override
	public Question generateQuestion() {
		return new Question(qg.question(), qg.answers(), QuestionFormatType.MULTIPLE_CHOICE);
	}
	
}