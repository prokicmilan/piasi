package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.util.Objects;

public class FormField {

	private String value;

	private Integer index;

	public FormField(Integer index) {
		this.index = index;
	}

	public FormField(String value, Integer index) {
		this.value = value;
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, value);
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
		return Objects.equals(index, other.index) && Objects.equals(value, other.value);
	}
}
