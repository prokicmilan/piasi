package rs.ac.bg.etf.pm160695.presentation.korisnickisistem;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSKorisnikDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSUlogaDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonError;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;

@ViewScoped
@Named
public class RegisterBacking extends BaseBackingBean {

	private static final long serialVersionUID = 1112494982140850963L;

	// Injections
	@Inject
	private KSKorisnikDao ksKorisnikDao;

	@Inject
	private KSUlogaDao ksUlogaDao;

	// Fields
	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(min = 2, message = "mora biti dužine {min} karaktera. Uneto ${validatedValue.length()} karaktera")
	private String password;

	@NotBlank
	@Size(max = 50)
	private String firstName;

	@NotBlank
	@Size(max = 50)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Email(message = "Adresa nije validna")
	private String email;

	@NotNull
	private LocalDate dateOfBirth;

	@NotBlank
	@Size(max = 50)
	private String placeOfBirth;

	@NotBlank
	@Pattern(regexp = "\\+(\\d{3})(\\d{2})(\\d{3,4})(\\d{3})", message = "Nije validan broj telefona")
	private String phoneNumber;

	@NotNull
	private KSUloga selectedUloga;

	private List<KSUloga> ulogaList;

	@PostConstruct
	protected void init() {
		ulogaList = ksUlogaDao.findFunkcionalne();
	}

	public void registerAction() {
		logger.info(this.getClass().getName() + ".register_action()");
		CommonErrors errors = ksKorisnikDao.registerUser(username, password, firstName, lastName, email, dateOfBirth,
				placeOfBirth, phoneNumber, selectedUloga);
		if (errors.isEmpty()) {
			messageDispatcher.info("info.registracija.uspesna");
		} else {
			for (CommonError error : errors.getErrors()) {
				messageDispatcher.error(error.getMessage());
			}
		}
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public KSUloga getSelectedUloga() {
		return selectedUloga;
	}

	public void setSelectedUloga(KSUloga selectedUloga) {
		this.selectedUloga = selectedUloga;
	}

	public List<KSUloga> getUlogaList() {
		return ulogaList;
	}

}
