package com.aji.rumahsakit.rs.model.master;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "praktek")
public class Praktek {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@Column(name="kode")
	private String kode;
	
	@JoinColumn(name = "id_dokter", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Dokter idDokter;
	
	@JoinColumn(name = "id_poli", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Poli idPoli;
	
	@Column(name="jam")
	private String jam;
	
	@Column(name="state")
	private String state;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public Dokter getIdDokter() {
		return idDokter;
	}

	public void setIdDokter(Dokter idDokter) {
		this.idDokter = idDokter;
	}

	public Poli getIdPoli() {
		return idPoli;
	}

	public void setIdPoli(Poli idPoli) {
		this.idPoli = idPoli;
	}

	public String getJam() {
		return jam;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
