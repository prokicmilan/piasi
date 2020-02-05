package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution;

@Entity
@Table(name = "questionaire_solution")
public class QuestionaireSolution extends TQSolution {

	private static final long serialVersionUID = 8643836432009777723L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
		if (!(obj instanceof QuestionaireSolution)) {
			return false;
		}
		QuestionaireSolution other = (QuestionaireSolution) obj;
		return Objects.equals(questionaire, other.questionaire);
	}
}
