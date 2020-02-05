package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;

@Entity
@Table(name = "test")
public class Test extends TestQuestionaire {

	private static final long serialVersionUID = -7785469119188575180L;

	@NotNull
	private Integer trajanje;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TestQuestion> testQuestions;
	
	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public Set<TestQuestion> getTestQuestions() {
		return testQuestions;
	}

	public void setTestQuestions(Set<TestQuestion> testQuestions) {
		this.testQuestions = testQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(testQuestions, trajanje);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Test)) {
			return false;
		}
		Test other = (Test) obj;
		return Objects.equals(testQuestions, other.testQuestions) && Objects.equals(trajanje, other.trajanje);
	}

}
