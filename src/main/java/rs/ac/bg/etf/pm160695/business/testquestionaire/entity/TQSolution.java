package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity;

@MappedSuperclass
public class TQSolution extends StatusBaseEntity {

	private static final long serialVersionUID = -6614332837108187263L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "korisnik_id")
	private KSKorisnik korisnik;

	private String answersData;

	public KSKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KSKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getAnswersData() {
		return answersData;
	}

	public void setAnswersData(String answersData) {
		this.answersData = answersData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answersData, korisnik);
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
		if (!(obj instanceof TQSolution)) {
			return false;
		}
		TQSolution other = (TQSolution) obj;
		return Objects.equals(answersData, other.answersData) && Objects.equals(korisnik, other.korisnik);
	}
}
