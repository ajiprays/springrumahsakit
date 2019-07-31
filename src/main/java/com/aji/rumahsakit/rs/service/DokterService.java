package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.DokterDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.Dokter;

@Service
public class DokterService {

	@Autowired
	private DokterDao dokterDao;
	
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(dokterDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!dokterDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Dokter dokter)throws ValidationException {
		
		if(dokter.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Dokter dokter)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(dokter.getNama()==null) {
			System.out.println("id jenis tindakan kosong");
			sb.append("jenis tindakan kosong \n");
			error++;
		}
		if(dokter.getJenisKelamin()==null) {
			System.out.println("id poli kosong");
			sb.append("jenis kelamin kosong\n");
			error++;
		}
		if(dokter.getAlamat()==null) {
			System.out.println("harga kosong");
			sb.append("alamat kosong \n");
			error++;
		}
		if(dokter.getState()==null) {
			System.out.println("harga kosong");
			sb.append("state kosong \n");
			error++;
		}

		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Dokter dokter)throws ValidationException{
		System.out.println("cek bk");
		if(dokterDao.isBkExist(dokter.getSip())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Dokter dokter)throws ValidationException{
		String s=findById(dokter.getId()).getSip();
		if(!dokter.getSip().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Dokter dokter) throws ValidationException{
		
		if(dokter.getSip()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Dokter dokter)throws ValidationException{
		
		valBkNotNull(dokter);
		valBkNotExist(dokter);
		System.out.println("bk not exist");
		valNonBk(dokter);
		dokterDao.save(dokter);
	}
	
	public void update(Dokter dokter )throws ValidationException{
		
		valIdNotNull(dokter);
		valIdExist(dokter.getId());
		valBkNotNull(dokter);
		valBkNotChange(dokter);
		valNonBk(dokter);
		dokterDao.save(dokter);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		dokterDao.delete(id);
	}
	
	public Dokter findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return dokterDao.findById(id);
	}
	
	public Dokter findByBk(Dokter dokter) {

		return dokterDao.findByBk(dokter.getSip());
	}
	public List<Dokter> findByAll( )throws ValidationException{
		return dokterDao.findAll();
	}
	
	public List<Dokter> findByFilters(String nama ,String alamat,String state )throws ValidationException{
		return dokterDao.findByFilters(nama, alamat, state);
	}
	
	public Dokter findBykode(String kode) {

		return dokterDao.findByBk(kode);
	}
}