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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "total_pembayaran")
public class TotalPembayaran {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@JoinColumn(name = "id_pemeriksaan", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Pemeriksaan idPemeriksaan;

	@Temporal(TemporalType.DATE)
	@Column(name = "tanggal")
	private Date tanggal;
	
	@Column(name = "total")
	private Double total;

	@JsonIgnoreProperties("id")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id")
	private List<DetailTotalPembayaran> detailTotalPembayaran;

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

	public Pemeriksaan getIdPemeriksaan() {
		return idPemeriksaan;
	}

	public void setIdPemeriksaan(Pemeriksaan idPemeriksaan) {
		this.idPemeriksaan = idPemeriksaan;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<DetailTotalPembayaran> getDetailTotalPembayaran() {
		return detailTotalPembayaran;
	}

	public void setDetailTotalPembayaran(List<DetailTotalPembayaran> detailTotalPembayaran) {
		this.detailTotalPembayaran = detailTotalPembayaran;
	}

}
