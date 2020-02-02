package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.util.List;

public class TQFormField extends FormField {

	private final List<InputType> inputTypeList = List.of(InputType.values());

	private InputType inputType;

	private String answers;

	public TQFormField(Integer index) {
		super(index);
	}

	public TQFormField(String value, Integer index) {
		super(value, index);
	}

	public TQFormField(String value, Integer index, InputType inputType, String answers) {
		super(value, index);
		this.inputType = inputType;
		this.answers = answers;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public List<InputType> getInputTypeList() {
		return inputTypeList;
	}
}
