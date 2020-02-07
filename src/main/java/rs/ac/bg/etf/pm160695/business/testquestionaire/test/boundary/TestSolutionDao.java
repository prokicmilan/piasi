package rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary;

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
	
	public TestSolution saveSolution(TestSolution ts, KSKorisnik ulogovaniKorisnik) {
		ts.setKsKorisnik(ulogovaniKorisnik);
		return persistOrMerge(ts);
	}
	
	public List<TestSolution> getTestSolutionsForTest(Test t) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TestSolution> criteriaQuery = cb.createQuery(getEntityClass());
		Root<TestSolution> root = criteriaQuery.from(getEntityClass());
		
		Predicate test = cb.equal(root.get(TestSolution_.test), t);
		
		criteriaQuery.select(root).where(test);
		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	public boolean testAlreadySolvedByUser(Test t, KSKorisnik user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<TestSolution> root = criteriaQuery.from(getEntityClass());
		
		Selection<Long> countSelection;
		List<Predicate> predicates = new LinkedList<>();
		
		predicates.add(cb.equal(root.get(TestSolution_.test), t));
		predicates.add(cb.equal(root.get(TQSolution_.korisnik), user));
		
		countSelection = cb.count(root);
		
		criteriaQuery.select(countSelection).where(predicates.toArray(new Predicate[] {}));
		
		return em.createQuery(criteriaQuery).getSingleResult() != 0;
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
