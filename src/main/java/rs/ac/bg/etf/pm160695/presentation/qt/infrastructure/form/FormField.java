package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form;

import java.util.Objects;

public class FormField {

	private String question;

	private Integer index;
	
	public FormField() {}

	public FormField(Integer index) {
		this.index = index;
	}

	public FormField(String question, Integer index) {
		this.question = question;
		this.index = index;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String value) {
		this.question = value;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, question);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FormField)) {
			return false;
		}
		FormField other = (FormField) obj;
		return Objects.equals(index, other.index) && Objects.equals(question, other.question);
	}
}
