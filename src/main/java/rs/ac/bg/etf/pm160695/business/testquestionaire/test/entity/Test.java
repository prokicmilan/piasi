package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;

@Entity
@Table(name = "test")
public class Test extends TestQuestionaire {

	private static final long serialVersionUID = -7785469119188575180L;

	@NotNull
	private Integer trajanje;

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((trajanje == null) ? 0 : trajanje.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (trajanje == null) {
			if (other.trajanje != null)
				return false;
		} else if (!trajanje.equals(other.trajanje))
			return false;
		return true;
	}

}
