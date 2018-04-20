package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/** QuestionGenerator - Surd implementation */
public class SurdQuestionGenerator extends QuestionGenerator {
    int a,b,c;

    public SurdQuestionGenerator() {
        super();
    }

    @Override
    protected void generateNumbers() {
        a = randomInteger(0,6);
        b = randomInteger(-6,6);
        c = randomInteger(-6,6);
    }

    @Override
    public String question() {
        generateNumbers();
        return String.format("(√%d%+d)(√%d%+d)", a, b, a, c); // e.g. (√3+4)(√3+5)
    }

    @Override
    public ArrayList<String> answers() {
        ArrayList<String> answers = new ArrayList<String>();
        String ans1 = String.format("%d√%d%+d", (b+c), a, a+(b*c)); // 9√3+23 (CORRECT ANSWER)
        String ans2 = String.format("%d√%d%+d", (b+c), a, a-(b*c)); // 9√3-17 (WRONG)
        String ans3 = String.format("%d√%d%+d", (b+c), a, -1*(a+(b*c))); // 9√3-23 (WRONG)
        String ans4 = String.format("%d√%d%+d", (b+c), a, -1*(a-(b*c))); // 9√3+17 (WRONG)
        answers.add(ans1);
        answers.add(ans2);
        answers.add(ans3);
        answers.add(ans4);

        return answers;
    }
}
