package rs.ac.bg.etf.pm160695.presentation.qt.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;

@FacesConverter(value = "questionaireConverter")
public class QuestionaireConverter implements Converter<Questionaire> {

	@Inject
	private QuestionaireDao questionaireDao;
	
	@Override
	public Questionaire getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			return questionaireDao.find(Long.parseLong(value));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, value + " nije validan id ankete", null));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Questionaire value) {
		if (value == null) {
			return "";
		}
		return String.valueOf(value.getId());
	}

}
