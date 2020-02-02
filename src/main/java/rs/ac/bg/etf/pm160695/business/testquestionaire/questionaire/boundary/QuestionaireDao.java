package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.business.testquestionaire.service.TestQuestionaireService;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;

@Stateless
public class QuestionaireDao extends BaseEntityDao<Questionaire> {

	@Inject
	private EntityManager em;

	@Inject
	private TestQuestionaireService tqService;

	public QuestionaireDao() {
		super(Questionaire.class);
	}

	public CommonErrors saveQuestionaire(@NotBlank String naziv, @NotBlank String opis, @NotNull LocalDate datumOd,
			@NotNull LocalDate datumDo, @NotNull Boolean anonymous, @NotEmpty List<? extends TQFormField> questions,
			@NotNull KSKorisnik ulogovaniKorisnik) {

		JsonValue questionsJsonData = tqService.generateQuestionJsonData(questions);

		Questionaire questionaire = new Questionaire();
		questionaire.setNaziv(naziv);
		questionaire.setOpis(opis);
		questionaire.setDatumOd(datumOd);
		questionaire.setDatumDo(datumDo);
		questionaire.setAnonymous(anonymous);
		questionaire.setQuestionsData(questionsJsonData.toString());
		questionaire.setKsKorisnik(ulogovaniKorisnik);

		persistOrMerge(questionaire);

		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
