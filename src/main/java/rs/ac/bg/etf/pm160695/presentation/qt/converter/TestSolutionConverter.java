package rs.ac.bg.etf.pm160695.presentation.qt.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;

@FacesConverter(value = "testSolutionConverter")
public class TestSolutionConverter implements Converter<TestSolution> {

	@Inject
	private TestSolutionDao testSolutionDao;
	
	@Override
	public TestSolution getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			TestSolution ts = testSolutionDao.find(Long.parseLong(value));
			return ts;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, value + " nije validan id popunjavanja testa", null));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TestSolution value) {
		if (value == null) {
			return "";
		}
		return String.valueOf(value.getId());
	}

}
