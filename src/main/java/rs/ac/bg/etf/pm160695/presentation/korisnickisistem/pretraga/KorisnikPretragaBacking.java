package rs.ac.bg.etf.pm160695.presentation.korisnickisistem.pretraga;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSKorisnikDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonError;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;

@Named
@ViewScoped
public class KorisnikPretragaBacking extends BaseBackingBean {

	private static final long serialVersionUID = 5013100468521474860L;

	@Inject
	private KSKorisnikDao ksKorisnikDao;

	private final Map<String, String> filters = new HashMap<>();

	private List<KSKorisnik> korisnikList = new LinkedList<>();

	private KSKorisnik selectedKorisnik;

	public void pretragaAction() {
		korisnikList = ksKorisnikDao.filter(filters);
		korisnikList.remove(currentUserBean.getUlogovaniKorisnik());
		selectedKorisnik = null;
	}

	public void ponistiAction() {
		korisnikList.clear();
		filters.clear();
		selectedKorisnik = null;
	}

	public void aktivirajAction() {
		logger.info(this.getClass().getName() + ".aktivirajAction() --> selectedKorisnik = "
				+ selectedKorisnik.getUsername());
		CommonErrors errors = ksKorisnikDao.activateKorisnik(selectedKorisnik, currentUserBean.getUlogovaniKorisnik());
		if (errors.isEmpty()) {
			messageDispatcher.info("info.korisnik.aktiviran", selectedKorisnik.getUsername());
			// osvezavamo podatke jer smo izvrsili promenu
			pretragaAction();
		} else {
			for (CommonError error : errors.getErrors()) {
				messageDispatcher.error(error.getMessage());
			}
		}
	}

	public void deaktivirajAction() {
		logger.info(this.getClass().getName() + ".deaktivirajAction() --> selectedKorisnik = "
				+ selectedKorisnik.getUsername());
		CommonErrors errors = ksKorisnikDao.deactivateKorisnik(selectedKorisnik,
				currentUserBean.getUlogovaniKorisnik());
		if (errors.isEmpty()) {
			messageDispatcher.info("info.korisnik.deaktiviran", selectedKorisnik.getUsername());
			// osvezavamo podatke jer smo izvrsili promenu
			pretragaAction();
		} else {
			for (CommonError error : errors.getErrors()) {
				messageDispatcher.error(error.getMessage());
			}
		}
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
