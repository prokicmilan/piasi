package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

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
	
}
