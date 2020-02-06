package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireSolution;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class QuestionaireSolutionDao extends BaseEntityDao<QuestionaireSolution> {

	@Inject
	private EntityManager em;
	
	public QuestionaireSolutionDao() {
		super(QuestionaireSolution.class);
	}
	
	public void saveSolution(QuestionaireSolution qs, KSKorisnik ulogovaniKorisnik) {
		qs.setKsKorisnik(ulogovaniKorisnik);
		persistOrMerge(qs);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
