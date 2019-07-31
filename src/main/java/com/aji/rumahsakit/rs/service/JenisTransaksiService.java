package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.JenisTransaksiDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.JenisTransaksi;

@Service
public class JenisTransaksiService {

	@Autowired
	private JenisTransaksiDao jenisTransaksiDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(jenisTransaksiDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!jenisTransaksiDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(JenisTransaksi jenisTransaksi)throws ValidationException {
		
		if(jenisTransaksi.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(JenisTransaksi jenisTransaksi)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(jenisTransaksi.getNamaTable()==null) {
			System.out.println("nama kosong");
			sb.append("jenis kosong \n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(JenisTransaksi jenisTransaksi)throws ValidationException{
		System.out.println("cek bk");
		if(jenisTransaksiDao.isBkExist(jenisTransaksi.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(JenisTransaksi jenisTransaksi)throws ValidationException{
		String s=findById(jenisTransaksi.getId()).getKode();
		if(!jenisTransaksi.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(JenisTransaksi jenisTransaksi) throws ValidationException{
		
		if(jenisTransaksi.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(JenisTransaksi jenisTransaksi)throws ValidationException{
		
		valBkNotNull(jenisTransaksi);
		valBkNotExist(jenisTransaksi);
		System.out.println("bk not exist");
		valNonBk(jenisTransaksi);
		jenisTransaksiDao.save(jenisTransaksi);
	}
	
	public void update(JenisTransaksi jenisTransaksi)throws ValidationException{
		
		valIdNotNull(jenisTransaksi);
		valIdExist(jenisTransaksi.getId());
		valBkNotNull(jenisTransaksi);
		valBkNotChange(jenisTransaksi);
		valNonBk(jenisTransaksi);
		jenisTransaksiDao.save(jenisTransaksi);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		jenisTransaksiDao.delete(id);
	}
	
	public JenisTransaksi findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return jenisTransaksiDao.findById(id);
	}
	
	public JenisTransaksi findByBk(JenisTransaksi jenisTransaksi) {

		return jenisTransaksiDao.findByBk(jenisTransaksi.getKode());
	}
	public List<JenisTransaksi> findByFilter( )throws ValidationException{
		return jenisTransaksiDao.findAll();
	}

	public JenisTransaksi findByKode(String kode) {
		
		return jenisTransaksiDao.findByBk(kode);
	}
}
