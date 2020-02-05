package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Question;

@Entity
@Table(name = "questionaire_question")
public class QuestionaireQuestion extends Question {

	private static final long serialVersionUID = 101249878129164902L;
	
	@ManyToOne
	@JoinColumn(name = "questionaire_id")
	private Questionaire questionaire;

	public Questionaire getQuestionaire() {
		return questionaire;
	}

	public void setQuestionaire(Questionaire questionaire) {
		this.questionaire = questionaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(questionaire);
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
		if (!(obj instanceof QuestionaireQuestion)) {
			return false;
		}
		QuestionaireQuestion other = (QuestionaireQuestion) obj;
		return Objects.equals(questionaire, other.questionaire);
	}
	
}
