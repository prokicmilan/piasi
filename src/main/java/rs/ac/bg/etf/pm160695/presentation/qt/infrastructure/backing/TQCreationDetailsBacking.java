package rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;
import rs.ac.bg.etf.pm160695.infrastructure.messaging.Messages;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.FormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.InputType;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TQFormField;

public abstract class TQCreationDetailsBacking extends BaseBackingBean {

	private static final long serialVersionUID = 1785152636931133728L;

	protected DynaFormModel formModel;
	protected List<FormField> formFieldList = new LinkedList<>();

	protected int numberOfQuestions = 0;

	protected String naziv;
	protected String opis;
	protected LocalDate pocetak;
	protected LocalDate kraj;
	
	protected TestQuestionaire tq;
	protected Boolean edit;
	
	public abstract void saveAction();
	
	public abstract boolean isRenderedAnonimno();
	
	public abstract boolean isRenderedTrajanje();
	
	
	protected abstract void save();
	
	protected abstract FormField createFormField();
	
	protected abstract List<? extends TQFormField> getQuestions();
	
	protected abstract List<? extends FormField> readQuestionData();
	
	public abstract String getSolveOutcome();
	
	protected void init() {
		formModel = new DynaFormModel();
	}
	
	public void initialize() {
		if (edit != null) {
			naziv = tq.getNaziv();
			opis = tq.getOpis();
			pocetak = tq.getDatumOd();
			kraj = tq.getDatumDo();
			
			List<FormField> ffList = new LinkedList<>(formFieldList);
			
			for (FormField formField : ffList) {
				removeQuestion(formField);
			}
				
			formFieldList.addAll(readQuestionData());
			populateModel();
		}
	}
	
	public void submitAction() {
		if (isValidFormData()) {
			save();
		}
	}
	
	public void resetAction() {
		List<FormField> ffList = new LinkedList<>(formFieldList);
		for (FormField formField : ffList) {
			removeQuestion(formField);
		}
		addNewQuestionRow();
		
		naziv = null;
		opis = null;
		pocetak = null;
		kraj = null;
	}

	public void addQuestion() {
		addNewQuestionRow();
	}

	public void removeQuestion(FormField formField) {
		removeQuestionRow(formField);
	}

	protected void addNewQuestionRow() {
		FormField formField = createFormField();

		formFieldList.add(formField);

		DynaFormRow row = formModel.createRegularRow();
		row.addControl(formField, "question");
	}

	protected void removeQuestionRow(FormField formField) {
		numberOfQuestions--;
		formModel.removeRegularRow(formField.getIndex());
		formFieldList.remove(formField);

		reindexList();
	}

	protected boolean isValidFormData() {
		boolean formValid = true;

		if (getPocetak() == null) {
			messageDispatcher.error(Messages.getMessageFromPoslovnaPravilaValidation("qt.pocetak.notnull"));
			formValid = false;
		}
		if (getKraj() == null) {
			messageDispatcher.error(Messages.getMessageFromPoslovnaPravilaValidation("qt.kraj.notnull"));
			formValid = false;
		}
		if (formValid && getKraj().isBefore(getPocetak())) {
			messageDispatcher.error(Messages.getMessageFromPoslovnaPravilaValidation("qt.pocetakkraj.odnos"));
			formValid = false;
		}

		return formValid;
	}
	
	private void reindexList() {
		int index = 0;
		for (FormField ff : formFieldList) {
			ff.setIndex(index++);
		}
	}
	
	protected void populateModel() {
		reindexList();
		for (FormField formField : formFieldList) {
			numberOfQuestions++;
			DynaFormRow row = formModel.createRegularRow();
			row.addControl(formField, "question");
		}
	}

	public boolean isRenderedPonudjeniOdgovori(TQFormField formField) {
		return InputType.CHECKBOX.equals(formField.getInputType()) || InputType.RADIO.equals(formField.getInputType());
	}
	
	public boolean isRenderedSave() {
		return Boolean.TRUE.equals(edit);
	}
	
	public boolean isRenderedSubmitReset() {
		return edit == null;
	}
	
	public boolean isDisabledFields() {
		return Boolean.FALSE.equals(edit);
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDate pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDate getKraj() {
		return kraj;
	}

	public void setKraj(LocalDate kraj) {
		this.kraj = kraj;
	}

	public TestQuestionaire getTq() {
		return tq;
	}

	public void setTq(TestQuestionaire tq) {
		this.tq = tq;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public DynaFormModel getFormModel() {
		return formModel;
	}

	public List<FormField> getFormFieldList() {
		return formFieldList;
	}

}
