package com.aji.rumahsakit.rs.model.transaksi;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.aji.rumahsakit.rs.model.master.Pasien;
import com.aji.rumahsakit.rs.model.master.Praktek;

@Entity
@Table(name = "registrasi")
public class Registrasi {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="kode")
	private String kode;
	
	@JoinColumn(name = "id_pasien", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Pasien idPasien;
	
	@JoinColumn(name = "id_praktek", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Praktek idPraktek;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tanggal")
	private Date tanggal;

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

	public Pasien getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(Pasien idPasien) {
		this.idPasien = idPasien;
	}

	public Praktek getIdPraktek() {
		return idPraktek;
	}

	public void setIdPraktek(Praktek idPraktek) {
		this.idPraktek = idPraktek;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	
}
