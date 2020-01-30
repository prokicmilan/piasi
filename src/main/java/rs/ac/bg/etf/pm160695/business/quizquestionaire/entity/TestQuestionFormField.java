package rs.ac.bg.etf.pm160695.business.quizquestionaire.entity;

import java.util.List;

public class TestQuestionFormField extends FormField {

	private final List<InputType> inputTypeList = List.of(InputType.values());
	
	private InputType inputType;
	
	private String correctAnswer;
	
	private String answers;
	
	private int index;
	
	public TestQuestionFormField(String name) {
		super(name);
	}
	
	public TestQuestionFormField(String name, int index) {
		super(name);
		this.index = index;
	}
	
	public TestQuestionFormField(String name, String value, InputType inputType, String correctAnswer, String answers) {
		super(name, value);
		this.inputType = inputType;
		this.answers = answers;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<InputType> getInputTypeList() {
		return inputTypeList;
	}

}
