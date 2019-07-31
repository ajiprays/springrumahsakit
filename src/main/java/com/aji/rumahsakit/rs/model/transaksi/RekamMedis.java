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

import com.aji.rumahsakit.rs.model.master.Pasien;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rekam_medis")
public class RekamMedis {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@Column(name="kode")
	private String kode;
	
	@JoinColumn(name = "id_pasien", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Pasien idPasien;

	@JsonIgnoreProperties("idRekamMedis")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idRekamMedis")
	private List<DetailRekamMedis> detailRekamMedis;
	
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

	public List<DetailRekamMedis> getDetailRekamMedis() {
		return detailRekamMedis;
	}

	public void setDetailRekamMedis(List<DetailRekamMedis> detailRekamMedis) {
		this.detailRekamMedis = detailRekamMedis;
	}
	
}
