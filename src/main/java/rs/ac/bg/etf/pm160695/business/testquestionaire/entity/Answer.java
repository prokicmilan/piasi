package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.util.Objects;

import javax.persistence.MappedSuperclass;

import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity;

@MappedSuperclass
public class Answer extends StatusBaseEntity {

	private static final long serialVersionUID = 1689320490579856050L;

	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answer);
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
		if (!(obj instanceof Answer)) {
			return false;
		}
		Answer other = (Answer) obj;
		return Objects.equals(answer, other.answer);
	}
}
