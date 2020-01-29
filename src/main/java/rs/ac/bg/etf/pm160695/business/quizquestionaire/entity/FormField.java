package rs.ac.bg.etf.pm160695.business.quizquestionaire.entity;

public class FormField {

	private String name;
	
	private String value;
	
	public FormField(String name) {
		this.name = name;
	}
	
	public FormField(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FormField [name=" + name + ", value=" + value + "]";
	}	
}
