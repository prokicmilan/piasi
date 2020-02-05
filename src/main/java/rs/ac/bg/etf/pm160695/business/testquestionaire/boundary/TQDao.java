package rs.ac.bg.etf.pm160695.business.testquestionaire.boundary;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Question;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire_;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TQFormField;

public abstract class TQDao<T extends TestQuestionaire> extends BaseEntityDao<T> {

	public TQDao(Class<T> entityClass) {
		super(entityClass);
	}
	
	protected abstract Set<? extends Question> createQuestions(T entity, List<? extends TQFormField> questions, KSKorisnik ulogovaniKorisnik);
	
	public abstract T save(T entity, List<? extends TQFormField> questions, KSKorisnik ulogovaniKorisnik);
	
	protected <X extends TestQuestionaire> List<Predicate> getPredicates(CriteriaBuilder cb, Root<X> root, String naziv, String opis, LocalDate datumOd, LocalDate datumDo) {
		List<Predicate> predicateList = new LinkedList<>();
		
		if (naziv != null && !naziv.isBlank()) {
			predicateList.add(cb.like(root.get(TestQuestionaire_.naziv), "%" + naziv + "%"));
		}
		if (opis != null && !opis.isBlank()) {
			predicateList.add(cb.like(root.get(TestQuestionaire_.opis), "%" + opis + "%"));
		}
		if (datumOd != null) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(TestQuestionaire_.datumOd), datumOd));
		}
		if (datumDo != null) {
			predicateList.add(cb.lessThanOrEqualTo(root.get(TestQuestionaire_.datumDo), datumDo));
		}
		
		return predicateList;
	}
	
}
