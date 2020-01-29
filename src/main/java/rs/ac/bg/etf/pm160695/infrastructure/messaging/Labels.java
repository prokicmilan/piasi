package rs.ac.bg.etf.pm160695.infrastructure.messaging;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class Labels {

	private static final String UI_BUNDLE_LABELS = "Labels";
	private static final String UI_BUNDLE_LABELS_TITLES = "LabelsTitle";
	private static final String UI_BUNDLE_LABELS_MESSAGES = "LabelsMessage";
	private static final String UI_BUNDLE_LABELS_COLUMNS = "LabelsColumn";

	public static String getLabel(String key, Object... params) {
		ResourceBundle resourceBundle = null;

		try {
			resourceBundle = ResourceBundle.getBundle(UI_BUNDLE_LABELS, getLocale());
		} catch (MissingResourceException e) {
			Logger.getLogger("rs.ac.bg.etf.pm160695.infrastructure.messaging.Labels").severe("Labels: " + e.getMessage());
			return "Ne postoji datoteka " + UI_BUNDLE_LABELS + "_" + getLocale() + ".properties";
		}

		return getString(key, resourceBundle, params);
	}

	public static String getTitle(String key, Object... params) {
		ResourceBundle resourceBundle = null;

		try {
			resourceBundle = ResourceBundle.getBundle(UI_BUNDLE_LABELS_TITLES, getLocale());
		} catch (MissingResourceException e) {
			return "Ne postoji datoteka " + UI_BUNDLE_LABELS_TITLES + "_" + getLocale() + ".properties";
		}

		return getString(key, resourceBundle, params);
	}

	public static String getMessage(String key, Object... params) {
		ResourceBundle resourceBundle = null;

		try {
			resourceBundle = ResourceBundle.getBundle(UI_BUNDLE_LABELS_MESSAGES, getLocale());
		} catch (MissingResourceException e) {
			return "Ne postoji datoteka " + UI_BUNDLE_LABELS_MESSAGES + "_" + getLocale() + ".properties";
		}

		return getString(key, resourceBundle, params);
	}

	public static String getColumn(String key, Object... params) {
		ResourceBundle resourceBundle = null;

		try {
			resourceBundle = ResourceBundle.getBundle(UI_BUNDLE_LABELS_COLUMNS);
		} catch (MissingResourceException e) {
			return "Ne postoji datoteka " + UI_BUNDLE_LABELS_COLUMNS + "_" + getLocale() + ".properties";
		}

		return getString(key, resourceBundle, params);
	}

	private static String getString(String key, ResourceBundle bundle, Object... params) {
		if (key == null || key.isBlank()) {
			return null;
		}
		return MessageFormat.format(bundle.getString(key), params);
	}

	private static Locale getLocale() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = context != null ? context.getViewRoot() : null;
		return viewRoot != null ? viewRoot.getLocale() : Locale.getDefault();
	}

	private Labels() {}
}
