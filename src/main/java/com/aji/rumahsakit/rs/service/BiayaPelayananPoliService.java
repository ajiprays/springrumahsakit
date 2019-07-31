package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.BiayaPelayananPoliDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.BiayaPelayananPoli;

@Service
public class BiayaPelayananPoliService {
	
	@Autowired
	private BiayaPelayananPoliDao biayaPelayananPoliDao;

	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(biayaPelayananPoliDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!biayaPelayananPoliDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(BiayaPelayananPoli biayaPelayananPoli)throws ValidationException {
		
		if(biayaPelayananPoli.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(BiayaPelayananPoli biayaPelayananPoli)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(biayaPelayananPoli.getIdPoli()==null||biayaPelayananPoli.getIdPoli().getId()==null) {
			System.out.println("id poli kosong");
			sb.append("id poli kosong\n");
			error++;
		}
		if(biayaPelayananPoli.getHarga()==null) {
			System.out.println("harga kosong");
			sb.append("harga kosong \n");
			error++;
		}

		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(BiayaPelayananPoli biayaPelayananPoli)throws ValidationException{
		System.out.println("cek bk");
		if(biayaPelayananPoliDao.isBkExist(biayaPelayananPoli.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(BiayaPelayananPoli biayaPelayananPoli)throws ValidationException{
		System.out.println(biayaPelayananPoli.getKode());
		String s=findById(biayaPelayananPoli.getId()).getKode();
		System.out.println(s);
		if(!biayaPelayananPoli.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(BiayaPelayananPoli biayaPelayananPoli) throws ValidationException{
		
		if(biayaPelayananPoli.getKode()==null) {
			System.out.println("null");
			System.out.println(biayaPelayananPoli.getKode());
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(BiayaPelayananPoli biayaPelayananPoli)throws ValidationException{
		
//		valIdNotExist(biayaPelayananPoli.getId());
		valBkNotNull(biayaPelayananPoli);
		valBkNotExist(biayaPelayananPoli);
		System.out.println("bk not exist");
		valNonBk(biayaPelayananPoli);
		biayaPelayananPoliDao.save(biayaPelayananPoli);
	}
	
	public void update(BiayaPelayananPoli biayaPelayananPoli )throws ValidationException{
		
		valIdNotNull(biayaPelayananPoli);
		valIdExist(biayaPelayananPoli.getId());
		valBkNotNull(biayaPelayananPoli);
		valBkNotChange(biayaPelayananPoli);
		valNonBk(biayaPelayananPoli);
		biayaPelayananPoliDao.save(biayaPelayananPoli);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		biayaPelayananPoliDao.delete(id);
	}
	
	public BiayaPelayananPoli findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return biayaPelayananPoliDao.findById(id);
	}
	
	public BiayaPelayananPoli findByBk(BiayaPelayananPoli bpl) {

		return biayaPelayananPoliDao.findByBk(bpl.getKode());
	}
	
	public List<BiayaPelayananPoli> findAll()throws ValidationException{
		return biayaPelayananPoliDao.findAll();
	}
	
	public BiayaPelayananPoli findByKode(String kode) {

		return biayaPelayananPoliDao.findByBk(kode);
	}
	
	public List<BiayaPelayananPoli> findByFilter( )throws ValidationException{
		return biayaPelayananPoliDao.findByFilter();
	}
}