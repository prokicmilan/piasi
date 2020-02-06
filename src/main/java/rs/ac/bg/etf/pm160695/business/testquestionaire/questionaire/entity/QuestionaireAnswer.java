package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Answer;

@Entity
@Table(name = "questionaire_answer")
public class QuestionaireAnswer extends Answer {

	private static final long serialVersionUID = -844458580916305093L;
	
	@OneToOne
	private QuestionaireQuestion question;

	public QuestionaireQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuestionaireQuestion question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(question);
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
		if (!(obj instanceof QuestionaireAnswer)) {
			return false;
		}
		QuestionaireAnswer other = (QuestionaireAnswer) obj;
		return Objects.equals(question, other.question);
	}
	
}
