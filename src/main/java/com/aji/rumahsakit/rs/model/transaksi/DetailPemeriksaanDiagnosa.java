package com.aji.rumahsakit.rs.model.transaksi;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detail_pemeriksaan_diagnosa")
public class DetailPemeriksaanDiagnosa {

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
	
	@Column(name = "diagnosa")
	private String diagnosa;

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

	public String getDiagnosa() {
		return diagnosa;
	}

	public void setDiagnosa(String diagnosa) {
		this.diagnosa = diagnosa;
	}
	
	
}
