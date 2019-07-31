package com.aji.rumahsakit.rs.model.transaksi;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pemeriksaan")
public class Pemeriksaan {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@JoinColumn(name = "id_registrasi", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Registrasi idRegistrasi;
	
	
	@Column(name="jenis_perawatan")
	private String jenisPerawatan;
	
	@JsonIgnoreProperties("idPemeriksaan")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idPemeriksaan")
	private List<DetailPemeriksaanDiagnosa> detailPemeriksaanDiagnosa;
	
	@JsonIgnoreProperties("idPemeriksaan")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idPemeriksaan")
	private List<DetailPemeriksaanTindakan> detailPemeriksaanTindakan;

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

	public Registrasi getIdRegistrasi() {
		return idRegistrasi;
	}

	public void setIdRegistrasi(Registrasi idRegistrasi) {
		this.idRegistrasi = idRegistrasi;
	}

	public String getJenisPerawatan() {
		return jenisPerawatan;
	}

	public void setJenisPerawatan(String jenisPerawatan) {
		this.jenisPerawatan = jenisPerawatan;
	}


	public List<DetailPemeriksaanDiagnosa> getDetailPemeriksaanDiagnosa() {
		return detailPemeriksaanDiagnosa;
	}

	public void setDetailPemeriksaanDiagnosa(List<DetailPemeriksaanDiagnosa> detailPemeriksaanDiagnosa) {
		this.detailPemeriksaanDiagnosa = detailPemeriksaanDiagnosa;
	}

	public List<DetailPemeriksaanTindakan> getDetailPemeriksaanTindakan() {
		return detailPemeriksaanTindakan;
	}

	public void setDetailPemeriksaanTindakan(List<DetailPemeriksaanTindakan> detailPemeriksaanTindakan) {
		this.detailPemeriksaanTindakan = detailPemeriksaanTindakan;
	}
	
}
