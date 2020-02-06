package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.data;

import java.util.List;

import java.util.LinkedList;

public abstract class TQAnswerData {

	private String answer;
	
	private String[] answers;
	
	private List<String> options = new LinkedList<>();
	
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
	
}
