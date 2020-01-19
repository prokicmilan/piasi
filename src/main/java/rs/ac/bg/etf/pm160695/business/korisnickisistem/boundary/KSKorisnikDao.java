package rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.control.SecurityProvider;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;

@Stateless
public class KSKorisnikDao extends BaseEntityDao<KSKorisnik> {

	@Inject
	private SecurityProvider securityProvider;

	public KSKorisnikDao() {
		super(KSKorisnik.class);
	}

	public KSKorisnik findByUsername(String username) {
		List<KSKorisnik> korisnikList = findByParameter("username", username);

		if (korisnikList.size() > 1) {
			// TODO: exception
			return null;
		}

		return korisnikList.isEmpty() ? null : korisnikList.get(0);
	}

	public CommonErrors registerUser(String username, String password, String firstName, String lastName, String email,
			LocalDate dateOfBirth, String placeOfBirth, String phoneNumber) {
		KSKorisnik korisnik = new KSKorisnik(username, firstName, lastName, email, dateOfBirth, placeOfBirth,
				phoneNumber);

		CommonErrors errors = validateSave(korisnik);

		if (errors.isEmpty()) {
			korisnik.setSalt(securityProvider.generateSalt());
			korisnik.setPassword(securityProvider.generateSaltedPassword(password, korisnik.getSalt()));
			persistOrMerge(korisnik);
		}

		return errors;
	}

	/**
	 * Metoda za validaciju cuvanja korisnika
	 * 
	 * @param korisnik
	 * @return true ako je validan, false u suprotnom
	 */
	private CommonErrors validateSave(KSKorisnik korisnik) {
		CommonErrors errors = new CommonErrors();
		if (!isUsernameUnique(korisnik.getUsername())) {
			errors.add("Korisničko ime nije jedinstveno");
		}
		if (!isEmailUnique(korisnik.getEmail())) {
			errors.add("Email nije jedinstven");
		}

		return errors;
	}

	/**
	 * Metoda za proveru jedinstvenosti korisnickog imena
	 * 
	 * @param username
	 * @param errors
	 */
	private boolean isUsernameUnique(String username) {
		return findByParameter("username", username).isEmpty();
	}

	/**
	 * Metoda za proveru jedinstvenosti emaila
	 * 
	 * @param email
	 * @param errors
	 */
	private boolean isEmailUnique(String email) {
		return findByParameter("email", email).isEmpty();
	}

}
