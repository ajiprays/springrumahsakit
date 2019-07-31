package com.aji.rumahsakit.rs.model.transaksi;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.aji.rumahsakit.rs.model.master.BiayaPelayananTambahan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detail_pemeriksaan_tindakan")
public class DetailPemeriksaanTindakan {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@JsonIgnoreProperties("detailPemeriksaan")
	@JoinColumn(name = "id_pemeriksaan", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Pemeriksaan idPemeriksaan;
	
	@JoinColumn(name = "tindakan", referencedColumnName = "id")
	@OneToOne(optional = false)
	private BiayaPelayananTambahan tindakan;

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

	public Pemeriksaan getIdPemeriksaan() {
		return idPemeriksaan;
	}

	public void setIdPemeriksaan(Pemeriksaan idPemeriksaan) {
		this.idPemeriksaan = idPemeriksaan;
	}

	public BiayaPelayananTambahan getTindakan() {
		return tindakan;
	}

	public void setTindakan(BiayaPelayananTambahan tindakan) {
		this.tindakan = tindakan;
	}
	
	
}
