package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution;

@Entity
@Table(name = "test_solution")
public class TestSolution extends TQSolution {

	private static final long serialVersionUID = 4000894669038498044L;

	@ManyToOne(fetch = FetchType.EAGER)
	private Test test;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@JoinTable(
			name = "test_solution_test_answer",
			joinColumns = @JoinColumn(name = "test_solution_id"),
			inverseJoinColumns = @JoinColumn(name = "test_answer_id"))
	private Set<TestAnswer> answers;
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Set<TestAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswer> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answers, test);
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
		return Objects.equals(answers, other.answers) && Objects.equals(test, other.test);
	}
}
