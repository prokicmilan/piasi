package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form;

import java.util.Objects;

public class TestQuestionFormField extends TQFormField {

	private String correctAnswer;
	
	public TestQuestionFormField() {}

	public TestQuestionFormField(Integer index) {
		super(index);
	}

	public TestQuestionFormField(String value, Integer index, InputType inputType, String answers,
			String correctAnswer) {
		super(value, index, inputType, answers);
		this.correctAnswer = correctAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(correctAnswer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof TestQuestionFormField)) {
			return false;
		}
		TestQuestionFormField other = (TestQuestionFormField) obj;
		return Objects.equals(correctAnswer, other.correctAnswer);
	}

}
