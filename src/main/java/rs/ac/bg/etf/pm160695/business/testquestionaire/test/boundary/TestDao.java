package rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.boundary.TQDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Question;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestQuestion;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TQFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TestQuestionFormField;

@Stateless
public class TestDao extends TQDao<Test> {

	@Inject
	private EntityManager em;

	public TestDao() {
		super(Test.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Test save(Test test, List<? extends TQFormField> questions, KSKorisnik ulogovaniKorisnik) {
		test.setKsKorisnik(ulogovaniKorisnik);
		test.setTestQuestions((Set<TestQuestion>) createQuestions(test, questions, ulogovaniKorisnik));
		
		return persistOrMerge(test);
	}
	
	public List<Test> search(String naziv, String opis, LocalDate datumOd, LocalDate datumDo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Test> criteriaQuery = cb.createQuery(getEntityClass());
		Root<Test> root = criteriaQuery.from(getEntityClass());
		
		List<Predicate> predicateList = getPredicates(cb, root, naziv, opis, datumOd, datumDo);
		
		Predicate[] predicates = predicateList.toArray(new Predicate[] {});
		
		criteriaQuery.select(root).where(predicates);
 		
		return em.createQuery(criteriaQuery).getResultList();
	}

	@SuppressWarnings("unchecked")
	public CommonErrors saveTest(@NotBlank String naziv, @NotBlank String opis, @NotNull LocalDate datumOd,
			@NotNull LocalDate datumDo, @NotNull Integer trajanje, @NotEmpty List<TestQuestionFormField> questions,
			@NotNull KSKorisnik ulogovaniKorisnik) {

		Test test = new Test();
		test.setNaziv(naziv);
		test.setOpis(opis);
		test.setDatumOd(datumOd);
		test.setDatumDo(datumDo);
		test.setTrajanje(trajanje);
		test.setKsKorisnik(ulogovaniKorisnik);
		test.setTestQuestions((Set<TestQuestion>) createQuestions(test, questions, ulogovaniKorisnik));

		persistOrMerge(test);

		return null;
	}
	
	@Override
	protected Set<? extends Question> createQuestions(Test test, List<? extends TQFormField> questions, KSKorisnik ulogovaniKorisnik) {
		Set<TestQuestion> testQuestions = new LinkedHashSet<>();
		for (TQFormField tqff : questions) {
			TestQuestion tq = new TestQuestion();
			tq.setInputType(tqff.getInputType());
			tq.setTest(test);
			tq.setQuestion(tqff.getQuestion());
			tq.setCorrectAnswer(((TestQuestionFormField) tqff).getCorrectAnswer());
			tq.setAnswers(tqff.getAnswers());
			tq.setKsKorisnik(ulogovaniKorisnik);
			testQuestions.add(tq);
		}
		
		return testQuestions;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}


}
