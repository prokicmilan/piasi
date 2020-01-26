package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;

@MappedSuperclass
public abstract class StatusBaseEntity extends BaseEntity {

	private static final long serialVersionUID = -1662948700649008948L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ks_korisnik_id")
	private KSKorisnik ksKorisnik;

	@Column(name = "record_status")
	private Integer recordStatus;

	@Column(name = "insert_timestamp")
	private LocalDateTime insertTimestamp;

	@Column(name = "last_update_timestamp")
	private LocalDateTime lastUpdateTimestamp;

	@Version
	private Integer version;

	public KSKorisnik getKsKorisnik() {
		return ksKorisnik;
	}

	public void setKsKorisnik(KSKorisnik ksKorisnik) {
		this.ksKorisnik = ksKorisnik;
	}

	public LocalDateTime getInsertTimestamp() {
		return insertTimestamp;
	}

	public LocalDateTime getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public Integer getVersion() {
		return version;
	}

	@PrePersist
	protected void prePersist() {
		insertTimestamp = LocalDateTime.now();
		lastUpdateTimestamp = LocalDateTime.of(insertTimestamp.toLocalDate(), insertTimestamp.toLocalTime());
		recordStatus = EntityRecordStatus.AKTIVAN.getValue();
	}

	@PreUpdate
	protected void preUpdate() {
		lastUpdateTimestamp = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(insertTimestamp, lastUpdateTimestamp, version);
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
		if (!(obj instanceof StatusBaseEntity)) {
			return false;
		}
		StatusBaseEntity other = (StatusBaseEntity) obj;
		return Objects.equals(insertTimestamp, other.insertTimestamp) && Objects.equals(ksKorisnik, other.ksKorisnik)
				&& Objects.equals(lastUpdateTimestamp, other.lastUpdateTimestamp)
				&& Objects.equals(version, other.version);
	}

}
