package rs.ac.bg.etf.pm160695.infrastructure.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Target({ PARAMETER, FIELD })
@Retention(RUNTIME)
public @interface CurrentUserLogout {
}
