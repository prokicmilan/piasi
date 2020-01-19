package rs.ac.bg.etf.pm160695.infrastructure.validation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class CommonErrors {

	private LinkedHashSet<CommonError> errors = new LinkedHashSet<>();

	public boolean isEmpty() {
		return errors.isEmpty();
	}

	public void add(ConstraintViolation<? extends Object> violation) {
		errors.add(buildCommonError(violation));
	}

	public void add(String summary) {
		errors.add(new CommonError("ERR", summary));
	}

	public void add(CommonErrors errs) {
		for (CommonError commonError : errs.getErrors()) {
			errors.add(commonError);
		}
	}

	public Set<CommonError> getErrors() {
		return errors;
	}

	private CommonError buildCommonError(ConstraintViolation<? extends Object> violation) {
		String code = String.format("CONSTRAINT-%s",
				violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName().toUpperCase());

		String fieldName = violation.getPropertyPath().toString();
		String[] camelCaseWords = fieldName.split("(?=[A-Z])");

		StringBuilder title = new StringBuilder();

		for (String word : camelCaseWords) {
			title.append(word.toUpperCase());
			title.append(" ");
		}

		String message = String.format("Polje '%s' %s", title, violation.getMessage());

		return new CommonError(code, message);
	}

}
