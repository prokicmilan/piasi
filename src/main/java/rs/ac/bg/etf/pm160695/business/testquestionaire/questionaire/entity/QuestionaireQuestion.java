package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Question;

@Entity
@Table(name = "questionaire_question")
public class QuestionaireQuestion extends Question {

	private static final long serialVersionUID = 101249878129164902L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "questionaire_questionaire_question",
			joinColumns = @JoinColumn(name = "questionaire_question_id"),
			inverseJoinColumns = @JoinColumn(name = "questionaire_id"))
	private Questionaire questionaire;

	public Questionaire getQuestionaire() {
		return questionaire;
	}

	public void setQuestionaire(Questionaire questionaire) {
		this.questionaire = questionaire;
	}
	
}
