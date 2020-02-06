package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.Question_;

@Generated(value="Dali", date="2020-02-06T21:43:18.752+0100")
@StaticMetamodel(TestQuestion.class)
public class TestQuestion_ extends Question_ {
	public static volatile SingularAttribute<TestQuestion, String> correctAnswer;
	public static volatile SingularAttribute<TestQuestion, Test> test;
}
