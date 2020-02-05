package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

public abstract class TQSearchBacking extends BaseBackingBean {

	private static final long serialVersionUID = 1760977821413925408L;

	protected String naziv;
	
	protected String opis;
	
	protected LocalDate datumOd;
	
	protected LocalDate datumDo;
	
	protected List<? extends TestQuestionaire> tqList = new LinkedList<>();
	
	protected TestQuestionaire selected;
	
	public abstract void searchAction();
	
	public abstract boolean isRenderedTrajanje();
	
	public abstract String getNoviOutcome();

	public abstract String getDetaljiOutcome();
	
	public void onRowSelect() {
		logger.info("onRowSelect() --> selected = " + selected.getNaziv());
	}
	
	public void cancelAction() {
		tqList.clear();
		selected = null;
		naziv = null;
		opis = null;
		datumOd = null;
		datumDo = null;
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(LocalDate datumOd) {
		this.datumOd = datumOd;
	}

	public LocalDate getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(LocalDate datumDo) {
		this.datumDo = datumDo;
	}

	public TestQuestionaire getSelected() {
		return selected;
	}

	public void setSelected(TestQuestionaire selected) {
		this.selected = selected;
	}
	
	public List<? extends TestQuestionaire> getTqList() {
		return tqList;
	}
	
}
