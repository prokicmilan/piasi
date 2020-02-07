package rs.ac.bg.etf.pm160695.presentation.qt.test;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQSolutionsBacking;

@Named
@ViewScoped
public class TestSolutionsBacking extends TQSolutionsBacking {

	private static final long serialVersionUID = -1438157069225809274L;
	
	@Inject
	private TestSolutionDao testSolutionDao;
	
	public void initialize() {
		solutions = testSolutionDao.getTestSolutionsForTest((Test) tq);
	}
	
	@Override
	public String getDetaljiOutcome() {
		return "testRezultatiDetalji";
	}

}
