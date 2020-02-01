package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.quizquestionaire.entity.FormField;
import rs.ac.bg.etf.pm160695.business.quizquestionaire.entity.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

@Named
@ViewScoped
public class TestCreationBacking extends BaseBackingBean {

	private static final long serialVersionUID = -1922383613850915029L;

	private DynaFormModel testFormModel;
	private List<FormField> formFieldList = new LinkedList<>();

	int numberOfQuestions = 0;

	private String naziv;
	private String opis;
	private LocalDate pocetak;
	private LocalDate kraj;
	private Integer trajanje;

	@PostConstruct
	public void init() {
		testFormModel = new DynaFormModel();
		addNewQuestionRow();
	}

	public void submitForm() {
		logger.info("submitForm");
		List<TestQuestionFormField> questions = new LinkedList<>();

		questions = testFormModel.getControls().stream().map(DynaFormControl::getData)
				.map(d -> (TestQuestionFormField) d).collect(Collectors.toList());

		questions.forEach(q -> logger.info(q.getName() + ":" + q.getValue() + ":" + q.getInputType()));

		/* CUT HERE - OVO IDE U BIZNIS */
		// pretvaram sve u json
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		for (TestQuestionFormField testQuestionFormField : questions) {
			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
			jsonObjectBuilder.add("Question", testQuestionFormField.getValue());
			jsonObjectBuilder.add("Type", testQuestionFormField.getInputType().getValue());
			jsonObjectBuilder.add("Answers", testQuestionFormField.getAnswers());
			jsonArrayBuilder.add(jsonObjectBuilder);
		}
		JsonArray jsonArray = jsonArrayBuilder.build();
		logger.info("generisan niz: " + jsonArray.toString());
	}

	public void addQuestion() {
		addNewQuestionRow();
	}

	public void removeQuestion(TestQuestionFormField formField) {
		removeQuestionRow(formField);
	}

	public void onInputTypeChange(TestQuestionFormField formField) {
		if (formField != null && formField.getInputType() != null) {
			logger.info("selected: " + formField.getInputType().getLabel());
		}
	}

	private void addNewQuestionRow() {
		DynaFormRow row = testFormModel.createRegularRow();

		logger.info("adding row " + numberOfQuestions);

		FormField formField = new TestQuestionFormField("pitanje " + numberOfQuestions, numberOfQuestions);
		numberOfQuestions++;
		formFieldList.add(formField);

		row.addControl(formField, "question");
	}

	private void removeQuestionRow(TestQuestionFormField formField) {
		logger.info("removing row " + formField.getIndex() + " out of " + numberOfQuestions);
		numberOfQuestions--;
		testFormModel.removeRegularRow(formField.getIndex());
		formFieldList.remove(formField);

		int index = 0;
		for (FormField ff : formFieldList) {
			TestQuestionFormField testQuestionFormField = (TestQuestionFormField) ff;
			testQuestionFormField.setIndex(index++);
		}
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

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public DynaFormModel getTestFormModel() {
		return testFormModel;
	}

}
