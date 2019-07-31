package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.JenisObatDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.JenisObat;

@Service
public class JenisObatService {

	@Autowired
	private JenisObatDao jenisObatDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(jenisObatDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!jenisObatDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(JenisObat jenisObat)throws ValidationException {
		
		if(jenisObat.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(JenisObat jenisObat)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(jenisObat.getNama()==null) {
			System.out.println("nama kosong");
			sb.append("nama kosong \n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(JenisObat jenisObat)throws ValidationException{
		System.out.println("cek bk");
		if(jenisObatDao.isBkExist(jenisObat.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(JenisObat jenisObat)throws ValidationException{
		String s=findById(jenisObat.getId()).getKode();
		if(!jenisObat.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(JenisObat jenisObat) throws ValidationException{
		
		if(jenisObat.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(JenisObat jenisObat)throws ValidationException{
		
		valBkNotNull(jenisObat);
		valBkNotExist(jenisObat);
		System.out.println("bk not exist");
		valNonBk(jenisObat);
		jenisObatDao.save(jenisObat);
	}
	
	public void update(JenisObat jenisObat)throws ValidationException{
		
		valIdNotNull(jenisObat);
		valIdExist(jenisObat.getId());
		valBkNotNull(jenisObat);
		valBkNotChange(jenisObat);
		valNonBk(jenisObat);
		jenisObatDao.save(jenisObat);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		jenisObatDao.delete(id);
	}
	
	public JenisObat findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return jenisObatDao.findById(id);
	}
	
	public JenisObat findByBk(JenisObat jenisObat) {

		return jenisObatDao.findByBk(jenisObat.getKode());
	}
	
	public List<JenisObat> findAll( )throws ValidationException{
		return jenisObatDao.findAll();
	}
	
	public JenisObat findByKode(String kode) {

		return jenisObatDao.findByBk(kode);
	}
	
	public List<JenisObat> findByFilter( )throws ValidationException{
		return jenisObatDao.findByFilter();
	}
}
