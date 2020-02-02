package rs.ac.bg.etf.pm160695.business.testquestionaire.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity;

@MappedSuperclass
public class TestQuestionaire extends StatusBaseEntity {

	private static final long serialVersionUID = 7598205931201832450L;

	@NotBlank
	private String naziv;

	@NotBlank
	private String opis;

	@NotNull
	@Column(name = "datum_od")
	private LocalDate datumOd;

	@NotNull
	@Column(name = "datum_do")
	private LocalDate datumDo;

	@NotNull
	@Column(name = "questions_data")
	private String questionsData;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(LocalDate datumOd) {
		this.datumOd = datumOd;
	}

	public LocalDate getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(LocalDate datumDo) {
		this.datumDo = datumDo;
	}

	public String getQuestionsData() {
		return questionsData;
	}

	public void setQuestionsData(String questionsData) {
		this.questionsData = questionsData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((datumDo == null) ? 0 : datumDo.hashCode());
		result = prime * result + ((datumOd == null) ? 0 : datumOd.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((questionsData == null) ? 0 : questionsData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestQuestionaire other = (TestQuestionaire) obj;
		if (datumDo == null) {
			if (other.datumDo != null)
				return false;
		} else if (!datumDo.equals(other.datumDo))
			return false;
		if (datumOd == null) {
			if (other.datumOd != null)
				return false;
		} else if (!datumOd.equals(other.datumOd))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (questionsData == null) {
			if (other.questionsData != null)
				return false;
		} else if (!questionsData.equals(other.questionsData))
			return false;
		return true;
	}

}
