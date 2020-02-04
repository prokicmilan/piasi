package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.InputType;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;
import rs.ac.bg.etf.pm160695.presentation.qt.TestSolveData;

@Named
@ViewScoped
public class TestSolveBacking extends BaseBackingBean {

	private static final long serialVersionUID = -3167189535333365822L;
	
	private Test test;
	
	private DynaFormModel formModel;
	
	public void initialize() {
		formModel = new DynaFormModel();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<TestQuestionFormField> tqff = objectMapper.readValue(test.getQuestionsData(), new TypeReference<List<TestQuestionFormField>>() {});
			for (TestQuestionFormField question : tqff) {
				DynaFormRow row = formModel.createRegularRow();
				DynaFormLabel label = row.addLabel(question.getQuestion());
				
				List<String> answers = new LinkedList<>();
				if (InputType.RADIO.equals(question.getInputType()) || InputType.CHECKBOX.equals(question.getInputType())) {
					answers.addAll(Arrays.asList(question.getAnswers().split(",")));
				}
				
				TestSolveData data = new TestSolveData();
				data.setOptions(answers);
				
				DynaFormControl control = row.addControl(data, question.getInputType().getLabel());
				label.setForControl(control);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public DynaFormModel getFormModel() {
		return formModel;
	}
	
}
