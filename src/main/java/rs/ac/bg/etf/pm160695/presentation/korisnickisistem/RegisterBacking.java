package rs.ac.bg.etf.pm160695.presentation.korisnickisistem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Logger;

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
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonError;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;

@ViewScoped
@Named
public class RegisterBacking implements Serializable {

	private static final long serialVersionUID = 1112494982140850963L;

	// Injections
	@Inject
	private KSKorisnikDao ksKorisnikDao;
	
	@Inject
	private Logger logger;

	// Fields
	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(min = 8, message = "mora biti dužine {min} karaktera. Poslato ${validatedValue.length()} karaktera")
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

	@Pattern(regexp = "\\+(\\d{3})(\\d{2})(\\d{3,4})(\\d{3})", message = "Nije validan broj telefona")
	private String phoneNumber;

	public void registerAction() {
		logger.info(this.getClass().getName() + ".register_action()");
		CommonErrors errors = ksKorisnikDao.registerUser(username, password, firstName, lastName, email, dateOfBirth,
				placeOfBirth, phoneNumber);
		if (errors.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste se registrovali.", null));
		} else {
			for (CommonError error : errors.getErrors()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, error.getMessage(), null));
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

}
