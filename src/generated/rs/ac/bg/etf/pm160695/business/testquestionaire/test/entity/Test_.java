package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionaire_;

@Generated(value="Dali", date="2020-02-06T21:43:18.724+0100")
@StaticMetamodel(Test.class)
public class Test_ extends TestQuestionaire_ {
	public static volatile SingularAttribute<Test, Integer> trajanje;
	public static volatile SetAttribute<Test, TestQuestion> testQuestions;
}
