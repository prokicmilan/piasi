package rs.ac.bg.etf.pm160695.presentation.qt.test;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestAnswer;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

@Named
@ViewScoped
public class TestSolutionDetailsBacking extends BaseBackingBean {

	private static final long serialVersionUID = -6253101175894827822L;
	
	private TestSolution solution;
	
	public String rowStyle(TestAnswer answer) {
		return answer.getQuestion().getCorrectAnswer().equalsIgnoreCase(answer.getAnswer()) ? "correct" : "incorrect";
	}
	
	public TestSolution getSolution() {
		return solution;
	}

	public void setSolution(TestSolution solution) {
		this.solution = solution;
	}
	
}
