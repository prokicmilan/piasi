package rs.ac.bg.etf.pm160695.presentation.qt;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.FormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.InputType;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQFormField;
import rs.ac.bg.etf.pm160695.infrastructure.messaging.Messages;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;
import rs.ac.bg.etf.pm160695.presentation.qt.questionaire.QuestionaireCreationBacking;

public abstract class TQCreationBacking extends BaseBackingBean {

	private static final long serialVersionUID = 1785152636931133728L;

	protected DynaFormModel formModel;
	protected List<FormField> formFieldList = new LinkedList<>();

	protected int numberOfQuestions = 0;

	protected String naziv;
	protected String opis;
	protected LocalDate pocetak;
	protected LocalDate kraj;

	protected void init() {
		formModel = new DynaFormModel();
	}

	public abstract void submitForm();

	public void addQuestion() {
		addNewQuestionRow();
	}

	public void removeQuestion(FormField formField) {
		removeQuestionRow(formField);
	}

	protected abstract FormField createFormField();

	protected void addNewQuestionRow() {
		FormField formField = createFormField();
		DynaFormRow row = formModel.createRegularRow();

		formFieldList.add(formField);

		row.addControl(formField, "question");
	}

	protected void removeQuestionRow(FormField formField) {
		numberOfQuestions--;
		formModel.removeRegularRow(formField.getIndex());
		formFieldList.remove(formField);

		int index = 0;
		for (FormField ff : formFieldList) {
			ff.setIndex(index++);
		}
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

	public boolean isRenderedPonudjeniOdgovori(TQFormField formField) {
		return InputType.CHECKBOX.equals(formField.getInputType()) || InputType.RADIO.equals(formField.getInputType());
	}
	
	public abstract boolean isRenderedTrajanje();
	
	public boolean isRenderedAnonimno() {
		return (this instanceof QuestionaireCreationBacking);
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

	public DynaFormModel getFormModel() {
		return formModel;
	}

	public List<FormField> getFormFieldList() {
		return formFieldList;
	}

}
