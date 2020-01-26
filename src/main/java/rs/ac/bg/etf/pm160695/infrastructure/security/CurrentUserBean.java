package rs.ac.bg.etf.pm160695.infrastructure.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSKorisnikDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;

@SessionScoped
public class CurrentUserBean implements Serializable {

	private static final long serialVersionUID = 881570699827136134L;

	@Inject
	private KSKorisnikDao ksKorisnikDao;

	private KSKorisnik ulogovaniKorisnik;

	public void onLogin(@Observes @CurrentUserLogin CurrentUserChangedEvent event) {
		ulogovaniKorisnik = ksKorisnikDao.findByUsername(event.getUsername());
	}

	public void onLogout(@Observes @CurrentUserLogout CurrentUserChangedEvent event) {
		ulogovaniKorisnik = null;
	}

	@Named("ulogovaniKorisnik")
	@Produces
	@CurrentUser
	public KSKorisnik getUlogovaniKorisnik() {
		return ulogovaniKorisnik;
	}
}
