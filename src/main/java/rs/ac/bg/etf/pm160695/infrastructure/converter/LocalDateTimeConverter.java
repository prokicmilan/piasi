package rs.ac.bg.etf.pm160695.infrastructure.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import rs.ac.bg.etf.pm160695.infrastructure.util.Formatting;

@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

	@Override
	public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			return LocalDateTime.parse(value, Formatting.DATUM_VREME_FORMATTER);
		} catch (DateTimeParseException dtpe) {
			dtpe.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
		if (value == null) {
			return "";
		}
		return Formatting.DATUM_VREME_FORMATTER.format(value);
	}

}
