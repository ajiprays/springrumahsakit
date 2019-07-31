package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.ObatDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.Obat;

@Service
public class ObatService {

	@Autowired
	private ObatDao obatDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(obatDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!obatDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Obat obat)throws ValidationException {
		
		if(obat.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Obat obat)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(obat.getIdJenis()==null||obat.getIdJenis().getId()==null) {
			System.out.println("jenis kosong");
			sb.append("jenis kosong \n");
			error++;
		}
		if(obat.getHarga()==null) {
			System.out.println("harga kosong");
			sb.append("harga kosong\n");
			error++;
		}
		if(obat.getNama()==null) {
			System.out.println("harga kosong");
			sb.append("nama kosong\n");
			error++;
		}
		if(obat.getState()==null) {
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
	
	public void valBkNotExist(Obat obat)throws ValidationException{
		System.out.println("cek bk");
		if(obatDao.isBkExist(obat.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Obat obat)throws ValidationException{
		String s=findById(obat.getId()).getKode();
		if(!obat.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Obat obat) throws ValidationException{
		
		if(obat.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Obat obat)throws ValidationException{
		
		valBkNotNull(obat);
		valBkNotExist(obat);
		System.out.println("bk not exist");
		valNonBk(obat);
		obatDao.save(obat);
	}
	
	public void update(Obat obat)throws ValidationException{
		
		valIdNotNull(obat);
		valIdExist(obat.getId());
		valBkNotNull(obat);
		valBkNotChange(obat);
		valNonBk(obat);
		obatDao.save(obat);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		obatDao.delete(id);
	}
	
	public Obat findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return obatDao.findById(id);
	}
	
	public Obat findByBk(Obat obat) {

		return obatDao.findByBk(obat.getKode());
	}
	
	public List<Obat> findAll( )throws ValidationException{
		return obatDao.findAll();
	}
	
	public Obat findKode(String kode) {

		return obatDao.findByBk(kode);
	}
	public List<Obat> findByFilter(String nama)throws ValidationException{
		return obatDao.findByFilter(nama);
	}
}