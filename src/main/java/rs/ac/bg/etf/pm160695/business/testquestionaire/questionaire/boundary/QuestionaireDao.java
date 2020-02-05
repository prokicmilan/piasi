package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.boundary.TQDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire_;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.TQFormField;

@Stateless
public class QuestionaireDao extends TQDao<Questionaire> {

	@Inject
	private EntityManager em;

	public QuestionaireDao() {
		super(Questionaire.class);
	}
	
	public List<Questionaire> search(String naziv, String opis, LocalDate datumOd, LocalDate datumDo, Boolean anonymous) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Questionaire> criteriaQuery = cb.createQuery(getEntityClass());
		Root<Questionaire> root = criteriaQuery.from(getEntityClass());
		
		List<Predicate> predicateList = getPredicates(cb, root, naziv, opis, datumOd, datumDo);
		if (anonymous != null) {
			predicateList.add(cb.equal(root.get(Questionaire_.anonymous), anonymous));
		}
		List<Questionaire> retList = em.createQuery(criteriaQuery).getResultList();
		return retList;
		
	}

	public CommonErrors saveQuestionaire(@NotBlank String naziv, @NotBlank String opis, @NotNull LocalDate datumOd,
			@NotNull LocalDate datumDo, @NotNull Boolean anonymous, @NotBlank String questionsData,
			@NotNull KSKorisnik ulogovaniKorisnik) {

		Questionaire questionaire = new Questionaire();
		questionaire.setNaziv(naziv);
		questionaire.setOpis(opis);
		questionaire.setDatumOd(datumOd);
		questionaire.setDatumDo(datumDo);
		questionaire.setAnonymous(anonymous);
		questionaire.setQuestionsData(questionsData);
		questionaire.setKsKorisnik(ulogovaniKorisnik);

		persistOrMerge(questionaire);

		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
