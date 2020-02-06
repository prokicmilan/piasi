package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing;


import org.primefaces.extensions.model.dynaform.DynaFormModel;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

public abstract class TQSolveBacking extends BaseBackingBean {

	private static final long serialVersionUID = -532528708806418325L;

	protected TestQuestionaire tq;
	
	protected DynaFormModel formModel;
	
	public abstract String submitAnswersAction();
	
	public abstract void populateModel();
	
	public void initialize() {
		formModel = new DynaFormModel();
		populateModel();
	}

	public TestQuestionaire getTq() {
		return tq;
	}

	public void setTq(TestQuestionaire tq) {
		this.tq = tq;
	}

	public DynaFormModel getFormModel() {
		return formModel;
	}
}
