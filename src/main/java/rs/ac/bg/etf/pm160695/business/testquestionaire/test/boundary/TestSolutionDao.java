package rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution_;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class TestSolutionDao extends BaseEntityDao<TestSolution> {

	@Inject
	private EntityManager em;
	
	public TestSolutionDao() {
		super(TestSolution.class);
	}
	
	public void saveSolution(TestSolution ts, KSKorisnik ulogovaniKorisnik) {
		ts.setKsKorisnik(ulogovaniKorisnik);
		persistOrMerge(ts);
	}
	
	public List<TestSolution> getTestSolutionsForTest(Test t) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TestSolution> criteriaQuery = cb.createQuery(getEntityClass());
		Root<TestSolution> root = criteriaQuery.from(getEntityClass());
		
		Predicate test = cb.equal(root.get(TestSolution_.test), t);
		
		criteriaQuery.select(root).where(test);
		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
