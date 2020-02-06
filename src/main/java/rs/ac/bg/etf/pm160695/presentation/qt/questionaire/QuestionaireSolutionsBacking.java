package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQSolutionsBacking;

@Named
@ViewScoped
public class QuestionaireSolutionsBacking extends TQSolutionsBacking {

	private static final long serialVersionUID = -2896192307556841732L;
	
	@Inject
	private QuestionaireSolutionDao questionaireSolutionDao;
	
	public void initialize() {
		solutions = questionaireSolutionDao.getQuestionaireSolutionsForQuestionaire((Questionaire) tq);
	}

}
