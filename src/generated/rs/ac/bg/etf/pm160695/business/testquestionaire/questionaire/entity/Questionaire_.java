package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire_;

@Generated(value="Dali", date="2020-02-06T21:43:18.670+0100")
@StaticMetamodel(Questionaire.class)
public class Questionaire_ extends TestQuestionaire_ {
	public static volatile SingularAttribute<Questionaire, Boolean> anonymous;
	public static volatile SetAttribute<Questionaire, QuestionaireQuestion> questionaireQuestions;
}
