package rs.ac.bg.etf.pm160695.business.quizquestionaire.entity;

public enum InputType {

	NUMBER(1, "number"),
	TEXT(2, "text"),
	TEXTAREA(3, "text-area"),
	RADIO(4, "radio"),
	CHECKBOX(5, "checkbox");
	
	private Integer value;
	private String label;
	
	private InputType(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
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
