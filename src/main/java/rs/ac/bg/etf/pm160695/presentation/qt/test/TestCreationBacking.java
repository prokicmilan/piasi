package rs.ac.bg.etf.pm160695.presentation.qt.test;

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

import rs.ac.bg.etf.pm160695.business.quizquestionaire.entity.InputType;
import rs.ac.bg.etf.pm160695.business.quizquestionaire.entity.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;

@Named
@ViewScoped
public class TestCreationBacking extends BaseBackingBean {

	private static final long serialVersionUID = -1922383613850915029L;
	
	private DynaFormModel testFormModel;
	
	int numberOfQuestions;
	
	@PostConstruct
	public void init() {
		testFormModel = new DynaFormModel();
		addNewQuestionRow();
	}
	
	public void submitForm() {
		logger.info("submitForm");
		List<TestQuestionFormField> questions = new LinkedList<>(); 
		
		questions = testFormModel.getControls()
								 .stream()
								 .map(DynaFormControl::getData)
								 .map(d -> (TestQuestionFormField) d)
								 .collect(Collectors.toList());
		
//		questions = testFormModel.getControls()
//							     .stream()
//							     .map(DynaFormControl::getData) 												// dohvatam data sa kontrole
//							     .map(d -> (TestQuestionFormField) d) 											// castujem u TestQuestionFormField
//							     .collect(Collectors.groupingBy(TestQuestionFormField::getName))				// mapiram po imenima
//							     .entrySet()																	
//							     .stream()
//							     .map(e -> {																	// za svaki entry u mapi
//							    	 TestQuestionFormField formField = new TestQuestionFormField(e.getKey());  
//							    	 String value = e.getValue().stream()										// dohvatam njegov value (ako nije null)
//							    			 					.filter(f -> f.getValue() != null &&
//							    			 								 !f.getValue().isBlank())
//							    			 					.collect(Collectors.toList())
//							    			 					.get(0)
//							    			 					.getValue();
//							    	 formField.setValue(value);
//							    	 InputType inputType = e.getValue().stream()								// dohvatam njegov inputType (ako nije null)
//							    			 						   .filter(f -> f.getInputType() != null)
//							    			 						   .collect(Collectors.toList())
//							    			 						   .get(0)
//							    			 						   .getInputType();
//							    	 formField.setInputType(inputType);
//							    	 String answers = e.getValue().stream()
//							    			 					  .filter(f -> f.getAnswers() != null &&
//							    			 							  	   !f.getAnswers().isBlank())
//							    			 					  .collect(Collectors.toList())
//							    			 					  .get(0)
//							    			 					  .getAnswers();
//							    	 formField.setAnswers(answers);
//							    	 
//							    	 return formField;															// mapiram entry u jedan objekat
//							     })
//							     .collect(Collectors.toList());													// skupljam sve u listu
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
	
	public void removeQuestion(int index) {
		removeLastQuestionRow(index);
	}
	
	public void onInputTypeChange(TestQuestionFormField formField) {
		if (formField != null && formField.getInputType() != null) {
			logger.info("selected: " + formField.getInputType().getLabel());
		}
	}
	
	private void addNewQuestionRow() {
		logger.info("adding new row");
		DynaFormRow row = testFormModel.createRegularRow();
		
		row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions, numberOfQuestions), "question");
		
//		DynaFormLabel label = row.addLabel(Labels.getLabel("label.test.tekstPitanja"));
//		DynaFormControl control = row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions, numberOfQuestions), "input");
//		label.setForControl(control);
//		
//		label = row.addLabel(Labels.getLabel("label.test.tipPitanja"));
//		control = row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions, numberOfQuestions), "select-one");
//		label.setForControl(control);
//		
//		label = row.addLabel(Labels.getLabel("label.test.tacanOdgovor"));
//		control = row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions, numberOfQuestions), "text-area");
//		label.setForControl(control);
//		
//		label = row.addLabel(Labels.getLabel("label.test.ponudjeniOdgovori"));
//		control = row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions, numberOfQuestions), "text-area-answers");
//		label.setForControl(control);
		
		numberOfQuestions++;
	}
	
	private void removeLastQuestionRow(int index) {
		testFormModel.removeRegularRow(index);
		numberOfQuestions--;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public DynaFormModel getTestFormModel() {
		return testFormModel;
	}
	
}
