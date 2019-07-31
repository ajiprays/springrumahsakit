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
@Table(name = "resep")
public class Resep {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;

	@Column(name="kode")
	private String kode;
	
	@JoinColumn(name = "id_pemeriksaan", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Pemeriksaan idPemeriksaan;
	
	@JsonIgnoreProperties("idResep")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idResep")
	private List<DetailResep> detailReseps;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Pemeriksaan getIdPemeriksaan() {
		return idPemeriksaan;
	}

	public void setIdPemeriksaan(Pemeriksaan idPemeriksaan) {
		this.idPemeriksaan = idPemeriksaan;
	}

	public List<DetailResep> getDetailReseps() {
		return detailReseps;
	}

	public void setDetailReseps(List<DetailResep> detailReseps) {
		this.detailReseps = detailReseps;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}
	
	
}
