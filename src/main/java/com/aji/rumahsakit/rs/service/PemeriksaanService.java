package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.DetailPemeriksaanDiagnosaDao;
import com.aji.rumahsakit.rs.dao.DetailPemeriksaanTindakanDao;
import com.aji.rumahsakit.rs.dao.PemeriksaanDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.DetailPemeriksaanDiagnosa;
import com.aji.rumahsakit.rs.model.transaksi.DetailPemeriksaanTindakan;
import com.aji.rumahsakit.rs.model.transaksi.Pemeriksaan;

@Service
public class PemeriksaanService {

	@Autowired
	private PemeriksaanDao pemeriksaanDao;
	
	@Autowired
	private DetailPemeriksaanDiagnosaDao detPemeriksaanDiagnosaDao;

	@Autowired
	private DetailPemeriksaanTindakanDao detPemeriksaanTindakanDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(pemeriksaanDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!pemeriksaanDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Pemeriksaan pemeriksaan)throws ValidationException {
		
		if(pemeriksaan.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Pemeriksaan pemeriksaan)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(pemeriksaan.getIdRegistrasi()==null||pemeriksaan.getIdRegistrasi().getId()==null) {
			System.out.println("id registrasi kosong");
			sb.append("id registrasi kosong \n");
			error++;
		}
		if(pemeriksaan.getJenisPerawatan()==null) {
			System.out.println("jenis perawatan kosong");
			sb.append("jenis perawatan kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Pemeriksaan pemeriksaan)throws ValidationException{
		System.out.println("cek bk");
		if(pemeriksaanDao.isBkExist(pemeriksaan.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Pemeriksaan pemeriksaan)throws ValidationException{
		String s=findById(pemeriksaan.getId()).getKode();
		if(!pemeriksaan.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Pemeriksaan pemeriksaan) throws ValidationException{
		
		if(pemeriksaan.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Pemeriksaan pemeriksaan)throws ValidationException{
		
		valBkNotNull(pemeriksaan);
		valBkNotExist(pemeriksaan);
		System.out.println("bk not exist");
		valNonBk(pemeriksaan);
		pemeriksaanDao.save(pemeriksaan);
	}
	
	public void savePemeriksaanAndDetail(Pemeriksaan pemeriksaan) throws ValidationException {


		List<DetailPemeriksaanDiagnosa> diag=pemeriksaan.getDetailPemeriksaanDiagnosa();
		List<DetailPemeriksaanTindakan> tind=pemeriksaan.getDetailPemeriksaanTindakan();
		
		pemeriksaan.setDetailPemeriksaanDiagnosa(null);
		pemeriksaan.setDetailPemeriksaanTindakan(null);

		save(pemeriksaan);
		Pemeriksaan pem=findByBk(pemeriksaan);

		
		try {

			for(DetailPemeriksaanDiagnosa d : diag) {
				d.setIdPemeriksaan(pem);
				saveDiagnosa(d);			
			}
			for(DetailPemeriksaanTindakan t : tind) {
				t.setIdPemeriksaan(pem);
				saveTindakan(t);
			}
			
		}catch(ValidationException e)  {

			delete(pemeriksaan.getId());
			
			throw new ValidationException(e.getMessage());
		}
		
	}

	public void updatePemeriksaanAndDetail(Pemeriksaan pemeriksaan) throws ValidationException {
		update(pemeriksaan);
		
		try {
			
			for(DetailPemeriksaanDiagnosa diag:pemeriksaan.getDetailPemeriksaanDiagnosa()) {
				
				updateDiagnosa(diag);			
			}
			for(DetailPemeriksaanTindakan tind:pemeriksaan.getDetailPemeriksaanTindakan()) {
				
				updateTindakan(tind);
			}
			
		}catch(ValidationException e) {
			
			throw new ValidationException(e.getMessage());
		}
	}
	
	public void update(Pemeriksaan pemeriksaan)throws ValidationException{
		
		valIdNotNull(pemeriksaan);
		valIdExist(pemeriksaan.getId());
		valBkNotNull(pemeriksaan);
		valBkNotChange(pemeriksaan);
		valNonBk(pemeriksaan);
		pemeriksaanDao.save(pemeriksaan);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		pemeriksaanDao.delete(id);
	}
	
	public Pemeriksaan findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return pemeriksaanDao.findById(id);
	}
	
	public Pemeriksaan findByBk(Pemeriksaan pemeriksaan) {

		return pemeriksaanDao.findByBk(pemeriksaan.getKode());
	}

	public List<Pemeriksaan> findAll( )throws ValidationException{
		return pemeriksaanDao.findAll();
	}
	
	public Pemeriksaan findByKode(String kode) {

		return pemeriksaanDao.findByBk(kode);
	}
	public List<Pemeriksaan> findByFilter(String nama,String jenisPerawatan)throws ValidationException{
		return pemeriksaanDao.findByFilter(nama, jenisPerawatan);
	}

///////////////////////////////////////////////////detail pemeriksaan diagnosa///////////////////////////////////////////////////

	public void valDiagnosaIdNotExist(UUID id)throws ValidationException{
		
		if(detPemeriksaanDiagnosaDao.isExist(id)) {
			throw new ValidationException("detail diagnosa sudah ada");
		}
	}
	
	public void valDiagnosaIdExist(UUID id)throws ValidationException{
		
		if(!detPemeriksaanDiagnosaDao.isExist(id)) {
			throw new ValidationException("Data detail tidak ada");
		}
	}
	
	public void valDiagnosaIdNotNull(DetailPemeriksaanDiagnosa detPemeriksaan)throws ValidationException {
		
		if(detPemeriksaan.getId()==null) {
			throw new ValidationException("Id detail tidak boleh kosong");
		}
	}
	
	public void valDiagnosaNonBk(DetailPemeriksaanDiagnosa detPemeriksaan)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(detPemeriksaan.getIdPemeriksaan()==null||detPemeriksaan.getIdPemeriksaan().getId()==null) {
			System.out.println("id pemeriksaan kosong");
			sb.append("id pemeriksaan kosong \n");
			error++;
		}
		if(detPemeriksaan.getDiagnosa()==null) {
			System.out.println("diagnosa kosong");
			sb.append("diagnosa kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valDiagnosaBkNotExist(DetailPemeriksaanDiagnosa detPemeriksaan)throws ValidationException{
		System.out.println("cek bk");
		if(detPemeriksaanDiagnosaDao.isBkExist(detPemeriksaan.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valDiagnosaBkNotChange(DetailPemeriksaanDiagnosa detPemeriksaan)throws ValidationException{
		String s=findById(detPemeriksaan.getId()).getKode();
		if(!detPemeriksaan.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valDiagnosaBkNotNull(DetailPemeriksaanDiagnosa detPemeriksaan) throws ValidationException{
		
		if(detPemeriksaan.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveDiagnosa(DetailPemeriksaanDiagnosa detPemeriksaan)throws ValidationException{
		
		valDiagnosaBkNotNull(detPemeriksaan);
		valDiagnosaBkNotExist(detPemeriksaan);
		System.out.println("bk not exist");
		valDiagnosaNonBk(detPemeriksaan);
		detPemeriksaanDiagnosaDao.save(detPemeriksaan);
	}
	
	public void updateDiagnosa(DetailPemeriksaanDiagnosa detPemeriksaan)throws ValidationException{
		
		valDiagnosaIdNotNull(detPemeriksaan);
		valDiagnosaIdExist(detPemeriksaan.getId());
		valDiagnosaBkNotNull(detPemeriksaan);
		valDiagnosaBkNotChange(detPemeriksaan);
		valDiagnosaNonBk(detPemeriksaan);
		detPemeriksaanDiagnosaDao.save(detPemeriksaan);
	}
	
	public void deleteDiagnosa(UUID id)throws ValidationException{
	
		valDiagnosaIdExist(id);
		pemeriksaanDao.delete(id);
	}
	
	public DetailPemeriksaanDiagnosa findDiagnosaById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return detPemeriksaanDiagnosaDao.findById(id);
	}
	
	public DetailPemeriksaanDiagnosa findDiagnosaByBk(DetailPemeriksaanDiagnosa detPemeriksaan) {

		return detPemeriksaanDiagnosaDao.findByBk(detPemeriksaan.getKode());
	}
	public List<DetailPemeriksaanDiagnosa> findDiagnosaByFilter( )throws ValidationException{
		return detPemeriksaanDiagnosaDao.findAll();
	}
	
///////////////////////////////////////////////////detail pemeriksaan tindakan///////////////////////////////////////////////////

	public void valTindakanIdNotExist(UUID id)throws ValidationException{
	
		if(detPemeriksaanTindakanDao.isExist(id)) {
			throw new ValidationException("detail tindakan sudah ada");
		}
	}
	
	public void valTindakanIdExist(UUID id)throws ValidationException{
	
		if(!detPemeriksaanTindakanDao.isExist(id)) {
			throw new ValidationException("Data detail tindakan tidak ada");
		}
	}
	
	public void valTindakanIdNotNull(DetailPemeriksaanTindakan detPemeriksaan)throws ValidationException {
	
		if(detPemeriksaan.getId()==null) {
				throw new ValidationException("Id detail tindakan tidak boleh kosong");
		}
	}
	
	public void valTindakanNonBk(DetailPemeriksaanTindakan detPemeriksaan)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;
		
		if(detPemeriksaan.getIdPemeriksaan()==null||detPemeriksaan.getIdPemeriksaan().getId()==null) {
			System.out.println("id pemeriksaan kosong");
			sb.append("id pemeriksaan kosong \n");
			error++;
		}
		if(detPemeriksaan.getTindakan()==null||detPemeriksaan.getTindakan().getId()==null) {
			System.out.println("tindakan kosong");
			sb.append("tindakan kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valTindakanBkNotExist(DetailPemeriksaanTindakan detPemeriksaan)throws ValidationException{
		System.out.println("cek bk");
		if(detPemeriksaanDiagnosaDao.isBkExist(detPemeriksaan.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valTindakanBkNotChange(DetailPemeriksaanTindakan detPemeriksaan)throws ValidationException{
		String s=findById(detPemeriksaan.getId()).getKode();
		
		if(!detPemeriksaan.getKode().toString().equals(s.toString())) {
		
			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valTindakanBkNotNull(DetailPemeriksaanTindakan detPemeriksaan) throws ValidationException{
		
		if(detPemeriksaan.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveTindakan(DetailPemeriksaanTindakan detPemeriksaan)throws ValidationException{
	
		valTindakanBkNotNull(detPemeriksaan);
		valTindakanBkNotExist(detPemeriksaan);
		System.out.println("bk not exist");
		valTindakanNonBk(detPemeriksaan);
		detPemeriksaanTindakanDao.save(detPemeriksaan);
	}
	
	public void updateTindakan(DetailPemeriksaanTindakan detPemeriksaan)throws ValidationException{
	
		valTindakanIdNotNull(detPemeriksaan);
		valTindakanIdExist(detPemeriksaan.getId());
		valTindakanBkNotNull(detPemeriksaan);
		valTindakanBkNotChange(detPemeriksaan);
		valTindakanNonBk(detPemeriksaan);
		detPemeriksaanTindakanDao.save(detPemeriksaan);
	}
	
	public void deleteTindakan(UUID id)throws ValidationException{
	
		valTindakanIdExist(id);
		detPemeriksaanTindakanDao.delete(id);
	}
	
	public DetailPemeriksaanTindakan findTindakanById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return detPemeriksaanTindakanDao.findById(id);
	}
	
	public DetailPemeriksaanTindakan findTindakanByBk(DetailPemeriksaanTindakan detPemeriksaan) {
	
		return detPemeriksaanTindakanDao.findByBk(detPemeriksaan.getKode());
	}
	
	public List<DetailPemeriksaanTindakan> findTindakanByFilter( )throws ValidationException{
		return detPemeriksaanTindakanDao.findAll();
	}
}
