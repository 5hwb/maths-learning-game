package apps.perry.mathslearninggame.backend;

/**
 * Question - Multiple choice implementation
 * @author perry
 *
 */
public class MCQuestion extends Question {

	public MCQuestion(QuestionType qt) { super(qt); }

	@Override
	public void generateQuestion() {
		System.out.printf("=======MULTIPLE CHOICE========\n"
				        + "Question: %s\n"
						+ "Possible answers: \n", qg.question());
		
		StringBuilder sbAnswers = new StringBuilder();
		String[] answers = qg.answer().split(qg.SPLIT);
		for (int i = 0; i < answers.length; i++) {
			sbAnswers.append(String.format("(%d) %s\n", i+1, answers[i]));
		}
		System.out.printf("%s", sbAnswers.toString());
	}
	
}