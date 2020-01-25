package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

public abstract class BaseEntityDao<T extends BaseEntity> {

	private Class<T> entityClass;

	public BaseEntityDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	protected List<T> findByParameter(SingularAttribute<T, ?> column, Object value) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);

		Predicate predicate = cb.equal(root.get(column), value);

		criteriaQuery.select(root).where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	protected Long executeCountQuery(SingularAttribute<T, ?> column, Object value, boolean distinct) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(entityClass);

		Selection<Long> countSelection;
		Path<?> columnPath = root.get(column);
		Predicate equalsPredicate = cb.equal(columnPath, value);

		if (distinct) {
			countSelection = cb.countDistinct(columnPath);
		} else {
			countSelection = cb.count(columnPath);
		}

		criteriaQuery.select(countSelection).where(equalsPredicate);

		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}

	protected T persistOrMerge(T entity) {
		if (entity.getId() == null) {
			getEntityManager().persist(entity);
		} else {
			getEntityManager().merge(entity);
		}
		return entity;
	}

	protected void delete(T entity) {
		getEntityManager().remove(entity);
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

}
