package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class BaseEntityDao<T extends BaseEntity> {

	private Class<T> entityClass;

	@Inject
	private EntityManager em;

	public BaseEntityDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public List<T> findByParameter(String key, Object value) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClass);
		Root<T> root = query.from(entityClass);

		query.where(cb.equal(root.get(key), value));

		return em.createQuery(query).getResultList();
	}

	protected T persistOrMerge(T entity) {
		if (entity.getId() == null) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		return entity;
	}

	public void delete(T entity) {
		em.remove(entity);
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public EntityManager getEm() {
		return em;
	}

	protected List<T> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		q.from(entityClass);

		TypedQuery<T> query = em.createQuery(q);

		List<T> resList = query.getResultList();

		return resList;
	}
}
