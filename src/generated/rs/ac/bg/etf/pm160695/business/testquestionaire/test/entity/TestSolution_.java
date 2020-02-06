package rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution_;

@Generated(value="Dali", date="2020-02-06T21:43:18.754+0100")
@StaticMetamodel(TestSolution.class)
public class TestSolution_ extends TQSolution_ {
	public static volatile SingularAttribute<TestSolution, Test> test;
	public static volatile SetAttribute<TestSolution, TestAnswer> answers;
}
