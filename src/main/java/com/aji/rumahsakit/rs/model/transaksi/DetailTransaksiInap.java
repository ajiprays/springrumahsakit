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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.aji.rumahsakit.rs.model.master.ListKamar;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detail_inap")
public class DetailTransaksiInap {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@JsonIgnoreProperties("detailTransaksiInap")
	@JoinColumn(name = "id_inap", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private TransaksiInap idInap;
	
	@JoinColumn(name = "id_kamar",referencedColumnName = "id")
	@OneToOne(optional = false)
	private ListKamar idKamar;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_masuk")
	private Date tglMasuk;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_keluar")
	private Date tglKeluar;
	
	@Column(name="total_biaya")
	private Long biaya;
	
	@Column(name="keterangan_keluar")
	private String keteranganKeluar;

	@JsonIgnoreProperties("idDetailTransaksiInap")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "idDetailTransaksiInap")
	private List<SubDetailTransaksiInap> subDetailTransaksiInap;
	
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

	public TransaksiInap getIdInap() {
		return idInap;
	}

	public void setIdInap(TransaksiInap idInap) {
		this.idInap = idInap;
	}

	public ListKamar getIdKamar() {
		return idKamar;
	}

	public void setIdKamar(ListKamar idKamar) {
		this.idKamar = idKamar;
	}

	public Date getTglMasuk() {
		return tglMasuk;
	}

	public void setTglMasuk(Date tglMasuk) {
		this.tglMasuk = tglMasuk;
	}

	public Date getTglKeluar() {
		return tglKeluar;
	}

	public void setTglKeluar(Date tglKeluar) {
		this.tglKeluar = tglKeluar;
	}

	public Long getBiaya() {
		return biaya;
	}

	public void setBiaya(Long biaya) {
		this.biaya = biaya;
	}

	public String getKeteranganKeluar() {
		return keteranganKeluar;
	}

	public void setKeteranganKeluar(String keteranganKeluar) {
		this.keteranganKeluar = keteranganKeluar;
	}

	public List<SubDetailTransaksiInap> getSubDetailTransaksiInap() {
		return subDetailTransaksiInap;
	}

	public void setSubDetailTransaksiInap(List<SubDetailTransaksiInap> subDetailTransaksiInap) {
		this.subDetailTransaksiInap = subDetailTransaksiInap;
	}
	
	
	
}
