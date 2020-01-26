package rs.ac.bg.etf.pm160695.presentation.korisnickisistem.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary.KSKorisnikDao;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;

@FacesConverter(value = "ksKorisnikConverter")
public class KSKorisnikConverter implements Converter<KSKorisnik> {

	@Inject
	private KSKorisnikDao ksKorisnikDao;

	@Override
	public KSKorisnik getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			return ksKorisnikDao.findById(Long.parseLong(value));
		} catch (NumberFormatException e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, value + " nije validan id korisnika", null));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, KSKorisnik value) {
		if (value == null) {
			return "";
		}
		return String.valueOf(value.getId());
	}

}
