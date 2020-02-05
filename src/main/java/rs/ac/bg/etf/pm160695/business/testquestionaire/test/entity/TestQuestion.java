package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Question;

@Entity
@Table(name = "test_question")
public class TestQuestion extends Question {

	private static final long serialVersionUID = -2920307462945852848L;
	
	@NotBlank
	@Column(name = "correct_answer")
	private String correctAnswer;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

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
		result = prime * result + Objects.hash(correctAnswer, test);
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
		if (!(obj instanceof TestQuestion)) {
			return false;
		}
		TestQuestion other = (TestQuestion) obj;
		return Objects.equals(correctAnswer, other.correctAnswer) && Objects.equals(test, other.test);
	}

}
