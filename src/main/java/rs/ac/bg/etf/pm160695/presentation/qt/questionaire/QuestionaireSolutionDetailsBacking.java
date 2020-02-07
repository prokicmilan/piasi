package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireSolution;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

@Named
@ViewScoped
public class QuestionaireSolutionDetailsBacking extends BaseBackingBean {

	private static final long serialVersionUID = -7221569019302949935L;
	
	private QuestionaireSolution solution;

	public QuestionaireSolution getSolution() {
		return solution;
	}

	public void setSolution(QuestionaireSolution solution) {
		this.solution = solution;
	}

}
