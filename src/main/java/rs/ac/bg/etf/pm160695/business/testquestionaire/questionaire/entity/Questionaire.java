package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;

@Entity
@Table(name = "questionaire")
public class Questionaire extends TestQuestionaire {

	private static final long serialVersionUID = 4057844454667571690L;

	private Boolean anonymous;

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((anonymous == null) ? 0 : anonymous.hashCode());
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
		Questionaire other = (Questionaire) obj;
		if (anonymous == null) {
			if (other.anonymous != null)
				return false;
		} else if (!anonymous.equals(other.anonymous))
			return false;
		return true;
	}

}
