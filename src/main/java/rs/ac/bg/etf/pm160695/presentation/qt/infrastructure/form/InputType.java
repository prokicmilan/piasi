package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InputType {

	NUMBER(1, "number", "label.inputType.number"), TEXT(2, "text", "label.inputType.text"),
	TEXTAREA(3, "text-area", "label.inputType.text-area"), RADIO(4, "radio", "label.inputType.radio"),
	CHECKBOX(5, "checkbox", "label.inputType.checkbox");

	private Integer value;
	private String label;
	private String displayKey;

	private InputType(Integer value, String label, String displayKey) {
		this.value = value;
		this.label = label;
		this.displayKey = displayKey;
	}

	@JsonValue
	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public String getDisplayKey() {
		return displayKey;
	}

	public static InputType parse(Integer value) {
		for (InputType answerType : InputType.values()) {
			if (answerType.getValue().equals(value)) {
				return answerType;
			}
		}

		return null;
	}

}
