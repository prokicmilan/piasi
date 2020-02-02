package rs.ac.bg.etf.pm160695.infrastructure.messaging;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageDispatcher implements Serializable {

	private static final long serialVersionUID = -6365570614961625474L;

	public void info(String msg, Object... params) {
		String message = getMessage(msg, params);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Messages.getMessageFromMessageDispatcher("sistem.obavestenje"), message);

		FacesContext.getCurrentInstance().addMessage("msgs", facesMessage);
	}

	public void warn(String msg, Object... params) {
		String message = getMessage(msg, params);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
				Messages.getMessageFromMessageDispatcher("sistem.upozorenje"), message);

		FacesContext.getCurrentInstance().addMessage("msgs", facesMessage);
	}

	public void error(String msg, Object... params) {
		String message = getMessage(msg, params);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				Messages.getMessageFromMessageDispatcher("sistem.greska"), message);

		FacesContext.getCurrentInstance().addMessage("msgs", facesMessage);
	}

	private String getMessage(String msg, Object... params) {
		String retVal = Messages.getMessageFromMessageDispatcher(msg, params);
		return retVal.isBlank() ? msg : retVal;
	}
}
