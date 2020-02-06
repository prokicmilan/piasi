package rs.ac.bg.etf.pm160695.presentation.korisnickisistem;

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
public class PasswordChangeBacking extends BaseBackingBean {

	private static final long serialVersionUID = 4702335356969922080L;

	@Inject
	private KSKorisnikDao ksKorisnikDao;
	
	@Inject LoginBacking loginBacking;
	
	private KSKorisnik korisnik;
	
	private String oldPassword;
	
	private String newPassword;
	
	public void saveNewPassword() {
		CommonErrors errors = ksKorisnikDao.saveNewPassword(korisnik, oldPassword, newPassword);
		
		if (errors.isEmpty()) {
			loginBacking.logoutAction();
			messageDispatcher.info("info.lozinka.uspesnoPromenjena", korisnik.getUsername());
		}
		else {
			for (CommonError error : errors.getErrors()) {
				logger.severe(error.getMessage());
			}
		}
	}

	public KSKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KSKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
