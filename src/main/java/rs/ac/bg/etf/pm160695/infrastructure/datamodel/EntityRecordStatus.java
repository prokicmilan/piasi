package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

public enum EntityRecordStatus {

	AKTIVAN(1, "aktivan"), STORNIRAN(0, "storniran"), OBRISAN(-1, "obrisan skriptom");

	private Integer value;
	private String label;

	private EntityRecordStatus(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static EntityRecordStatus parse(Integer value) {
		for (EntityRecordStatus ers : EntityRecordStatus.values()) {
			if (ers.getValue().equals(value)) {
				return ers;
			}
		}

		return null;
	}

}
