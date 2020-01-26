package rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.control.SecurityProvider;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik_;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;
import rs.ac.bg.etf.pm160695.infrastructure.validation.ValidationUtils;

@Stateless
public class KSKorisnikDao extends BaseEntityDao<KSKorisnik> {

	@Inject
	private KSUlogaDao ksUlogaDao;

	@Inject
	private SecurityProvider securityProvider;

	@Inject
	private EntityManager em;

	public KSKorisnikDao() {
		super(KSKorisnik.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public KSKorisnik findById(Long id) {
		return em.find(getEntityClass(), id);
	}

	public KSKorisnik findByUsername(String username) {
		List<KSKorisnik> korisnikList = findByParameter(KSKorisnik_.username, username);

		if (korisnikList.size() > 1) {
			// TODO: exception
			return null;
		}

		return korisnikList.isEmpty() ? null : korisnikList.get(0);
	}

	public CommonErrors registerUser(String username, String password, String firstName, String lastName, String email,
			LocalDate dateOfBirth, String placeOfBirth, String phoneNumber, KSUloga type) {
		KSKorisnik korisnik = new KSKorisnik(username, firstName, lastName, email, dateOfBirth, placeOfBirth,
				phoneNumber);

		korisnik.setPassword("");
		korisnik.setSalt("");
		korisnik.setAktivan(false);
		CommonErrors errors = validateSave(korisnik);

		if (errors.isEmpty()) {
			korisnik.setSalt(securityProvider.generateSalt());
			korisnik.setPassword(securityProvider.generateSaltedPassword(password, korisnik.getSalt()));
			korisnik.addUloge(ksUlogaDao.findOsnovne());
			persistOrMerge(korisnik);
		}

		return errors;
	}

	/**
	 * Metoda za cuvanje korisnika, bilo da je u pitanju izmena ili kreiranje novog
	 * od strane administratora
	 * 
	 * @param korisnik
	 * @param ulogovaniKorisnik
	 * @return
	 */
	public CommonErrors saveUser(KSKorisnik korisnik, KSKorisnik ulogovaniKorisnik) {
		CommonErrors errors;
		korisnik.setKsKorisnik(ulogovaniKorisnik);
		if (korisnik.getAktivan() == null) {
			korisnik.setAktivan(Boolean.TRUE);
		}

		errors = validate(korisnik);

		if (errors.isEmpty()) {
			if (korisnik.getId() == null) {
				// cuvamo novog
			}
		}

		return errors;
	}

	private CommonErrors validate(KSKorisnik korisnik) {
		CommonErrors errors;

		if (korisnik.getId() == null) {
			errors = validateSave(korisnik);
		} else {
			errors = validateUpdate(korisnik);
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
		CommonErrors errors = ValidationUtils.validate(korisnik);
		if (!isUsernameUnique(korisnik.getUsername())) {
			errors.add("Korisničko ime nije jedinstveno");
		}
		if (!isEmailUnique(korisnik.getEmail())) {
			errors.add("Email nije jedinstven");
		}

		return errors;
	}

	private CommonErrors validateUpdate(KSKorisnik korisnik) {
		CommonErrors errors = ValidationUtils.validate(korisnik);

		KSKorisnik k = findById(korisnik.getId());
		if (!k.getEmail().equals(korisnik.getEmail()) && !isEmailUnique(korisnik.getEmail())) {
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
		return executeCountQuery(KSKorisnik_.username, username, Boolean.TRUE) == 0;
	}

	/**
	 * Metoda za proveru jedinstvenosti emaila
	 * 
	 * @param email
	 * @param errors
	 */
	private boolean isEmailUnique(String email) {
		return executeCountQuery(KSKorisnik_.email, email, Boolean.TRUE) == 0;
	}

}
