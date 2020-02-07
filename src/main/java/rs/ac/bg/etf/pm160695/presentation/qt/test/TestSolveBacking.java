package rs.ac.bg.etf.pm160695.presentation.qt.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.annotation.RequestMap;
import javax.faces.annotation.ViewMap;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestAnswer;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQSolveBacking;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.data.TestAnswerData;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.InputType;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.utility.QuestionDataReader;

@Named
@ViewScoped
public class TestSolveBacking extends TQSolveBacking {

	private static final long serialVersionUID = -3167189535333365822L;
	
	@Inject
	private TestSolutionDao testSolutionDao;
	
	
	
	@Override
	public void populateModel() {
		List<TestQuestionFormField> tqff = QuestionDataReader.readTestQuestionFormFields(((Test)tq).getTestQuestions());
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
	
	@Override
	public String submitAnswersAction() {
		List<TestAnswerData> answers = formModel.getControls().stream().map(DynaFormControl::getData).map(d -> (TestAnswerData) d).collect(Collectors.toList());
		Set<TestAnswer> testAnswers = answers.stream().map(a -> {
				TestAnswer ta = new TestAnswer();
				ta.setQuestion(a.getQuestion());
				ta.setKsKorisnik(currentUserBean.getUlogovaniKorisnik());
				if (a.getAnswers() != null && a.getAnswers().length > 0) {
					ta.setAnswer(String.join(", ", a.getAnswers()));
				}
				else {
					ta.setAnswer(a.getAnswer());
				}
				return ta;
			}).collect(Collectors.toSet());
		
		TestSolution ts = new TestSolution();
		ts.setTest((Test) tq);
		ts.setKorisnik(currentUserBean.getUlogovaniKorisnik());
		ts.setAnswers(testAnswers);
		
		ts = testSolutionDao.saveSolution(ts, currentUserBean.getUlogovaniKorisnik());
		logger.info("tsId = " + ts.getId());
		
		return "testRezultatiDetalji";
	}

}
