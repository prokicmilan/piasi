package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

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
@Table(name = "questionaire_solution")
public class QuestionaireSolution extends TQSolution {

	private static final long serialVersionUID = 8643836432009777723L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionaire_id")
	private Questionaire questionaire;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@JoinTable(
			name = "questionaire_solution_questionaire_answer",
			joinColumns = @JoinColumn(name = "questionaire_solution_id"),
			inverseJoinColumns = @JoinColumn(name = "questionaire_answer_id"))
	private Set<QuestionaireAnswer> answers;

	public Questionaire getQuestionaire() {
		return questionaire;
	}

	public void setQuestionaire(Questionaire questionaire) {
		this.questionaire = questionaire;
	}

	public Set<QuestionaireAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<QuestionaireAnswer> answers) {
		this.answers = answers;
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
