package apps.perry.mathslearninggame.backend;

import java.util.ArrayList;

/**
 * A Question contains a question and the possible answers to the question.
 * @author perry
 *
 */
public class Question {
	private String q;
	private ArrayList<String> a;
	private QuestionFormatType qft;
	
	public Question(String q, ArrayList<String> a, QuestionFormatType qft) {
		this.q = q;
		this.a = a;
		this.qft = qft;
	}
	
	public String question() {
		return this.q;
	}
	
	public ArrayList<String> answers() {
		return this.a;
	}
	
	public QuestionFormatType type() {
		return this.qft;
	}
}
