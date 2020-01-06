package rs.ac.bg.etf.pm160695.business.korisnickisistem.boundary;

import java.util.List;

import javax.ejb.Stateless;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSUloga;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;

@Stateless
public class KSUlogaDao extends BaseEntityDao<KSUloga> {

	public KSUlogaDao() {
		super(KSUloga.class);
	}

	public List<KSUloga> findAll() {
		return super.findAll();
	}

}
