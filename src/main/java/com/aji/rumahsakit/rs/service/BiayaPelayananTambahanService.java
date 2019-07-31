package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.BiayaPelayananTambahanDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.BiayaPelayananTambahan;

@Service
public class BiayaPelayananTambahanService {

	@Autowired
	private BiayaPelayananTambahanDao biayaPelayananTambahanDao;
	

	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(biayaPelayananTambahanDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!biayaPelayananTambahanDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(BiayaPelayananTambahan biayaPelayananTambahan)throws ValidationException {
		
		if(biayaPelayananTambahan.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}

	public void valNonBk(BiayaPelayananTambahan biayaPelayananTambahan)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(biayaPelayananTambahan.getNamaPelayanan()==null) {
			System.out.println("id jenis tindakan kosong");
			sb.append("jenis tindakan kosong \n");
			error++;
		}
		if(biayaPelayananTambahan.getHarga()==null) {
			System.out.println("id poli kosong");
			sb.append("id poli kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}

	public void valBkNotExist(BiayaPelayananTambahan biayaPelayananTambahan)throws ValidationException{

		System.out.println("cek bk");
		if(biayaPelayananTambahanDao.isBkExist(biayaPelayananTambahan.getKode())) {
			throw new ValidationException("Data sudah ada");
		}	
	}
	
	public void valBkNotChange(BiayaPelayananTambahan biayaPelayananTambahan)throws ValidationException{
		System.out.println(biayaPelayananTambahan.getKode());
		String s=findById(biayaPelayananTambahan.getId()).getKode();
		System.out.println(s);
		if(!biayaPelayananTambahan.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(BiayaPelayananTambahan biayaPelayananTambahan) throws ValidationException{
		
		if(biayaPelayananTambahan.getKode()==null) {
			System.out.println("null");
			System.out.println(biayaPelayananTambahan.getKode());
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	public void save(BiayaPelayananTambahan biayaPelayananTambahan )throws ValidationException{
		
		valBkNotNull(biayaPelayananTambahan);
		valBkNotExist(biayaPelayananTambahan);
		System.out.println("bk not exist");
		valNonBk(biayaPelayananTambahan);
		biayaPelayananTambahanDao.save(biayaPelayananTambahan);
	}
	
	public void delete( UUID id)throws ValidationException{
		valIdExist(id);
		biayaPelayananTambahanDao.delete(id);
	}
	
	public void update(BiayaPelayananTambahan biayaPelayananTambahan )throws ValidationException{

		valIdNotNull(biayaPelayananTambahan);
		valIdExist(biayaPelayananTambahan.getId());
		valBkNotNull(biayaPelayananTambahan);
		valBkNotChange(biayaPelayananTambahan);
		valNonBk(biayaPelayananTambahan);
		biayaPelayananTambahanDao.save(biayaPelayananTambahan);
	}
	
	public BiayaPelayananTambahan findById( UUID id)throws ValidationException{
		return biayaPelayananTambahanDao.findById(id);
	}
	
	public BiayaPelayananTambahan findByBk(BiayaPelayananTambahan bpt) {

		return biayaPelayananTambahanDao.findByBk(bpt.getKode());
	}
	
	public List<BiayaPelayananTambahan> findByFilter(String nama,Long harga)throws ValidationException{
		return biayaPelayananTambahanDao.findByFilters(nama, harga);
	}
	
	public BiayaPelayananTambahan findByKode(String kode) {

		return biayaPelayananTambahanDao.findByBk(kode);
	}
	
	public List<BiayaPelayananTambahan> findAll()throws ValidationException{
		return biayaPelayananTambahanDao.findAll();
	}
}
