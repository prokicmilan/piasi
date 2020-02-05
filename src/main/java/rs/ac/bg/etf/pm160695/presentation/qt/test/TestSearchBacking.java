package rs.ac.bg.etf.pm160695.presentation.qt.test;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestDao;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQSearchBacking;

@Named
@ViewScoped
public class TestSearchBacking extends TQSearchBacking {

	private static final long serialVersionUID = -6497475489452436949L;

	@Inject
	private TestDao testDao;
	
	@Override
	public void searchAction() {
		logger.info("searchAction() -> naziv = " + naziv + " opis = " + opis + " datumOd = " + datumOd + " datumDo = " + datumDo);
		tqList = testDao.search(naziv, opis, datumOd, datumDo);
		selected = null;
	}

	@Override
	public boolean isRenderedTrajanje() {
		return true;
	}
	
	@Override
	public String getNoviOutcome() {
		return "testNovi";
	}
	
	@Override
	public String getDetaljiOutcome() {
		return "testDetalji";
	}
	
}
