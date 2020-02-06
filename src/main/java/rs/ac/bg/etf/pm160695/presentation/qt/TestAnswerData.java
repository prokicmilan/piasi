package rs.ac.bg.etf.pm160695.presentation.qt;

import java.util.LinkedList;
import java.util.List;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestQuestion;

public class TestAnswerData {

	private String answer;
	
	private String[] answers;

	private List<String> options = new LinkedList<>();
	
	private TestQuestion question;
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public TestQuestion getQuestion() {
		return question;
	}

	public void setQuestion(TestQuestion question) {
		this.question = question;
	}
	
}
