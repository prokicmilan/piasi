package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing;

import java.util.List;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

public abstract class TQSolutionsBacking extends BaseBackingBean {

	private static final long serialVersionUID = 2216745261912065033L;
	
	protected TestQuestionaire tq;
	
	protected List<? extends TQSolution> solutions;
	
	protected TQSolution selected;
	
	public abstract String getDetaljiOutcome();

	public TestQuestionaire getTq() {
		return tq;
	}

	public void setTq(TestQuestionaire tq) {
		this.tq = tq;
	}

	public List<? extends TQSolution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<? extends TQSolution> solutions) {
		this.solutions = solutions;
	}

	public TQSolution getSelected() {
		return selected;
	}

	public void setSelected(TQSolution selected) {
		this.selected = selected;
	}
	
}
