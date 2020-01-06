package rs.ac.bg.etf.pm160695.presentation.razvoj;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSUlogaDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;

@ViewScoped
@Named
public class Razvoj implements Serializable {

	private static final long serialVersionUID = 9004290161325992942L;

	@Inject
	private KSUlogaDao ksUlogaDao;

	private List<KSUloga> ksUlogaList;

	@PostConstruct
	private void init() {
		ksUlogaList = new LinkedList<>();
	}

	public void ulogeAction() {
		ksUlogaList = ksUlogaDao.findAll();
	}

	public List<KSUloga> getKsUlogaList() {
		return ksUlogaList;
	}

}
