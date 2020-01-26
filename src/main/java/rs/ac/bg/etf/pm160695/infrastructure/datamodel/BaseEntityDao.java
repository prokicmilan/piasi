package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public T find(Object key) {
		return getEntityManager().find(entityClass, key);
	}

	public List<T> filter(Map<String, String> filters) {
		CriteriaQuery<T> criteriaQuery = prepareCriteriaQuery(filters);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

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

	protected void evictEntityFromCache(T entity) {
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evict(entityClass, entity.getId());
	}

	protected void evictClassFromCache() {
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evict(entityClass);
	}

	private CriteriaQuery<T> prepareCriteriaQuery(Map<String, String> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);

		Predicate[] predicates = getPredicates(cb, root, filters);

		criteriaQuery.where(predicates);

		return criteriaQuery;
	}

	private Predicate[] getPredicates(CriteriaBuilder cb, Root<T> root, Map<String, String> filters) {
		List<Predicate> predicates = new LinkedList<>();

		for (Entry<String, String> entry : filters.entrySet()) {
			if (entry.getValue() != null && !entry.getValue().isBlank()) {
				String[] filter = entry.getKey().split("\\.", 2);
				String operator = filter[0];
				String attribute = filter[1];
				String value = entry.getValue().trim();

				switch (operator) {
					case "eq":
						predicates.add(cb.equal(getPath(root, attribute), value));
						break;
					case "like":
						predicates.add(cb.like(getPath(root, attribute), value + "%"));
						break;
					default:
						break;
				}
			}
		}

		return predicates.toArray(new Predicate[] {});
	}

	@SuppressWarnings("unchecked")
	private <E, R> Path<R> getPath(Path<E> root, String path) {
		String[] pathElements = path.split("\\.");
		Path<?> retVal = root;
		for (String pathEl : pathElements) {
			retVal = retVal.get(pathEl);
		}

		return (Path<R>) retVal;
	}

	protected void delete(T entity) {
		getEntityManager().remove(entity);
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

}
