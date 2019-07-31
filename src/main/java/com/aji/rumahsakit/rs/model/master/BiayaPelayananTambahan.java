package com.aji.rumahsakit.rs.model.master;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "biaya_pelayanan_tambahan")
public class BiayaPelayananTambahan {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@Column(name="kode")
	private String kode;
	
	@Column(name="jenis_pelayanan")
	private String namaPelayanan;
	
	@Column(name="harga_pelayanan")
	private Double harga;

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

	public String getNamaPelayanan() {
		return namaPelayanan;
	}

	public void setNama(String namaPelayanan) {
		this.namaPelayanan = namaPelayanan;
	}

	public Double getHarga() {
		return harga;
	}

	public void setHarga(Double harga) {
		this.harga = harga;
	}
	
}
