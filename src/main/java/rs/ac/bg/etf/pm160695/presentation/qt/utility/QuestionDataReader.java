package rs.ac.bg.etf.pm160695.presentation.qt.utility;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireQuestion;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestQuestion;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.QuestionaireQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TestQuestionFormField;

public class QuestionDataReader {

	public static List<TestQuestionFormField> readTestQuestionFormFields(Set<TestQuestion> testQuestions) {
		return testQuestions.stream().map(testQuestion -> {
			TestQuestionFormField tqff = new TestQuestionFormField();
			tqff.setInputType(testQuestion.getInputType());
			tqff.setQuestion(testQuestion.getQuestion());
			tqff.setCorrectAnswer(testQuestion.getCorrectAnswer());
			tqff.setAnswers(testQuestion.getAnswers());
			tqff.setTestQuestion(testQuestion);
			
			return tqff;
		}).collect(Collectors.toList());
	}
	
	public static List<QuestionaireQuestionFormField> readQuestionaireQuestionFormFields(Set<QuestionaireQuestion> questionaireQuestions) {
		return questionaireQuestions.stream().map(questionaireQuestion -> {
			QuestionaireQuestionFormField qqff = new QuestionaireQuestionFormField();
			qqff.setInputType(questionaireQuestion.getInputType());
			qqff.setQuestion(questionaireQuestion.getQuestion());
			qqff.setAnswers(questionaireQuestion.getAnswers());
			return qqff;
		}).collect(Collectors.toList());
	}
	
	private QuestionDataReader() {}
}
