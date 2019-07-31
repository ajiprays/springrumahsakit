package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.DetailRekamMedisDao;
import com.aji.rumahsakit.rs.dao.RekamMedisDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.DetailRekamMedis;
import com.aji.rumahsakit.rs.model.transaksi.RekamMedis;

@Service
public class RekamMedisService {

	@Autowired
	private RekamMedisDao rmDao;
	
	@Autowired
	private DetailRekamMedisDao detRmDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(rmDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!rmDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(RekamMedis rm)throws ValidationException {
		
		if(rm.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(RekamMedis rm)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(rm.getIdPasien()==null||rm.getIdPasien().getId()==null) {
			System.out.println("id pasien kosong");
			sb.append("id pasien kosong \n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(RekamMedis rm)throws ValidationException{
		System.out.println("cek bk");
		if(rmDao.isBkExist(rm.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(RekamMedis rm)throws ValidationException{
		String s=findById(rm.getId()).getKode();
		if(!rm.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(RekamMedis rm) throws ValidationException{
		
		if(rm.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(RekamMedis rm)throws ValidationException{
		
		valBkNotNull(rm);
		valBkNotExist(rm);
		System.out.println("bk not exist");
		valNonBk(rm);
		rmDao.save(rm);
	}
	
	public void saveRekamMedisAndDetail(RekamMedis rm) throws Exception {


		List<DetailRekamMedis> detList=rm.getDetailRekamMedis();
			
		rm.setDetailRekamMedis(null);

		save(rm);
		RekamMedis rmtemp=findByBk(rm);

		
		try {

			for(DetailRekamMedis d : detList) {
				
				d.setIdRekamMedis(rmtemp);
				saveDetail(d);			
			}
			
		}catch(ValidationException e)  {

			delete(rmtemp.getId());
			
			throw new ValidationException(e.getMessage());
		}
		catch(Exception e)  {

			delete(rmtemp.getId());
			
			throw new Exception(e.getMessage());
		}
		
	}

	public void updateRekamMedisAndDetail(RekamMedis rm) throws ValidationException {
						
		try {

			for(DetailRekamMedis d : rm.getDetailRekamMedis()) {			
								
				updateDetail(d);
					
			}

			update(rm);

		}catch(ValidationException e)  {

			throw new ValidationException(e.getMessage());
		}
	}
	
	public void update(RekamMedis rm)throws ValidationException{
		
		valIdNotNull(rm);
		valIdExist(rm.getId());
		valBkNotNull(rm);
		valBkNotChange(rm);
		valNonBk(rm);
		rmDao.save(rm);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		rmDao.delete(id);
	}
	
	public RekamMedis findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return rmDao.findById(id);
	}
	
	public RekamMedis findByBk(RekamMedis rm) {

		return rmDao.findByBk(rm.getKode());
	}
	
	public RekamMedis findByKode(String kode) {

		return rmDao.findByBk(kode);
	}

	public List<RekamMedis> findAll( )throws ValidationException{
		return rmDao.findAll();
	}
	
	public List<RekamMedis> findByFilter(String pasien,String dokter,String tanggal)throws ValidationException{
		return rmDao.findByFilter(pasien, dokter, tanggal);
	}
	
///////////////////////////////////////////////////detail rekam medis//////////////////////////////////////////////////////////////////////////////////////

	public void valDetailIdNotExist(UUID id)throws ValidationException{
		
		if(detRmDao.isExist(id)) {
			throw new ValidationException("detail sudah ada");
		}
	}
	
	public void valDetailIdExist(UUID id)throws ValidationException{
		
		if(!detRmDao.isExist(id)) {
			throw new ValidationException("Data detail tidak ada");
		}
	}
	
	public void valDetailIdNotNull(DetailRekamMedis detRm)throws ValidationException {
		
		if(detRm.getId()==null) {
			throw new ValidationException("Id detail tidak boleh kosong");
		}
	}
	
	public void valDetailNonBk(DetailRekamMedis detRm)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(detRm.getIdDokter()==null||detRm.getIdDokter().getId()==null) {
			System.out.println("id dokter kosong");
			sb.append("id dokter kosong \n");
			error++;
		}
		if(detRm.getTanggal()==null) {
			System.out.println("tanggal kosong");
			sb.append("tanggal kosong\n");
			error++;
		}
		if(detRm.getIdPemeriksaan()==null||detRm.getIdPemeriksaan().getId()==null) {
			System.out.println("id pemeriksaan kosong");
			sb.append("id pemeriksaan kosong\n");
			error++;
		}
		if(detRm.getIdRekamMedis()==null||detRm.getIdRekamMedis().getId()==null) {
			System.out.println("id rekammedis kosong");
			sb.append("id rekammedis kosong\n");
			error++;
		}		
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valDetailBkNotExist(DetailRekamMedis detRm)throws ValidationException{
		
		System.out.println("cek bk");
		
		if(detRmDao.isBkExist(detRm.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valDetailBkNotChange(DetailRekamMedis detRm)throws ValidationException{
		
		String s=findDetailById(detRm.getId()).getKode();
		
		if(!detRm.getKode().toString().equals(s.toString())) {
			System.out.println("kode berubah");
			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valDetailBkNotNull(DetailRekamMedis detRm) throws ValidationException{
		
		if(detRm.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveDetail(DetailRekamMedis detRm)throws ValidationException{
		
		valDetailBkNotNull(detRm);
		valDetailBkNotExist(detRm);
		System.out.println("bk not exist");
		valDetailNonBk(detRm);
		
		detRmDao.save(detRm);
		System.out.println("clear");
	}
	
	public void updateDetail(DetailRekamMedis detRm)throws ValidationException{

		valDetailIdNotNull(detRm);
		valDetailIdExist(detRm.getId());
		valDetailBkNotNull(detRm);
		valDetailBkNotChange(detRm);
		valDetailNonBk(detRm);
		detRmDao.save(detRm);
		System.out.println("updated");
	}
	
	public void deleteDetail(UUID id)throws ValidationException{
	
		valDetailIdExist(id);
		detRmDao.delete(id);
	}
	
	public DetailRekamMedis findDetailById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return detRmDao.findById(id);
	}
	
	public DetailRekamMedis findDetailByBk(DetailRekamMedis detRm) {

		return detRmDao.findByBk(detRm.getKode());
	}
	public List<DetailRekamMedis> findDetailByFilter( )throws ValidationException{
		return detRmDao.findAll();
	}

}
