package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

public enum EntityRecordStatus {

	AKTIVAN(1, "aktivan"), STORNIRAN(-1, "storniran");

	private Integer value;
	private String label;

	private EntityRecordStatus(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static EntityRecordStatus parse(Integer value) {
		for (EntityRecordStatus recordStatus : EntityRecordStatus.values()) {
			if (recordStatus.getValue().equals(value)) {
				return recordStatus;
			}
		}
		return null;
	}
}
