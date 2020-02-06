package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Answer;

@Entity
@Table(name = "test_answer")
public class TestAnswer extends Answer {

	private static final long serialVersionUID = -7397679611864498061L;
	
	@OneToOne
	private TestQuestion question;

	public TestQuestion getQuestion() {
		return question;
	}

	public void setQuestion(TestQuestion question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((question == null) ? 0 : question.hashCode());
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
		TestAnswer other = (TestAnswer) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
}
