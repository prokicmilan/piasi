package rs.ac.bg.etf.pm160695.business.quizquestionaire.entity;

import java.io.Serializable;

public class Test implements Serializable {

	private static final long serialVersionUID = -7880129203912936747L;

	private String name;
	private Object value;
	private boolean required;
	
	public Test(String name, boolean required) {
		this.name = name;
		this.required = required;
	}

	public Test(String name, Object value, boolean required) {
		this.name = name;
		this.value = value;
		this.required = required;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}
