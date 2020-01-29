package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.quizquestionaire.entity.InputType;
import rs.ac.bg.etf.pm160695.business.quizquestionaire.entity.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.infrastructure.messaging.Labels;
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
		List<TestQuestionFormField> questions = new LinkedList<>(); 
		
		questions = testFormModel.getControls()
							     .stream()
							     .map(DynaFormControl::getData) 												// dohvatam data sa kontrole
							     .map(d -> (TestQuestionFormField) d) 											// castujem u TestQuestionFormField
							     .collect(Collectors.groupingBy(TestQuestionFormField::getName))				// mapiram po imenima
							     .entrySet()																	
							     .stream()
							     .map(e -> {																	// za svaki entry u mapi
							    	 TestQuestionFormField formField = new TestQuestionFormField(e.getKey());  
							    	 String value = e.getValue().stream()										// dohvatam njegov value (ako nije null)
							    			 					.filter(f -> f.getValue() != null &&
							    			 								 !f.getValue().isBlank())
							    			 					.collect(Collectors.toList())
							    			 					.get(0)
							    			 					.getValue();
							    	 formField.setValue(value);
							    	 InputType inputType = e.getValue().stream()								// dohvatam njegov inputType (ako nije null)
							    			 						   .filter(f -> f.getInputType() != null)
							    			 						   .collect(Collectors.toList())
							    			 						   .get(0)
							    			 						   .getInputType();
							    	 formField.setInputType(inputType);
							    	 
							    	 return formField;															// mapiram entry u jedan objekat
							     })
							     .collect(Collectors.toList());													// skupljam sve u listu
		questions.forEach(q -> logger.info(q.getName() + ":" + q.getValue() + ":" + q.getInputType()));
	}
	
	public void addQuestion() {
		addNewQuestionRow();
	}
	
	private void addNewQuestionRow() {
		DynaFormRow row = testFormModel.createRegularRow();
		
		DynaFormLabel label = row.addLabel(Labels.getLabel("label.test.tekstPitanja"));
		DynaFormControl control = row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions), "input");
		label.setForControl(control);
		
		label = row.addLabel(Labels.getLabel("label.test.tipPitanja"));
		control = row.addControl(new TestQuestionFormField("pitanje " + numberOfQuestions), "select-one");
		
		numberOfQuestions++;
	}

	public DynaFormModel getTestFormModel() {
		return testFormModel;
	}
	
}
