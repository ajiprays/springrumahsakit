package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.PraktekDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.Praktek;

@Service
public class PraktekService {

	@Autowired
	private PraktekDao praktekDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(praktekDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!praktekDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Praktek praktek)throws ValidationException {
		
		if(praktek.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Praktek praktek)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(praktek.getIdPoli()==null||praktek.getIdPoli().getId()==null) {
			System.out.println("id poli kosong");
			sb.append("id poli kosong \n");
			error++;
		}
		if(praktek.getIdDokter()==null||praktek.getIdDokter().getId()==null) {
			System.out.println("id dokter kosong");
			sb.append("id dokter kosong\n");
			error++;
		}
		if(praktek.getJam()==null) {
			System.out.println("jam kosong");
			sb.append("jam kosong\n");
			error++;
		}
		if(praktek.getState()==null) {
			System.out.println("state kosong");
			sb.append("state kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Praktek praktek)throws ValidationException{
		System.out.println("cek bk");
		if(praktekDao.isBkExist(praktek.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Praktek praktek)throws ValidationException{
		String s=findById(praktek.getId()).getKode();
		if(!praktek.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Praktek praktek) throws ValidationException{
		
		if(praktek.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Praktek praktek)throws ValidationException{
		
		valBkNotNull(praktek);
		valBkNotExist(praktek);
		System.out.println("bk not exist");
		valNonBk(praktek);
		praktekDao.save(praktek);
	}
	
	public void update(Praktek praktek)throws ValidationException{
		
		valIdNotNull(praktek);
		valIdExist(praktek.getId());
		valBkNotNull(praktek);
		valBkNotChange(praktek);
		valNonBk(praktek);
		praktekDao.save(praktek);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		praktekDao.delete(id);
	}
	
	public Praktek findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return praktekDao.findById(id);
	}
	
	public Praktek findByBk(Praktek praktek) {

		return praktekDao.findByBk(praktek.getKode());
	}
	public List<Praktek> findByAll( )throws ValidationException{
		return praktekDao.findAll();
	}
	public List<Praktek> findByFilter(String nama,String poli,String jam)throws ValidationException{
		return praktekDao.findByFilters(nama, poli, jam);
	}
	public Praktek findByKode(String kode) {

		return praktekDao.findByBk(kode);
	}
}
