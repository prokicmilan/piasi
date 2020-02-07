package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution_;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireSolution;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireSolution_;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class QuestionaireSolutionDao extends BaseEntityDao<QuestionaireSolution> {

	@Inject
	private EntityManager em;
	
	public QuestionaireSolutionDao() {
		super(QuestionaireSolution.class);
	}
	
	public QuestionaireSolution saveSolution(QuestionaireSolution qs, KSKorisnik ulogovaniKorisnik) {
		qs.setKsKorisnik(ulogovaniKorisnik);
		return persistOrMerge(qs);
	}
	
	public List<QuestionaireSolution> getQuestionaireSolutionsForQuestionaire(Questionaire q) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<QuestionaireSolution> criteriaQuery = cb.createQuery(getEntityClass());
		Root<QuestionaireSolution> root = criteriaQuery.from(getEntityClass());
		
		Predicate questionaire = cb.equal(root.get(QuestionaireSolution_.questionaire), q);

		criteriaQuery.select(root).where(questionaire);
		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	public boolean questionaireAlreadySolvedByUser(Questionaire q, KSKorisnik user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<QuestionaireSolution> root = criteriaQuery.from(getEntityClass());
		
		Selection<Long> countSelection = cb.count(root);
		List<Predicate> predicates = new LinkedList<>();
		
		predicates.add(cb.equal(root.get(QuestionaireSolution_.questionaire), q));
		predicates.add(cb.equal(root.get(TQSolution_.korisnik), user));
		
		criteriaQuery.select(countSelection).where(predicates.toArray(new Predicate[] {}));
		
		return em.createQuery(criteriaQuery).getSingleResult() != 0;
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
