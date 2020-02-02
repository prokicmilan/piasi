package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

public class TestQuestionFormField extends TQFormField {

	private String correctAnswer;

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

}
