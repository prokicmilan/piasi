package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TQFormField extends FormField {

	@JsonIgnore
	private final List<InputType> inputTypeList = List.of(InputType.values());
	
	private InputType inputType;

	private String answers;
	
	public TQFormField() {}

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

	public void setInputType(int inputType) {
		this.inputType = InputType.parse(inputType);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answers, inputType, inputTypeList);
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
		if (!(obj instanceof TQFormField)) {
			return false;
		}
		TQFormField other = (TQFormField) obj;
		return Objects.equals(answers, other.answers) && inputType == other.inputType
				&& Objects.equals(inputTypeList, other.inputTypeList);
	}
}
