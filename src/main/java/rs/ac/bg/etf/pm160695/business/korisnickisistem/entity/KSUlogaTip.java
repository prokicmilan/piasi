package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

public enum KSUlogaTip {

	OSNOVNA(0, "osnovna"), SISTEMSKA(1, "sistemska"), FUNKCIONALNA(2, "funkcionalna");

	private Integer value;
	private String label;

	private KSUlogaTip(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static KSUlogaTip parse(Integer value) {
		for (KSUlogaTip ut : KSUlogaTip.values()) {
			if (ut.getValue().equals(value)) {
				return ut;
			}
		}

		return null;
	}

}
