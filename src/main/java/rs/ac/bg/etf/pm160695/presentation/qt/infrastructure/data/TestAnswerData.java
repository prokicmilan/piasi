package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.data;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestQuestion;

public class TestAnswerData extends TQAnswerData {

	private TestQuestion question;
	
	public TestQuestion getQuestion() {
		return question;
	}

	public void setQuestion(TestQuestion question) {
		this.question = question;
	}
	
}
