package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireQuestion;

public class QuestionaireQuestionFormField extends TQFormField {

	private QuestionaireQuestion questionaireQuestion;
	
	public QuestionaireQuestionFormField() {}
	
	public QuestionaireQuestionFormField(Integer index) {
		super(index);
	}

	public QuestionaireQuestionFormField(String value, Integer index, InputType inputType, String answers) {
		super(value, index, inputType, answers);
	}

	public QuestionaireQuestion getQuestionaireQuestion() {
		return questionaireQuestion;
	}

	public void setQuestionaireQuestion(QuestionaireQuestion questionaireQuestion) {
		this.questionaireQuestion = questionaireQuestion;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof QuestionaireQuestionFormField)) {
			return false;
		}
		return true;
	}

}
