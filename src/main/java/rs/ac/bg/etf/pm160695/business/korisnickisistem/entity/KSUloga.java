package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rs.ac.bg.etf.pm160695.infrastructure.datamodel.BaseEntity;

@Entity
@Table(name = "ks_uloga")
@NamedQuery(name = "findAll", query = "select k from KSUloga k")
public class KSUloga extends BaseEntity {

	private String oznaka;
	
	private String naziv;
	
	private Boolean privileged;

	public String getOznaka() {
		return oznaka;
	}

	public String getNaziv() {
		return naziv;
	}

	public Boolean getPrivileged() {
		return privileged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(naziv, oznaka, privileged);
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
		if (!(obj instanceof KSUloga)) {
			return false;
		}
		KSUloga other = (KSUloga) obj;
		return Objects.equals(naziv, other.naziv) && Objects.equals(oznaka, other.oznaka)
				&& Objects.equals(privileged, other.privileged);
	}
	
}
