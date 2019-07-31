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

import com.aji.rumahsakit.rs.model.master.BiayaPelayananTambahan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sub_detail_inap")
public class SubDetailTransaksiInap {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@JoinColumn(name = "tindakan", referencedColumnName = "id")
	@OneToOne(optional = false)
	private BiayaPelayananTambahan idTindakan;
	
	@JsonIgnoreProperties("subDetailTransaksiInap")
	@JoinColumn(name = "id_detail_inap", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private DetailTransaksiInap idDetailTransaksiInap;

	@Column(name="total_biaya")
	private Long total;
	
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

	public BiayaPelayananTambahan getIdTindakan() {
		return idTindakan;
	}

	public void setIdTindakan(BiayaPelayananTambahan tindakan) {
		this.idTindakan = tindakan;
	}

	public DetailTransaksiInap getIdDetailTransaksiInap() {
		return idDetailTransaksiInap;
	}

	public void setIdDetailTransaksiInap(DetailTransaksiInap idDetailTransaksiInap) {
		this.idDetailTransaksiInap = idDetailTransaksiInap;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}
