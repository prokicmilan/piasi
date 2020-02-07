package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
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
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
