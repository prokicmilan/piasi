package rs.ac.bg.etf.pm160695.business.quizquestionaire.entity;

import java.util.List;

public class TestQuestionFormField extends FormField {

	private final List<InputType> inputTypeList = List.of(InputType.values());
	
	private InputType inputType;
	
	public TestQuestionFormField(String name) {
		super(name);
	}

	public TestQuestionFormField(String name, String value) {
		super(name, value);
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public List<InputType> getInputTypeList() {
		return inputTypeList;
	}

	@Override
	public String toString() {
		return "name = " + getName() + " value = " + getValue() + " inputType = " + inputType;
	}

}
