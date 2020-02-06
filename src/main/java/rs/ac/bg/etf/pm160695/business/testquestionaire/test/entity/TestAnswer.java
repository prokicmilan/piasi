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
	
}
