package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/**
 * QuestionGenerator - Basic math function implementation
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
		a = randomInteger(-9,9);
		b = randomInteger(-9,9);
		c = randomInteger(-9,9);
	}
	
	@Override
	public String question() {
		generateNumbers();
		return String.format("%d%+d*%d", a, b, c); // 5+8*6
	}

	@Override
	public ArrayList<String> answers() {
		ArrayList<String> answers = new ArrayList<String>();
		String ans1 = String.format("%d", a+b*c); // 27 (CORRECT ANSWER)
		String ans2 = String.format("%d", a*b+c); // 46 (WRONG)
		String ans3 = String.format("%d", a+b+c); // 19 (WRONG)
		String ans4 = String.format("%d", a*b*c); // 240 (WRONG)
		answers.add(ans1);
		answers.add(ans2);
		answers.add(ans3);
		answers.add(ans4);
		
		return answers;
	}
}