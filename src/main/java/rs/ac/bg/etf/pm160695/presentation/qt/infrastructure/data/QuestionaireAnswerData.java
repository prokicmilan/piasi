package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.data;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireQuestion;

public class QuestionaireAnswerData extends TQAnswerData {

	private QuestionaireQuestion question;

	public QuestionaireQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuestionaireQuestion question) {
		this.question = question;
	}
	
}
