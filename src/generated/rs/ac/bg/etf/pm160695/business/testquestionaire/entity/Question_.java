package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity_;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.InputType;

@Generated(value="Dali", date="2020-02-06T21:43:18.621+0100")
@StaticMetamodel(Question.class)
public class Question_ extends StatusBaseEntity_ {
	public static volatile SingularAttribute<Question, InputType> inputType;
	public static volatile SingularAttribute<Question, String> question;
	public static volatile SingularAttribute<Question, String> answers;
}
