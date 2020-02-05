package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;

@Entity
@Table(name = "questionaire")
public class Questionaire extends TestQuestionaire {

	private static final long serialVersionUID = 4057844454667571690L;

	private Boolean anonymous;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "questionaire")
	private Set<QuestionaireQuestion> questionaireQuestions;

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Set<QuestionaireQuestion> getQuestionaireQuestions() {
		return questionaireQuestions;
	}

	public void setQuestionaireQuestions(Set<QuestionaireQuestion> questionaireQuestions) {
		this.questionaireQuestions = questionaireQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(anonymous, questionaireQuestions);
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
		if (!(obj instanceof Questionaire)) {
			return false;
		}
		Questionaire other = (Questionaire) obj;
		return Objects.equals(anonymous, other.anonymous)
				&& Objects.equals(questionaireQuestions, other.questionaireQuestions);
	}

}
