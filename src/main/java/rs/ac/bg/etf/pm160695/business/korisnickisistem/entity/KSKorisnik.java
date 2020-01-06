package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.infrastructure.datamodel.EntityRecordStatus;
import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity;

@Entity
@Table(name = "ks_korisnik")
public class KSKorisnik extends StatusBaseEntity {

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;

	@NotNull
	@NotBlank
	private String salt;

	@NotNull
	@NotBlank
	private String ime;

	@NotNull
	@NotBlank
	private String prezime;

	@NotNull
	@NotBlank
	private String email;

	@NotNull
	@Column(name = "datum_rodjenja")
	private LocalDate datumRodjenja;

	@NotNull
	@Column(name = "mesto_rodjenja")
	private String mestoRodjenja;

	@NotNull
	private String telefon;

	public String getUsername() {
		return username;
	}

	public KSKorisnik() {
	}

	public KSKorisnik(@NotNull @NotBlank String username, @NotNull @NotBlank String ime,
			@NotNull @NotBlank String prezime, @NotNull @NotBlank String email, @NotNull LocalDate datumRodjenja,
			@NotNull String mestoRodjenja, @NotNull String telefon) {
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.mestoRodjenja = mestoRodjenja;
		this.telefon = telefon;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getMestoRodjenja() {
		return mestoRodjenja;
	}

	public void setMestoRodjenja(String mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public String toString() {
		return "KSKorisnik [id = " + getId() + ", username=" + username + ", password=" + password + ", salt = " + salt
				+ ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", datumRodjenja=" + datumRodjenja
				+ ", mestoRodjenja=" + mestoRodjenja + ", telefon=" + telefon + ", recordStatus = "
				+ EntityRecordStatus.parse(getRecordStatus()) + "]";
	}

}
