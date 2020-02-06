package rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;
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
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
