package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.FormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.QuestionaireQuestionFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.presentation.qt.TQCreationBacking;

@Named
@ViewScoped
public class QuestionaireCreationBacking extends TQCreationBacking {

	private static final long serialVersionUID = -8164550945331565775L;

	@Inject
	private QuestionaireDao questionaireDao;

	private Boolean anonymous = Boolean.FALSE;

	@PostConstruct
	protected void init() {
		super.init();
		addNewQuestionRow();
	}
	
	public void test() {
		logger.info("value changed");
		logger.info("tq is " + tq == null ? "null" : "not null");
	}

	@Override
	public void saveAction() {
		if (isValidFormData()) {
			ObjectMapper objectMapper = new ObjectMapper();
			List<QuestionaireQuestionFormField> questions = getQuestions();
			
			try {
				tq.setNaziv(naziv);
				tq.setOpis(opis);
				tq.setDatumOd(pocetak);
				tq.setDatumDo(kraj);
				((Questionaire) tq).setAnonymous(anonymous);
				tq.setQuestionsData(objectMapper.writeValueAsString(questions));
				tq.setKsKorisnik(currentUserBean.getUlogovaniKorisnik());
				
				questionaireDao.save((Questionaire)tq);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void initialize() {
		super.initialize();
		if (edit != null) {
			anonymous = ((Questionaire) tq).getAnonymous();
		}
	}
	
	@Override
	protected void save() {
		questionaireDao.saveQuestionaire(naziv, opis, pocetak, kraj, anonymous, questionsJsonData, currentUserBean.getUlogovaniKorisnik());
	}
	
	@Override
	protected List<QuestionaireQuestionFormField> getQuestions() {
		return formModel.getControls().stream().map(DynaFormControl::getData)
				.map(d -> (QuestionaireQuestionFormField) d).collect(Collectors.toList());
	}
	
	@Override
	protected List<? extends FormField> readQuestionData(ObjectMapper objectMapper) throws JsonProcessingException {
		return objectMapper.readValue(tq.getQuestionsData(), new TypeReference<List<QuestionaireQuestionFormField>>() {});
	}
	
	@Override
	public boolean isRenderedTrajanje() {
		return false;
	}
	
	@Override
	public boolean isRenderedAnonimno() {
		return true;
	}

	@Override
	protected FormField createFormField() {
		return new QuestionaireQuestionFormField(numberOfQuestions++);
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

}
