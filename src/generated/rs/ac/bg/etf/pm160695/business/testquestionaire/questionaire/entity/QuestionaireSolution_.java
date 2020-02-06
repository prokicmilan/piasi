package rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution_;

@Generated(value="Dali", date="2020-02-06T21:43:18.721+0100")
@StaticMetamodel(QuestionaireSolution.class)
public class QuestionaireSolution_ extends TQSolution_ {
	public static volatile SingularAttribute<QuestionaireSolution, Questionaire> questionaire;
	public static volatile SetAttribute<QuestionaireSolution, QuestionaireAnswer> answers;
}
