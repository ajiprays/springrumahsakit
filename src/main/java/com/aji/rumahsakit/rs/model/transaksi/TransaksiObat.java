package com.aji.rumahsakit.rs.model.transaksi;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transaksi_obat")
public class TransaksiObat {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tanggal")
	private Date tanggal;
	
	@Column(name = "total_harga")
	private Double totalHarga;

	@JsonIgnoreProperties("transaksiObat")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "transaksiObat")
	private List<DetailTransaksiObat> detailTransaksiObat;

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

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Double getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(Double totalHarga) {
		this.totalHarga = totalHarga;
	}

	public List<DetailTransaksiObat> getDetailTransaksiObat() {
		return detailTransaksiObat;
	}

	public void setDetailTransaksiObat(List<DetailTransaksiObat> detailTransaksiObat) {
		this.detailTransaksiObat = detailTransaksiObat;
	}

}
