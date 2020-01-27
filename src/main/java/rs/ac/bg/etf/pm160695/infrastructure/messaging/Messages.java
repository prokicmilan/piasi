package rs.ac.bg.etf.pm160695.infrastructure.messaging;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class Messages {
	private static final String UI_BUNDLE_MESSAGE_DISPATCHER_MESSAGES = "MessageDispatcherMessages";
	private static final String UI_BUNDLE_POSLOVNA_PRAVILA_MESSAGES = "PoslovnaPravilaValidationMessages";
	
	public static String getMessageFromMessageDispatcher(String key, Object... params) {
		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = ResourceBundle.getBundle(UI_BUNDLE_MESSAGE_DISPATCHER_MESSAGES, getLocale());
		} catch (MissingResourceException e) {
			return "Ne postoji datoteka " + UI_BUNDLE_MESSAGE_DISPATCHER_MESSAGES + "_" + getLocale() + ".properties";
		}
		
		return getString(resourceBundle, key, params);
	}
	
	public static String getMessageFromPoslovnaPravilaValidation(String key, Object... params) {
		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = ResourceBundle.getBundle(UI_BUNDLE_POSLOVNA_PRAVILA_MESSAGES, getLocale());
		} catch (MissingResourceException e) {
			return "Ne postoji datoteka " + UI_BUNDLE_POSLOVNA_PRAVILA_MESSAGES + "_" + getLocale() + ".properties";
		}
		
		return getString(resourceBundle, key, params);
	}
	
	private static String getString(ResourceBundle resourceBundle, String key, Object... params) {
		if (key == null || resourceBundle == null) {
			return "";
		}
		
		try {
			return MessageFormat.format(resourceBundle.getString(key), params);
		} catch (MissingResourceException e) {
			return "";
		}
	}
	
	private static Locale getLocale() {
		Locale locale = null;
		
		if (FacesContext.getCurrentInstance() == null) {
			return Locale.getDefault();
		}
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		if (viewRoot != null) {
			locale = viewRoot.getLocale();
		}
		
		return locale != null ? locale : Locale.getDefault();
	}
	
	private Messages() {}
}
