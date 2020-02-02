package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

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
}
