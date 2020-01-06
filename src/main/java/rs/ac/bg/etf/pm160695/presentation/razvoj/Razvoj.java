package rs.ac.bg.etf.pm160695.presentation.razvoj;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSKorisnikDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;

@ViewScoped
@Named
public class Razvoj implements Serializable {

	private static final long serialVersionUID = 9004290161325992942L;

	@Inject
	private KSKorisnikDao ksKorisnikDao;

	private String username;
	private String password;

	private List<KSUloga> ksUlogaList;

	@PostConstruct
	private void init() {
		ksUlogaList = new LinkedList<>();
	}

	public void loginAction() {
		System.out.println(ksKorisnikDao.validateLogin(username, password));
	}

	public List<KSUloga> getKsUlogaList() {
		return ksUlogaList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
