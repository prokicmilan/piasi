package rs.ac.bg.etf.pm160695.presentation.korisnickisistem.pretraga;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

@Named
@ViewScoped
public class KorisnikProfilBacking extends BaseBackingBean {

	private static final long serialVersionUID = 6425992813106275719L;

	private Long userId;

	private Boolean edit;

	private KSKorisnik korisnik;

	public void sacuvajAction() {
		logger.info(this.getClass().getName() + ".sacuvajAction()");
	}

	public KSKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KSKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

}
