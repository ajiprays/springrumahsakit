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

import com.aji.rumahsakit.rs.model.master.Obat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detail_resep")
public class DetailResep {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	
	@JoinColumn(name = "id_obat", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Obat idObat;
	
	@Column(name="keterangan")
	private String keterangan;
	
	@JsonIgnoreProperties("detailReseps")
	@JoinColumn(name = "id_resep", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Resep idResep;

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

	public Obat getIdObat() {
		return idObat;
	}

	public void setIdObat(Obat idObat) {
		this.idObat = idObat;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Resep getIdResep() {
		return idResep;
	}

	public void setIdResep(Resep idResep) {
		this.idResep = idResep;
	}
	
}
