package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.JenisKamarDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.JenisKamar;

@Service
public class JenisKamarService {

	@Autowired
	private JenisKamarDao jenisKamarDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(jenisKamarDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!jenisKamarDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(JenisKamar jenisKamar)throws ValidationException {
		
		if(jenisKamar.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(JenisKamar jenisKamar)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(jenisKamar.getJenis()==null) {
			System.out.println("jenis kosong");
			sb.append("jenis kosong \n");
			error++;
		}
		if(jenisKamar.getHarga()==null) {
			System.out.println("harga kosong");
			sb.append("harga kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(JenisKamar jenisKamar)throws ValidationException{
		System.out.println("cek bk");
		if(jenisKamarDao.isBkExist(jenisKamar.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(JenisKamar jenisKamar)throws ValidationException{
		String s=findById(jenisKamar.getId()).getKode();
		if(!jenisKamar.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(JenisKamar jenisKamar) throws ValidationException{
		
		if(jenisKamar.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(JenisKamar jenisKamar)throws ValidationException{
		
		valBkNotNull(jenisKamar);
		valBkNotExist(jenisKamar);
		System.out.println("bk not exist");
		valNonBk(jenisKamar);
		jenisKamarDao.save(jenisKamar);
	}
	
	public void update(JenisKamar jenisKamar)throws ValidationException{
		
		valIdNotNull(jenisKamar);
		valIdExist(jenisKamar.getId());
		valBkNotNull(jenisKamar);
		valBkNotChange(jenisKamar);
		valNonBk(jenisKamar);
		jenisKamarDao.save(jenisKamar);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		jenisKamarDao.delete(id);
	}
	
	public JenisKamar findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return jenisKamarDao.findById(id);
	}
	
	public JenisKamar findByBk(JenisKamar jenisKamar) {

		return jenisKamarDao.findByBk(jenisKamar.getKode());
	}
	
	public List<JenisKamar> findAll()throws ValidationException{
		return jenisKamarDao.findAll();
	}
	
	public JenisKamar findByKode(String kode) {

		return jenisKamarDao.findByBk(kode);
	}
	
	public List<JenisKamar> findByFilter(String nama)throws ValidationException{
		return jenisKamarDao.findByFilter(nama);
	}

}