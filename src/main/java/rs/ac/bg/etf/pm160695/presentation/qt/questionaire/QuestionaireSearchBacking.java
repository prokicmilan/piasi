package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
import rs.ac.bg.etf.pm160695.presentation.qt.TQSearchBacking;

@Named
@ViewScoped
public class QuestionaireSearchBacking extends TQSearchBacking {

	private static final long serialVersionUID = -2872811783870343179L;

	private Boolean anonymous = Boolean.TRUE;
	
	@Inject
	private QuestionaireDao questionaireDao;
	
	@Override
	public void searchAction() {
		logger.info("searchAction() --> naziv = " + naziv + " opis = " + opis + " datumOd = " + datumOd + " datumDo = " + datumDo + " anonymous = " + anonymous);
		tqList = questionaireDao.search(naziv, opis, datumOd, datumDo, anonymous);
		logger.info("searchAction() --> tqList = " + tqList);
		selected = null;
	}
	
	@Override
	public void cancelAction() {
		super.cancelAction();
		anonymous = Boolean.FALSE;
	}

	@Override
	public boolean isRenderedTrajanje() {
		return false;
	}
	
	@Override
	public String getNoviOutcome() {
		return "questionaireNovi";
	}
	
	@Override
	public String getDetaljiOutcome() {
		return "questionaireDetalji";
	}
	
	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}


}
