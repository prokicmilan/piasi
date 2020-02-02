package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

public class QuestionaireQuestionFormField extends TQFormField {

	public QuestionaireQuestionFormField(Integer index) {
		super(index);
	}

	public QuestionaireQuestionFormField(String value, Integer index, InputType inputType, String answers) {
		super(value, index, inputType, answers);
	}

}
