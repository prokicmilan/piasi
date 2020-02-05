package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary;

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
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireQuestion;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire_;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.QuestionaireQuestionFormField;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TQFormField;

@Stateless
public class QuestionaireDao extends TQDao<Questionaire> {

	@Inject
	private EntityManager em;

	public QuestionaireDao() {
		super(Questionaire.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Questionaire save(Questionaire questionaire, List<? extends TQFormField> questions, KSKorisnik ulogovaniKorisnik) {
		questionaire.setKsKorisnik(ulogovaniKorisnik);
		questionaire.setQuestionaireQuestions((Set<QuestionaireQuestion>) createQuestions(questionaire, questions, ulogovaniKorisnik));
		
		return persistOrMerge(questionaire);
	}
	
	public List<Questionaire> search(String naziv, String opis, LocalDate datumOd, LocalDate datumDo, Boolean anonymous) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Questionaire> criteriaQuery = cb.createQuery(getEntityClass());
		Root<Questionaire> root = criteriaQuery.from(getEntityClass());
		
		List<Predicate> predicateList = getPredicates(cb, root, naziv, opis, datumOd, datumDo);
		if (anonymous != null) {
			predicateList.add(cb.equal(root.get(Questionaire_.anonymous), anonymous));
		}
		List<Questionaire> retList = em.createQuery(criteriaQuery).getResultList();
		return retList;
		
	}

	@SuppressWarnings("unchecked")
	public CommonErrors saveQuestionaire(@NotBlank String naziv, @NotBlank String opis, @NotNull LocalDate datumOd,
			@NotNull LocalDate datumDo, @NotNull Boolean anonymous, @NotEmpty List<QuestionaireQuestionFormField> questions,
			@NotNull KSKorisnik ulogovaniKorisnik) {

		Questionaire questionaire = new Questionaire();
		questionaire.setNaziv(naziv);
		questionaire.setOpis(opis);
		questionaire.setDatumOd(datumOd);
		questionaire.setDatumDo(datumDo);
		questionaire.setAnonymous(anonymous);
		questionaire.setKsKorisnik(ulogovaniKorisnik);
		questionaire.setQuestionaireQuestions((Set<QuestionaireQuestion>) createQuestions(questionaire, questions, ulogovaniKorisnik));
		
		persistOrMerge(questionaire);

		return null;
	}
	
	@Override
	protected Set<? extends Question> createQuestions(Questionaire questionaire, List<? extends TQFormField> questions, KSKorisnik ulogovaniKorisnik) {
		Set<QuestionaireQuestion> questionaireQuestions = new LinkedHashSet<>();
		for (TQFormField tqff : questions) {
			QuestionaireQuestion tq = new QuestionaireQuestion();
			tq.setInputType(tqff.getInputType());
			tq.setQuestionaire(questionaire);
			tq.setQuestion(tqff.getQuestion());
			tq.setAnswers(tqff.getAnswers());
			tq.setKsKorisnik(ulogovaniKorisnik);
			questionaireQuestions.add(tq);
		}
		
		return questionaireQuestions;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
