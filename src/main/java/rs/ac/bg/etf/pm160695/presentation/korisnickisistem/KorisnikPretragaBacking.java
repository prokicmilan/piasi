package rs.ac.bg.etf.pm160695.presentation.korisnickisistem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;

@Named
@ViewScoped
public class KorisnikPretragaBacking implements Serializable {

	private static final long serialVersionUID = 5013100468521474860L;

	@Inject
	private Logger logger;

	private final Map<String, String> filters = new HashMap<>();

	private List<KSKorisnik> korisnikList = new LinkedList<>();

	public void pretragaAction() {
		logger.info(this.getClass().getName() + ".pretragaAction() --> filters = " + filters);
	}

	public void ponistiAction() {
		logger.info(this.getClass().getName() + ".ponistiAction()");
		korisnikList.clear();
		filters.clear();
	}

	public List<KSKorisnik> getKorisnikList() {
		return korisnikList;
	}

	public Map<String, String> getFilters() {
		return filters;
	}

}
