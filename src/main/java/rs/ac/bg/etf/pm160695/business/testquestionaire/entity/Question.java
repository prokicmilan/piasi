package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity;
import rs.ac.bg.etf.pm160695.presentation.qt.infrastructure.form.InputType;

@MappedSuperclass
public class Question extends StatusBaseEntity {

	private static final long serialVersionUID = -4455191811827514420L;
	
	@NotNull
	@Enumerated
	@Column(name = "input_type")
	private InputType inputType;
	
	@NotBlank
	private String question;
	
	private String answers;
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answers, inputType, question);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Question)) {
			return false;
		}
		Question other = (Question) obj;
		return Objects.equals(answers, other.answers) && inputType == other.inputType
				&& Objects.equals(question, other.question);
	}

}
