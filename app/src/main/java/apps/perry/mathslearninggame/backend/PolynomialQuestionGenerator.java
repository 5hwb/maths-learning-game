package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/**
 * QuestionGenerator - Polynomial implementation
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
		a = randomInteger(-5,5);
		b = randomInteger(-5,5);
		c = randomInteger(-5,5);
		d = randomInteger(-5,5);
	}
	
	@Override
	public String question() {
		generateNumbers();
		return String.format("(%dx%+d)(%dx%+d)", a, b, c, d); // (1x+1)(2x+3)
	}

	@Override
	public ArrayList<String> answers() {
		ArrayList<String> answers = new ArrayList<String>();
		String ans1 = String.format("%dx²%+dx%+d", a*c, a*d + b*c, b*d); // 2x²+5x+3 (CORRECT ANSWER)
		String ans2 = String.format("%dx²%+dx%+d", a*c, -1*(a*d + b*c), b*d); // 2x²-5x+3 (WRONG)
		String ans3 = String.format("%dx²%+dx%+d", a*c, -1*(a*d) + b*c, b*d); // 2x²-1x+3 (WRONG)
		String ans4 = String.format("%dx%+d%+dx%+d", a, b, c, d); // 1x+1+2x+3 (WRONG)
		answers.add(ans1);
		answers.add(ans2);
		answers.add(ans3);
		answers.add(ans4);
		return answers;
	}
	
}