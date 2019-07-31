package com.aji.rumahsakit.rs.model.master;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="daftar_kamar")
public class ListKamar {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@JoinColumn(name = "id_jenis_kamar", referencedColumnName = "id")
	@OneToOne(optional = false)
	private JenisKamar idJenisKamar;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="lantai")
	private Integer lantai;
	
	@Column(name="state")
	private String state;
	
	@Column(name="kode_kamar")
	private String kode;

	public ListKamar() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public JenisKamar getIdJenisKamar() {
		return idJenisKamar;
	}

	public void setIdJenisKamar(JenisKamar idJenisKamar) {
		this.idJenisKamar = idJenisKamar;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getLantai() {
		return lantai;
	}

	public void setLantai(Integer lantai) {
		this.lantai = lantai;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}
	
	
}
