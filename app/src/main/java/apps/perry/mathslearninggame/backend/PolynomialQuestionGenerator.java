package apps.perry.mathslearninggame.backend;

/**
 * Implementation that generates polynomial questions
 * @author perry
 *
 */
public class PolynomialQuestionGenerator extends QuestionGenerator {

	int a,b,c,d;
	
	public PolynomialQuestionGenerator() {
		super();
	}
	
	@Override
	protected void generateNumbers() {
		a = randomInteger(1,5);
		b = randomInteger(1,5);
		c = randomInteger(1,5);
		d = randomInteger(1,5);
	}
	
	@Override
	public String question() {
		generateNumbers();
		return String.format("(%dx%+d)(%dx%+d)", a, b, c, d); // (1x+1)(2x+3)
	}

	@Override
	public String answer() {
		String ans1 = String.format("%dx^2%+dx%+d", a*c, a*d + b*c, b*d); // 2x^2+5x+3 (CORRECT ANSWER)
		String ans2 = String.format("%dx^2%+dx%+d", a*c, -1*(a*d + b*c), b*d); // 2x^2-5x+3 (WRONG)
		String ans3 = String.format("%dx^2%+dx%+d", a*c, -1*(a*d) + b*c, b*d); // 2x^2-1x+3 (WRONG)
		String ans4 = String.format("%dx%+d%+dx%+d", a, b, c, d); // 1x+1+2x+3 (WRONG)
		
		return ans1 + SPLIT + ans2 + SPLIT + ans3 + SPLIT + ans4;
	}
	
}