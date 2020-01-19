package rs.ac.bg.etf.pm160695.infrastructure.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtils {

	public static CommonErrors validate(Object object) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> violations = validator.validate(object);

		CommonErrors commonErrors = new CommonErrors();

		for (ConstraintViolation<Object> violation : violations) {
			commonErrors.add(violation);
		}

		return commonErrors;
	}

}
