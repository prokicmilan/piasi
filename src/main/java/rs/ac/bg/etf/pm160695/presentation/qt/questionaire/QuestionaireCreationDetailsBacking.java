package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;


import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQCreationDetailsBacking;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.FormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.QuestionaireQuestionFormField;

@Named
@ViewScoped
public class QuestionaireCreationDetailsBacking extends TQCreationDetailsBacking {

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
			tq.setNaziv(naziv);
			tq.setOpis(opis);
			tq.setDatumOd(pocetak);
			tq.setDatumDo(kraj);
			((Questionaire) tq).setAnonymous(anonymous);
			
			questionaireDao.save((Questionaire)tq, getQuestions(), currentUserBean.getUlogovaniKorisnik());
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
		questionaireDao.saveQuestionaire(naziv, opis, pocetak, kraj, anonymous, getQuestions(), currentUserBean.getUlogovaniKorisnik());
	}
	
	@Override
	protected List<QuestionaireQuestionFormField> getQuestions() {
		return formModel.getControls().stream().map(DynaFormControl::getData)
				.map(d -> (QuestionaireQuestionFormField) d).collect(Collectors.toList());
	}
	
	@Override
	protected List<? extends FormField> readQuestionData() {
		return ((Questionaire) tq).getQuestionaireQuestions().stream().map(questionaireQuestion -> {
			QuestionaireQuestionFormField qqff = new QuestionaireQuestionFormField();
			qqff.setInputType(questionaireQuestion.getInputType());
			qqff.setQuestion(questionaireQuestion.getQuestion());
			qqff.setAnswers(questionaireQuestion.getAnswers());
			return qqff;
		}).collect(Collectors.toList());
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
