package com.aji.rumahsakit.rs.model.master;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="dokter")
public class Dokter {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@Column(name="sip")
	private String sip;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="jenis_kelamin")
	private String jenisKelamin;
	
	@Column(name="alamat")
	private String alamat;
	
	@Column(name="telp")
	private Long telp;
	
	@Column(name="state")
	private String state;

	public Dokter() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setIdr(UUID id) {
		this.id = id;
	}

	public String getSip() {
		return sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
