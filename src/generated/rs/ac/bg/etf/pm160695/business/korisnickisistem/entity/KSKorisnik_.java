package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity_;

@Generated(value="Dali", date="2020-01-25T18:30:14.019+0100")
@StaticMetamodel(KSKorisnik.class)
public class KSKorisnik_ extends StatusBaseEntity_ {
	public static volatile SingularAttribute<KSKorisnik, String> username;
	public static volatile SingularAttribute<KSKorisnik, String> password;
	public static volatile SingularAttribute<KSKorisnik, String> salt;
	public static volatile SingularAttribute<KSKorisnik, String> ime;
	public static volatile SingularAttribute<KSKorisnik, String> prezime;
	public static volatile SingularAttribute<KSKorisnik, String> email;
	public static volatile SingularAttribute<KSKorisnik, LocalDate> datumRodjenja;
	public static volatile SingularAttribute<KSKorisnik, String> mestoRodjenja;
	public static volatile SingularAttribute<KSKorisnik, String> telefon;
	public static volatile SingularAttribute<KSKorisnik, Boolean> aktivan;
	public static volatile SetAttribute<KSKorisnik, KSUloga> uloge;
}
