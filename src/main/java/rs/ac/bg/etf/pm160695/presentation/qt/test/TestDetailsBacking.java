package rs.ac.bg.etf.pm160695.presentation.qt.test;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

@Named
@ViewScoped
public class TestDetailsBacking extends BaseBackingBean {

	private static final long serialVersionUID = 9189882119883990207L;

	private Test test;
	
	private Boolean edit;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	
}
