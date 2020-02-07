package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;


import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQCreationDetailsBacking;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.FormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.QuestionaireQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.utility.QuestionDataReader;

@Named
@ViewScoped
public class QuestionaireCreationDetailsBacking extends TQCreationDetailsBacking {

	private static final long serialVersionUID = -8164550945331565775L;

	@Inject
	private QuestionaireDao questionaireDao;
	
	@Inject
	private QuestionaireSolutionDao questionaireSolutionDao;

	private Boolean anonymous = Boolean.FALSE;

	@PostConstruct
	protected void init() {
		super.init();
		addNewQuestionRow();
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
		vecPopunjen = questionaireSolutionDao.questionaireAlreadySolvedByUser((Questionaire) tq, currentUserBean.getUlogovaniKorisnik());
		if (vecPopunjen) {
			messageDispatcher.info("info.anketa.popunjena");
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
		return QuestionDataReader.readQuestionaireQuestionFormFields(((Questionaire) tq).getQuestionaireQuestions());
	}
	
	@Override
	protected FormField createFormField() {
		return new QuestionaireQuestionFormField(numberOfQuestions++);
	}

	@Override
	public boolean isDisabledZapocniResavanje() {
		return vecPopunjen;
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
	public String getSolveOutcome() {
		return "questionaireSolve";
	}
	
	@Override
	public String getSubmitOutcome() {
		return "anketaPretraga";
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

}
