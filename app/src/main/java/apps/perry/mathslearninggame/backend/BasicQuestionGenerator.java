package apps.perry.mathslearninggame.backend;

/**
 * Implementation that generates basic math questions
 * @author perry
 *
 */
public class BasicQuestionGenerator extends QuestionGenerator {

	int a,b,c;

	public BasicQuestionGenerator() {
		super();
	}
	
	@Override
	protected void generateNumbers() {
		a = randomInteger(1,9);
		b = randomInteger(1,9);
		c = randomInteger(1,9);
	}
	
	@Override
	public String question() {
		generateNumbers();
		return String.format("%d%+d*%d", a, b, c); // 5+8*6
	}

	@Override
	public String answer() {
		String ans1 = String.format("%d", a+b*c); // 27 (CORRECT ANSWER)
		String ans2 = String.format("%d", a*b+c); // 46 (WRONG)
		String ans3 = String.format("%d", a+b+c); // 19 (WRONG)
		String ans4 = String.format("%d", a*b*c); // 240 (WRONG)
		
		
		return ans1 + SPLIT + ans2 + SPLIT + ans3 + SPLIT + ans4;
	}
}