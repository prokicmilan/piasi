package rs.ac.bg.etf.pm160695.business.korisnickisistem.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import rs.ac.bg.etf.pm160695.infrastructure.datamodel.StatusBaseEntity;

@Entity
@Table(name = "ks_korisnik")
public class KSKorisnik extends StatusBaseEntity {

	@NotBlank
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String salt;

	@NotNull
	@NotBlank
	private String ime;

	@NotBlank
	private String prezime;

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
	
	@NotNull
	private Boolean aktivan;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ks_korisnik_ks_uloga",
			   joinColumns = @JoinColumn(
			   			name = "korisnik_id", 
			   			referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(
					    name = "uloga_id",
					    referencedColumnName = "id"))
	private Set<KSUloga> uloge = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public KSKorisnik() {
	}
	
	public KSKorisnik(@NotNull @NotBlank String username, @NotNull @NotBlank String ime, @NotNull @NotBlank String prezime,
			@NotNull @NotBlank String email, @NotNull LocalDate datumRodjenja, @NotNull String mestoRodjenja,
			@NotNull String telefon, @NotNull KSUloga uloga) {
		super();
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.mestoRodjenja = mestoRodjenja;
		this.telefon = telefon;
		this.uloge.add(uloga);
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

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	public Set<KSUloga> getUloge() {
		return new HashSet<>(uloge);
	}
	
	public void setUloge(Set<KSUloga> uloge) {
		this.uloge.clear();
		if (uloge != null) {
			this.uloge.addAll(uloge);
		}
	}
	
	public void addUloga(KSUloga uloga) {
		uloge.add(uloga);
	}
	
	public void removeUloga(KSUloga uloga) {
		uloge.remove(uloga);
	}

	@Override
	public String toString() {
		return "KSKorisnik [id = " + getId() + ", username=" + username + ", password=" + password + ", salt = " + salt
				+ ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", datumRodjenja=" + datumRodjenja
				+ ", mestoRodjenja=" + mestoRodjenja + ", telefon=" + telefon + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(aktivan, datumRodjenja, email, ime, mestoRodjenja, password, prezime,
				salt, telefon, uloge, username);
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
		if (!(obj instanceof KSKorisnik)) {
			return false;
		}
		KSKorisnik other = (KSKorisnik) obj;
		return Objects.equals(aktivan, other.aktivan) && Objects.equals(datumRodjenja, other.datumRodjenja)
				&& Objects.equals(email, other.email) && Objects.equals(ime, other.ime)
				&& Objects.equals(mestoRodjenja, other.mestoRodjenja) && Objects.equals(password, other.password)
				&& Objects.equals(prezime, other.prezime) && Objects.equals(salt, other.salt)
				&& Objects.equals(telefon, other.telefon) && Objects.equals(uloge, other.uloge)
				&& Objects.equals(username, other.username);
	}

}
