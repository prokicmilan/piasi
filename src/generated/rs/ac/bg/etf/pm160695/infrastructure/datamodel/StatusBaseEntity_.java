package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;

@Generated(value="Dali", date="2020-01-25T19:01:34.831+0100")
@StaticMetamodel(StatusBaseEntity.class)
public class StatusBaseEntity_ extends BaseEntity_ {
	public static volatile SingularAttribute<StatusBaseEntity, KSKorisnik> ksKorisnik;
	public static volatile SingularAttribute<StatusBaseEntity, Integer> recordStatus;
	public static volatile SingularAttribute<StatusBaseEntity, LocalDateTime> insertTimestamp;
	public static volatile SingularAttribute<StatusBaseEntity, LocalDateTime> lastUpdateTimestamp;
	public static volatile SingularAttribute<StatusBaseEntity, Integer> version;
}
