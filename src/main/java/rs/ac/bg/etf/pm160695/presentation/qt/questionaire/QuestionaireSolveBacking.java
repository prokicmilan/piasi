package rs.ac.bg.etf.pm160695.presentation.qt.questionaire;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireAnswer;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireSolution;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.backing.TQSolveBacking;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.data.QuestionaireAnswerData;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.InputType;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.QuestionaireQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.utility.QuestionDataReader;

@Named
@ViewScoped
public class QuestionaireSolveBacking extends TQSolveBacking {

	private static final long serialVersionUID = 4754144933846162214L;

	@Inject
	private QuestionaireSolutionDao questionaireSolutionDao;
	
	@Override
	public void populateModel() {
		List<QuestionaireQuestionFormField> qqff = QuestionDataReader.readQuestionaireQuestionFormFields(((Questionaire) tq).getQuestionaireQuestions());
		for (QuestionaireQuestionFormField question : qqff) {
			DynaFormRow row = formModel.createRegularRow();
			DynaFormLabel label = row.addLabel(question.getQuestion());
			
			List<String> answers = new LinkedList<>();
			if (InputType.RADIO.equals(question.getInputType()) || InputType.CHECKBOX.equals(question.getInputType())) {
				answers.addAll(Arrays.asList(question.getAnswers().split(", ")));
				Collections.shuffle(answers);
			}
			
			QuestionaireAnswerData data = new QuestionaireAnswerData();
			data.setOptions(answers);
			data.setQuestion(question.getQuestionaireQuestion());
			
			DynaFormControl control = row.addControl(data, question.getInputType().getLabel());
			label.setForControl(control);
		}
	}
	
	@Override
	public String submitAnswersAction() {
		List<QuestionaireAnswerData> answers = formModel.getControls().stream().map(DynaFormControl::getData).map(d -> (QuestionaireAnswerData) d).collect(Collectors.toList());
		Set<QuestionaireAnswer> questionaireAnswers = answers.stream().map(a -> {
			QuestionaireAnswer qa = new QuestionaireAnswer();
			qa.setQuestion(a.getQuestion());
			qa.setKsKorisnik(currentUserBean.getUlogovaniKorisnik());
			if (a.getAnswers() != null && a.getAnswers().length > 0) {
				qa.setAnswer(String.join(", ", a.getAnswers()));
			}
			else {
				qa.setAnswer(a.getAnswer());
			}
			return qa;
		}).collect(Collectors.toSet());
		
		QuestionaireSolution qs = new QuestionaireSolution();
		qs.setQuestionaire((Questionaire) tq);
		qs.setKorisnik(currentUserBean.getUlogovaniKorisnik());
		qs.setAnswers(questionaireAnswers);
		
		questionaireSolutionDao.saveSolution(qs, currentUserBean.getUlogovaniKorisnik());
		
		// TODO: idemo na stranicu na kojoj prikazujemo rezultate
		return "questionaireRezultatiDetalji?faces-redirect=true&includeViewParams=true";
	}

}
