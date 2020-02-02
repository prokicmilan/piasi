package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

public class QuestionaireQuestionFormField extends TQFormField {

	public QuestionaireQuestionFormField(Integer index) {
		super(index);
	}

	public QuestionaireQuestionFormField(String value, Integer index, InputType inputType, String answers) {
		super(value, index, inputType, answers);
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
