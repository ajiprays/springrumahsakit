package com.aji.rumahsakit.rs.model.master;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pasien")
public class Pasien {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@Column(name="kode")
	private String kodePasien;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tgl_lahir")
	private Date tglLahir;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="jenis_kelamin")
	private String JenisKelamin;
	
	@Column(name="alamat")
	private String alamat;
	
	@Column(name="telp")
	private Long telp;

	
	public UUID getId() {
		return id;
	}
	public void setIdn(UUID id) {
		this.id = id;
	}
	public String getKodePasien() {
		return kodePasien;
	}
	public void setKodePasien(String kodePasien) {
		this.kodePasien = kodePasien;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getJenisKelamin() {
		return JenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		JenisKelamin = jenisKelamin;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public Long getTelp() {
		return telp;
	}
	public void setTelp(Long telp) {
		this.telp = telp;
	}
	public Date getTglLahir() {
		return tglLahir;
	}
	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	
}
