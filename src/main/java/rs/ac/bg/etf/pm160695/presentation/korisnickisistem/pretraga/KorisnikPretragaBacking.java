package rs.ac.bg.etf.pm160695.presentation.korisnickisistem.pretraga;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSKorisnikDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;

@Named
@ViewScoped
public class KorisnikPretragaBacking implements Serializable {

	private static final long serialVersionUID = 5013100468521474860L;

	@Inject
	private Logger logger;

	@Inject
	private KSKorisnikDao ksKorisnikDao;

	private final Map<String, String> filters = new HashMap<>();

	private List<KSKorisnik> korisnikList = new LinkedList<>();

	private KSKorisnik selectedKorisnik;

	public void pretragaAction() {
		korisnikList = ksKorisnikDao.filter(filters);
		selectedKorisnik = null;
	}

	public void ponistiAction() {
		korisnikList.clear();
		filters.clear();
		selectedKorisnik = null;
	}

	public String noviAction() {
		logger.info(this.getClass().getName() + ".noviAction()");
		return "";
	}

	public List<KSKorisnik> getKorisnikList() {
		return korisnikList;
	}

	public KSKorisnik getSelectedKorisnik() {
		return selectedKorisnik;
	}

	public void setSelectedKorisnik(KSKorisnik selectedKorisnik) {
		this.selectedKorisnik = selectedKorisnik;
	}

	public Map<String, String> getFilters() {
		return filters;
	}
}
