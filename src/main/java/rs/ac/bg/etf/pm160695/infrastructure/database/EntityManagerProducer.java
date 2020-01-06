package rs.ac.bg.etf.pm160695.infrastructure.database;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	@Produces
	@PersistenceContext(unitName = "piasiPU")
	private EntityManager em;
	
}
