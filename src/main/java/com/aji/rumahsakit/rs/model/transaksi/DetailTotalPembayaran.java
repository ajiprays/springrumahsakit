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

import com.aji.rumahsakit.rs.model.master.JenisTransaksi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detail_total_pembayaran")
public class DetailTotalPembayaran {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "kode")
	private String kode;
	
	@JoinColumn(name = "id_jenis_trx", referencedColumnName = "id")
	@OneToOne(optional = false)
	private JenisTransaksi jenisTransaksi;
	
//	@JoinColumn(name = "id_trx", referencedColumnName = "id")
//	@OneToOne(optional = false)
	@Column(name="id_trx")
	private UUID idTrx; 
	
	private double total;

	@JsonIgnoreProperties("detailPemeriksaan")
	@JoinColumn(name = "id_pembayaran", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private TotalPembayaran idPembayaran;
	
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


	public TotalPembayaran getIdPembayaran() {
		return idPembayaran;
	}


	public void setIdPembayaran(TotalPembayaran idPembayaran) {
		this.idPembayaran = idPembayaran;
	}


	public JenisTransaksi getJenisTransaksi() {
		return jenisTransaksi;
	}


	public void setJenisTransaksi(JenisTransaksi jenisTransaksi) {
		this.jenisTransaksi = jenisTransaksi;
	}


	public UUID getIdTrx() {
		return idTrx;
	}


	public void setIdTrx(UUID idTrx) {
		this.idTrx = idTrx;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
}
