package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution;

@Entity
@Table(name = "test_solution")
public class TestSolution extends TQSolution {

	private static final long serialVersionUID = 4000894669038498044L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "test_id")
	private Test test;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(test);
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
		if (!(obj instanceof TestSolution)) {
			return false;
		}
		TestSolution other = (TestSolution) obj;
		return Objects.equals(test, other.test);
	}
}
