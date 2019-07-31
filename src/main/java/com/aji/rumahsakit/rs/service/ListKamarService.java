package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.ListKamarDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.ListKamar;

@Service
public class ListKamarService {

	@Autowired
	private ListKamarDao listKamarDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(listKamarDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!listKamarDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(ListKamar listKamar)throws ValidationException {
		
		if(listKamar.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(ListKamar listKamar)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(listKamar.getIdJenisKamar()==null||listKamar.getIdJenisKamar().getId()==null) {
			System.out.println("jenis kosong");
			sb.append("jenis kosong \n");
			error++;
		}
		if(listKamar.getLantai()==null) {
			System.out.println("lantai kosong");
			sb.append("lantai kosong\n");
			error++;
		}
		if(listKamar.getNama()==null) {
			System.out.println("nama kosong");
			sb.append("nama kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(ListKamar listKamar)throws ValidationException{
		System.out.println("cek bk");
		if(listKamarDao.isBkExist(listKamar.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(ListKamar listKamar)throws ValidationException{
		String s=findById(listKamar.getId()).getKode();
		if(!listKamar.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(ListKamar listKamar) throws ValidationException{
		
		if(listKamar.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(ListKamar listKamar)throws ValidationException{
		
		valBkNotNull(listKamar);
		valBkNotExist(listKamar);
		System.out.println("bk not exist");
		valNonBk(listKamar);
		listKamarDao.save(listKamar);
	}
	
	public void update(ListKamar listKamar)throws ValidationException{
		
		valIdNotNull(listKamar);
		valIdExist(listKamar.getId());
		valBkNotNull(listKamar);
		valBkNotChange(listKamar);
		valNonBk(listKamar);
		listKamarDao.save(listKamar);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		listKamarDao.delete(id);
	}
	
	public ListKamar findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return listKamarDao.findById(id);
	}
	
	public ListKamar findByBk(ListKamar listKamar) {

		return listKamarDao.findByBk(listKamar.getKode());
	}
	
	public List<ListKamar> findAll()throws ValidationException{
		return listKamarDao.findAll();
	}
	
	public ListKamar findByKode(String kode) {

		return listKamarDao.findByBk(kode);
	}
	public List<ListKamar> findByFilter(String nama,String lantai,String jenis )throws ValidationException{
		return listKamarDao.findByFilter(nama, lantai, jenis);
	}
}