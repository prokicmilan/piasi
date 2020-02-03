package rs.ac.bg.etf.pm160695.presentation.qt.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;

@FacesConverter(value = "testConverter")
public class TestConverter implements Converter<Test> {

	@Inject
	private TestDao testDao;
	
	@Override
	public Test getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			return testDao.find(Long.parseLong(value));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, value + " nije validan id testa", null));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Test value) {
		if (value == null) {
			return "";
		}
		return String.valueOf(value.getId());
	}

}
