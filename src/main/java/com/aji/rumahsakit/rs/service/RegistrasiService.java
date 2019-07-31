package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.RegistrasiDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.Registrasi;

@Service
public class RegistrasiService {
	
	@Autowired
	private RegistrasiDao regDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(regDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!regDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Registrasi reg)throws ValidationException {
		
		if(reg.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Registrasi reg)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(reg.getIdPasien()==null||reg.getIdPasien().getId()==null) {
			System.out.println("id pasien kosong");
			sb.append("id pasien kosong \n");
			error++;
		}
		if(reg.getIdPraktek()==null||reg.getIdPraktek().getId()==null) {
			System.out.println("id praktek kosong");
			sb.append("id praktek kosong\n");
			error++;
		}
		if(reg.getTanggal()==null) {
			System.out.println("tanggal kosong");
			sb.append("tanggal kosong \n");
			error++;
		}

		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Registrasi reg)throws ValidationException{
		System.out.println("cek bk");
		if(regDao.isBkExist(reg.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Registrasi reg)throws ValidationException{
		System.out.println(reg.getKode());
		String s=findById(reg.getId()).getKode();
		System.out.println(s);
		if(!reg.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Registrasi reg) throws ValidationException{
		
		if(reg.getKode()==null) {
			System.out.println("null");
			System.out.println(reg.getKode());
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Registrasi reg)throws ValidationException{
		
//		valIdNotExist(biayaPelayananPoli.getId());
		valBkNotNull(reg);
		valBkNotExist(reg);
		System.out.println("bk not exist");
		valNonBk(reg);
		regDao.save(reg);
	}
	
	public void update(Registrasi reg )throws ValidationException{
		
		valIdNotNull(reg);
		valIdExist(reg.getId());
		valBkNotNull(reg);
		valBkNotChange(reg);
		valNonBk(reg);
		regDao.save(reg);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		regDao.delete(id);
	}
	
	public Registrasi findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return regDao.findById(id);
	}
	
	public Registrasi findByBk(Registrasi reg) {

		return regDao.findByBk(reg.getKode());
	}
	
	public Registrasi findByKode(String kode) {

		return regDao.findByBk(kode);
	}
	
	public List<Registrasi> findAll( )throws ValidationException{
		return regDao.findAll();
	}
	
	public List<Registrasi> findByFilter(String nama,String tanggal )throws ValidationException{
		return regDao.findByFilter(nama,tanggal);
	}
}
