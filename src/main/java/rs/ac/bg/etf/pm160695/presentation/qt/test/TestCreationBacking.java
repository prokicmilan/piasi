package rs.ac.bg.etf.pm160695.presentation.qt.test;

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

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQCreationBacking;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.FormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TestQuestionFormField;

@Named
@ViewScoped
public class TestCreationBacking extends TQCreationBacking {

	private static final long serialVersionUID = -1922383613850915029L;

	@Inject
	private TestDao testDao;
	
	private Integer trajanje;

	@PostConstruct
	protected void init() {
		super.init();
		addNewQuestionRow();
	}
	
	@Override
	public void submitAction() {
		if (isValidFormData()) {
			List<TestQuestionFormField> questions = getQuestions();
			ObjectMapper objectMapper = new ObjectMapper();
			String questionsJsonData = "";
			try {
				questionsJsonData = objectMapper.writeValueAsString(questions);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			testDao.saveTest(naziv, opis, pocetak, kraj, trajanje, questionsJsonData, currentUserBean.getUlogovaniKorisnik());
		}
	}
	
	@Override
	public void saveAction() {
		if (isValidFormData()) {
			ObjectMapper objectMapper = new ObjectMapper();
			List<TestQuestionFormField> questions = getQuestions();
			
			try {
				tq.setNaziv(naziv);
				tq.setOpis(opis);
				tq.setDatumOd(pocetak);
				tq.setDatumDo(kraj);
				((Test) tq).setTrajanje(trajanje);
				tq.setQuestionsData(objectMapper.writeValueAsString(questions));
				tq.setKsKorisnik(currentUserBean.getUlogovaniKorisnik());
				
				testDao.save((Test)tq);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void initialize() {
		super.initialize();
		if (edit != null) {
			trajanje = ((Test) tq).getTrajanje();
		}
	}
	
	@Override
	protected void save() {
		testDao.saveTest(naziv, opis, pocetak, kraj, trajanje, questionsJsonData, currentUserBean.getUlogovaniKorisnik());
	}
	
	@Override
	protected List<? extends FormField> readQuestionData(ObjectMapper objectMapper) throws JsonProcessingException {
		return objectMapper.readValue(tq.getQuestionsData(), new TypeReference<List<TestQuestionFormField>>() {});
	}
	
	@Override
	protected List<TestQuestionFormField> getQuestions() {
		return formModel.getControls().stream().map(DynaFormControl::getData)
				.map(d -> (TestQuestionFormField) d).collect(Collectors.toList());
	}

	@Override
	protected boolean isValidFormData() {
		boolean formValid = super.isValidFormData();

		return formValid;
	}
	
	@Override
	public boolean isRenderedTrajanje() {
		return true;
	}
	
	@Override
	public boolean isRenderedAnonimno() {
		return false;
	}

	@Override
	protected FormField createFormField() {
		return new TestQuestionFormField(numberOfQuestions++);
	}
	
	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}


}
