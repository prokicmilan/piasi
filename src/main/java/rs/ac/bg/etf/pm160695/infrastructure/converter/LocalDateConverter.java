package rs.ac.bg.etf.pm160695.infrastructure.converter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import rs.ac.bg.etf.pm160695.infrastructure.util.Formatting;

@FacesConverter(value = "localDateConverter")
public class LocalDateConverter implements Converter<LocalDate> {

	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty() || value.isBlank()) {
			return null;
		}
		try {
			return LocalDate.parse(value, Formatting.DATUM_FORMATTER);
		} catch (DateTimeParseException dtpe) {
			dtpe.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
		if (value == null) {
			return "";
		}
		return Formatting.DATUM_FORMATTER.format(value);
	}

}
