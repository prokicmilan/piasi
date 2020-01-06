package rs.ac.bg.etf.pm160695.infrastructure.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public abstract class StatusBaseEntity extends BaseEntity {

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "ks_korisnik_id")
//	private KSKorisnik ksKorisnik;

	@Column(name = "insert_timestamp")
	private LocalDateTime insertTimestamp;

	@Column(name = "last_update_timestamp")
	private LocalDateTime lastUpdateTimestamp;

	@Column(name = "record_status")
	private Integer recordStatus;

	@Version
	private Integer version;

//	public KSKorisnik getKsKorisnik() {
//		return ksKorisnik;
//	}

	public LocalDateTime getInsertTimestamp() {
		return insertTimestamp;
	}

	public LocalDateTime getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public Integer getVersion() {
		return version;
	}

	@PrePersist
	public void prePersist() {
		insertTimestamp = LocalDateTime.now();
		lastUpdateTimestamp = LocalDateTime.of(insertTimestamp.toLocalDate(), insertTimestamp.toLocalTime());
		recordStatus = EntityRecordStatus.AKTIVAN.getValue();
	}

	@PreUpdate
	public void preUpdate() {
		lastUpdateTimestamp = LocalDateTime.now();
	}

}
