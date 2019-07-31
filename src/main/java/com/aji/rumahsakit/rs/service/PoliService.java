package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.PoliDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.Poli;

@Service
public class PoliService {

	@Autowired
	private PoliDao poliDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(poliDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!poliDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Poli poli)throws ValidationException {
		
		if(poli.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Poli poli)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(poli.getNama()==null) {
			System.out.println("nama kosong");
			sb.append("nama kosong \n");
			error++;
		}
		if(poli.getState()==null) {
			System.out.println("status kosong");
			sb.append("status kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Poli poli)throws ValidationException{
		System.out.println("cek bk");
		if(poliDao.isBkExist(poli.getKodePoli())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Poli poli)throws ValidationException{
		String s=findById(poli.getId()).getKodePoli();
		if(!poli.getKodePoli().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Poli poli) throws ValidationException{
		
		if(poli.getKodePoli()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Poli poli)throws ValidationException{
		
		valBkNotNull(poli);
		valBkNotExist(poli);
		System.out.println("bk not exist");
		valNonBk(poli);
		poliDao.save(poli);
	}
	
	public void update(Poli poli)throws ValidationException{
		
		valIdNotNull(poli);
		valIdExist(poli.getId());
		valBkNotNull(poli);
		valBkNotChange(poli);
		valNonBk(poli);
		poliDao.save(poli);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		poliDao.delete(id);
	}
	
	public Poli findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return poliDao.findById(id);
	}
	
	public Poli findByBk(Poli poli) {

		return poliDao.findByBk(poli.getKodePoli());
	}

	public List<Poli> findAll( )throws ValidationException{
		return poliDao.findAll();
	}
	public Poli findByKode(String kode) {

		return poliDao.findByBk(kode);
	}
	public List<Poli> findByFilter(String nama )throws ValidationException{
		return poliDao.findByFilter(nama);
	}
}
