package rs.ac.bg.etf.pm160695.presentation.korisnickisistem;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserChangedEvent;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserLogin;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserLogout;

@Named
@ViewScoped
public class LoginBacking implements Serializable {

	private static final long serialVersionUID = 4603656371326301973L;

	private String FORWARD_REQUEST_URI;

	// Injections

	@Inject
	@CurrentUserLogin
	private Event<CurrentUserChangedEvent> userLoginEvent;

	@Inject
	@CurrentUserLogout
	private Event<CurrentUserChangedEvent> userLogoutEvent;

	@Inject
	private Logger logger;

	// Fields

	@NotNull
	private String username;

	@NotNull
	private String password;

	@PostConstruct
	public void init() {
		FORWARD_REQUEST_URI = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get(RequestDispatcher.FORWARD_REQUEST_URI);
	}

	// Actions

	public void loginAction() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		if (request.getUserPrincipal() != null) {
			// na sesiji postoji korisnik
			if (request.getUserPrincipal().getName().equals(username)) {
				// korisnik pokusava da se loguje sa istim username-om
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(FORWARD_REQUEST_URI != null ? FORWARD_REQUEST_URI : request.getContextPath());
				} catch (IOException e) {
					logger.severe("Redirect vec ulogovanog korisnika nije uspeo!");
				}
			} else {
				// pokusava da se loguje sa drugom username-om, radimo logout
				try {
					logger.info("Logovanje sa drugim userom na istoj sesiji. Stari user: "
							+ request.getUserPrincipal().getName() + " Novi user: " + username);
					request.logout();
				} catch (ServletException e) {
					logger.severe("Nije uspela promena korisnika na sesiji");
				}
			}
		}

		try {
			// pokusavamo login
			request.login(username, password);
			userLoginEvent.fire(new CurrentUserChangedEvent(username));

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(FORWARD_REQUEST_URI != null ? FORWARD_REQUEST_URI : request.getContextPath());
		} catch (ServletException e) {
			// login exception
			String message = e.getMessage();

			if (message.startsWith("UT010031: Login failed")) {
				// neispravan username ili lozinka
				message = "Neispravno korisničko ime ili lozinka";
			} else {
				logger.severe("Neocekivana greska pri loginu: " + message);
			}

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		} catch (IOException e) {
			logger.severe("Neuspesno redirektovanje korisnika pri loginu.");
		}

	}

	public void logoutAction() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		try {
			request.logout();
			userLogoutEvent.fire(new CurrentUserChangedEvent(null));

			FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath());
		} catch (ServletException e) {
			logger.severe("Neocekivana greska pri logoutu: " + e.getMessage());
		} catch (IOException e) {
			logger.severe("Neuspesno redirektovanje korisnika pri logoutu.");
		}

	}

	// Getters and setters

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
