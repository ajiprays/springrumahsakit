package com.aji.rumahsakit.rs.model.transaksi;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.aji.rumahsakit.rs.model.master.Dokter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detail_rekam_medis")
public class DetailRekamMedis {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;

	@Column(name = "kode")
	private String kode;

	@JsonIgnoreProperties("detailRekamMedis")
	@JoinColumn(name = "id_rekam_medis", referencedColumnName = "id")
	@ManyToOne(optional = false)
    private RekamMedis idRekamMedis;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tanggal")
	private Date tanggal;
	
	@JoinColumn(name = "id_dokter", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Dokter idDokter;
	
	@JoinColumn(name = "id_pemeriksaan", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Pemeriksaan idPemeriksaan;
	
	@JoinColumn(name = "id_inap", referencedColumnName = "id")
	@OneToOne(optional = false)
	private TransaksiInap idInap;

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

	public RekamMedis getIdRekamMedis() {
		return idRekamMedis;
	}

	public void setIdRekamMedis(RekamMedis idRekamMedis) {
		this.idRekamMedis = idRekamMedis;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Dokter getIdDokter() {
		return idDokter;
	}

	public void setIdDokter(Dokter idDokter) {
		this.idDokter = idDokter;
	}

	public Pemeriksaan getIdPemeriksaan() {
		return idPemeriksaan;
	}

	public void setIdPemeriksaan(Pemeriksaan idPemeriksaan) {
		this.idPemeriksaan = idPemeriksaan;
	}

	public TransaksiInap getIdInap() {
		return idInap;
	}

	public void setIdInap(TransaksiInap idInap) {
		this.idInap = idInap;
	}
		
	
}
