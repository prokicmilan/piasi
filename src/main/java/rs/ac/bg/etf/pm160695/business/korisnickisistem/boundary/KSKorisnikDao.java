package rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class KSKorisnikDao extends BaseEntityDao<KSKorisnik> {

	public KSKorisnikDao() {
		super(KSKorisnik.class);
	}

	public KSKorisnik findByUsername(String username) {
		List<KSKorisnik> korisnikList = findByParameter("username", username);

		if (korisnikList.size() > 0) {
			// TODO: exception
			return null;
		}

		return korisnikList.isEmpty() ? null : korisnikList.get(0);
	}

	public KSKorisnik registerUser(String username, String password, String firstName, String lastName, String email,
			LocalDate dateOfBirth, String placeOfBirth, String phoneNumber) {
		KSKorisnik korisnik = new KSKorisnik(username, password, firstName, lastName, email, dateOfBirth, placeOfBirth,
				phoneNumber);
		if (validateKorisnik(korisnik)) {
			return persistOrMerge(korisnik);
		} else {
			return null;
		}
	}

	/**
	 * Sprovodimo dodatne validacije korisnika koje se ne mogu izvrsiti anotacijama
	 * 
	 * @param korisnik
	 * @return true ako je validan, false u suprotnom
	 */
	private boolean validateKorisnik(KSKorisnik korisnik) {
		return isUsernameUnique(korisnik.getUsername()) && isEmailUnique(korisnik.getEmail());
	}

	/**
	 * Validiramo jedinstvenost korisnickog imena u bazi
	 * 
	 * @param username
	 * @return true ako je korisnicko ime jedinstveno, false ako nije
	 */
	private boolean isUsernameUnique(String username) {
		return findByParameter("username", username).isEmpty() ? true : false;
	}

	/**
	 * Validiramo jedinstvenost email-a u bazi
	 * 
	 * @param email
	 * @return true ako je email jedinstven, false ako nije
	 */
	private boolean isEmailUnique(String email) {
		return findByParameter("email", email).isEmpty() ? true : false;
	}

	// samo za testiranje
	public List<KSKorisnik> findAll() {
		return super.findAll();
	}

}
