package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntity_;

@Generated(value="Dali", date="2020-01-25T18:56:22.933+0100")
@StaticMetamodel(KSUloga.class)
public class KSUloga_ extends BaseEntity_ {
	public static volatile SingularAttribute<KSUloga, String> oznaka;
	public static volatile SingularAttribute<KSUloga, String> naziv;
	public static volatile SingularAttribute<KSUloga, Integer> ulogaTip;
}
