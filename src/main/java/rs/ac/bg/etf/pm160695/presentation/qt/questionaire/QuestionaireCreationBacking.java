package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.FormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.QuestionaireQuestionFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
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

	@Override
	public void submitForm() {
		logger.info("submitForm() - questionaireCreationBacking");

		if (isValidFormData()) {
			List<QuestionaireQuestionFormField> questions = new LinkedList<>();

			questions = getFormModel().getControls().stream().map(DynaFormControl::getData)
					.map(d -> (QuestionaireQuestionFormField) d).collect(Collectors.toList());

			questionaireDao.saveQuestionaire(naziv, opis, pocetak, kraj, anonymous, questions,
					currentUserBean.getUlogovaniKorisnik());
		}
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
