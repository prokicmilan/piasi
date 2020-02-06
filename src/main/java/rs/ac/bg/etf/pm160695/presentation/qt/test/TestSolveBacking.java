package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestAnswer;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;
import rs.ac.bg.etf.pm160695.infrastructure.presentation.BaseBackingBean;
import rs.ac.bg.etf.pm160695.presentation.qt.TestAnswerData;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.InputType;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.utility.QuestionDataReader;

@Named
@ViewScoped
public class TestSolveBacking extends BaseBackingBean {

	private static final long serialVersionUID = -3167189535333365822L;
	
	@Inject
	private TestSolutionDao testSolutionDao;
	
	private Test test;
	
	private DynaFormModel formModel;
	
	public void initialize() {
		formModel = new DynaFormModel();
		List<TestQuestionFormField> tqff = QuestionDataReader.readTestQuestionFormFields(test.getTestQuestions());
		for (TestQuestionFormField question : tqff) {
			DynaFormRow row = formModel.createRegularRow();
			DynaFormLabel label = row.addLabel(question.getQuestion());
			
			List<String> answers = new LinkedList<>();
			if (InputType.RADIO.equals(question.getInputType()) || InputType.CHECKBOX.equals(question.getInputType())) {
				answers.addAll(Arrays.asList(question.getAnswers().split(",")));
				answers.add(question.getCorrectAnswer());
				Collections.shuffle(answers);
			}
			
			TestAnswerData data = new TestAnswerData();
			data.setOptions(answers);
			data.setQuestion(question.getTestQuestion());
			
			DynaFormControl control = row.addControl(data, question.getInputType().getLabel());
			label.setForControl(control);
		}
	}
	
	public void endTestAction() {
		logger.info("endTestAction()");
		
		List<TestAnswerData> answers = formModel.getControls().stream().map(DynaFormControl::getData).map(d -> (TestAnswerData) d).collect(Collectors.toList());
//		Set<TestAnswer> testAnswers = answers.stream().map(a -> {
//				TestAnswer ta = new TestAnswer();
//				ta.setQuestion(a.getQuestion());
//				if (a.getAnswers() != null && a.getAnswers().length > 0) {
//					ta.setAnswer(String.join(", ", a.getAnswers()));
//				}
//				else {
//					ta.setAnswer(a.getAnswer());
//				}
//				return ta;
//			}).collect(Collectors.toSet());
		
		Set<TestAnswer> testAnswers = new LinkedHashSet<>();
		for (TestAnswerData a : answers) {
			TestAnswer ta = new TestAnswer();
			ta.setQuestion(a.getQuestion());
			ta.setKsKorisnik(currentUserBean.getUlogovaniKorisnik());
			if (a.getAnswers() != null && a.getAnswers().length > 0) {
				ta.setAnswer(String.join(", ", a.getAnswers()));
			}
			else {
				ta.setAnswer(a.getAnswer());
			}
			testAnswers.add(ta);
		}
		
		TestSolution ts = new TestSolution();
		ts.setTest(test);
		ts.setKorisnik(currentUserBean.getUlogovaniKorisnik());
		ts.setAnswers(testAnswers);
		
		testSolutionDao.saveSolution(ts, currentUserBean.getUlogovaniKorisnik());
		
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
