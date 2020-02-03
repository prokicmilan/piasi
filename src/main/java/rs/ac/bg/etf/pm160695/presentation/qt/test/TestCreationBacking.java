package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.FormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestDao;
import rs.ac.bg.etf.pm160695.presentation.qt.TQCreationBacking;

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
	public void submitForm() {
		logger.info("submitForm() - testCreationBacking");

		if (isValidFormData()) {
			List<TestQuestionFormField> questions = new LinkedList<>();

			questions = getFormModel().getControls().stream().map(DynaFormControl::getData)
					.map(d -> (TestQuestionFormField) d).collect(Collectors.toList());

			testDao.saveTest(naziv, opis, pocetak, kraj, trajanje, questions, currentUserBean.getUlogovaniKorisnik());
		}
	}

	public void addQuestion() {
		addNewQuestionRow();
	}

	public void removeQuestion(TestQuestionFormField formField) {
		removeQuestionRow(formField);
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
