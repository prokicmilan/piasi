package rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class KSUlogaDao extends BaseEntityDao<KSUloga> {

	@Inject
	private EntityManager em;
	
	public KSUlogaDao() {
		super(KSUloga.class);
	}
	
	public List<KSUloga> findNonPrivileged() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KSUloga> criteriaQuery = cb.createQuery(getEntityClass());
		Root<KSUloga> root = criteriaQuery.from(getEntityClass());
		
		Predicate nonPrivileged = cb.equal(root.get("privileged"), Boolean.FALSE);
		
		criteriaQuery.select(root).where(nonPrivileged);
		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
}
