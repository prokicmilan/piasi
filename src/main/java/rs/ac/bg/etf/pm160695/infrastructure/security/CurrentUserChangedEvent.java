package rs.ac.bg.etf.pm160695.infrastructure.security;

import java.io.Serializable;

public class CurrentUserChangedEvent implements Serializable {

	private static final long serialVersionUID = 5030830902423764085L;
	
	private String username;

	public CurrentUserChangedEvent(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
