package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQCreationDetailsBacking;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.FormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.utility.QuestionDataReader;

@Named
@ViewScoped
public class TestCreationDetailsBacking extends TQCreationDetailsBacking {

	private static final long serialVersionUID = -1922383613850915029L;

	@Inject
	private TestDao testDao;
	
	@Inject
	private TestSolutionDao testSolutionDao;
	
	private Integer trajanje;

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
			((Test) tq).setTrajanje(trajanje);
			
			testDao.save((Test)tq, getQuestions(), currentUserBean.getUlogovaniKorisnik());
		}
	}
	
	@Override
	public void initialize() {
		super.initialize();
		if (edit != null) {
			trajanje = ((Test) tq).getTrajanje();
		}
		vecPopunjen = testSolutionDao.testAlreadySolvedByUser((Test) tq, currentUserBean.getUlogovaniKorisnik());
		if (vecPopunjen) {
			messageDispatcher.info("info.test.popunjen");
		}
		if (!tqAktivan) {
			messageDispatcher.info("info.test.nijeAktivan");
		}
	}
	
	@Override
	protected void save() {
		testDao.saveTest(naziv, opis, pocetak, kraj, trajanje, getQuestions(), currentUserBean.getUlogovaniKorisnik());
	}
	
	@Override
	protected List<? extends FormField> readQuestionData() {
		return QuestionDataReader.readTestQuestionFormFields(((Test) tq).getTestQuestions());
	}
	
	@Override
	protected List<TestQuestionFormField> getQuestions() {
		return formModel.getControls().stream().map(DynaFormControl::getData)
				.map(d -> (TestQuestionFormField) d).collect(Collectors.toList());
	}
	
	@Override
	protected FormField createFormField() {
		return new TestQuestionFormField(numberOfQuestions++);
	}

	@Override
	protected boolean isValidFormData() {
		return super.isValidFormData();
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
	public String getSolveOutcome() {
		return "testSolve";
	}
	
	@Override
	protected String getSubmitOutcome() {
		return "testPretraga";
	}
	
	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}


}
