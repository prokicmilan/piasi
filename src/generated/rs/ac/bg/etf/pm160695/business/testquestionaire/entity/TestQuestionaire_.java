package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity_;

@Generated(value="Dali", date="2020-02-06T21:43:18.641+0100")
@StaticMetamodel(TestQuestionaire.class)
public class TestQuestionaire_ extends StatusBaseEntity_ {
	public static volatile SingularAttribute<TestQuestionaire, String> naziv;
	public static volatile SingularAttribute<TestQuestionaire, String> opis;
	public static volatile SingularAttribute<TestQuestionaire, LocalDate> datumOd;
	public static volatile SingularAttribute<TestQuestionaire, LocalDate> datumDo;
}
