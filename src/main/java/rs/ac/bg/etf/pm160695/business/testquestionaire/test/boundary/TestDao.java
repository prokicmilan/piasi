package rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary;

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
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.service.TestQuestionaireService;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntityDao;
import rs.ac.bg.etf.pm160695.infrastructure.validation.CommonErrors;

@Stateless
public class TestDao extends BaseEntityDao<Test> {

	@Inject
	private EntityManager em;

	@Inject
	private TestQuestionaireService tqService;

	public TestDao() {
		super(Test.class);
	}

	public CommonErrors saveTest(@NotBlank String naziv, @NotBlank String opis, @NotNull LocalDate datumOd,
			@NotNull LocalDate datumDo, @NotNull Integer trajanje, @NotEmpty List<TestQuestionFormField> questions,
			@NotNull KSKorisnik ulogovaniKorisnik) {
		JsonValue questionsJsonData = tqService.generateQuestionJsonData(questions);

		Test test = new Test();
		test.setNaziv(naziv);
		test.setOpis(opis);
		test.setDatumOd(datumOd);
		test.setDatumDo(datumDo);
		test.setTrajanje(trajanje);
		test.setQuestionsData(questionsJsonData.toString());
		test.setKsKorisnik(ulogovaniKorisnik);

		persistOrMerge(test);

		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
