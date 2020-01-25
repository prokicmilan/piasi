package rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUlogaTip;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga_;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class KSUlogaDao extends BaseEntityDao<KSUloga> {

	@Inject
	private EntityManager em;

	public KSUlogaDao() {
		super(KSUloga.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<KSUloga> findOsnovne() {
		return findByParameter(KSUloga_.ulogaTip, KSUlogaTip.OSNOVNA.getValue());
	}

	public List<KSUloga> findFunkcionalne() {
		return findByParameter(KSUloga_.ulogaTip, KSUlogaTip.FUNKCIONALNA.getValue());
	}

	public List<KSUloga> findSistemske() {
		return findByParameter(KSUloga_.ulogaTip, KSUlogaTip.SISTEMSKA.getValue());
	}

}
