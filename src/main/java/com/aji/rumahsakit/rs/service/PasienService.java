package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.PasienDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.Pasien;

@Service
public class PasienService {

	@Autowired
	private PasienDao pasienDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(pasienDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!pasienDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Pasien pasien)throws ValidationException {
		
		if(pasien.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Pasien pasien)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(pasien.getNama()==null) {
			System.out.println("nama kosong");
			sb.append("jenis kosong \n");
			error++;
		}
		if(pasien.getJenisKelamin()==null) {
			System.out.println("jenis kelamin kosong");
			sb.append("jenis kelamin kosong\n");
			error++;
		}
		if(pasien.getAlamat()==null) {
			System.out.println("alamat kosong");
			sb.append("alamat kosong\n");
			error++;
		}
		if(pasien.getTglLahir()==null) {
			System.out.println("tgl lahir kosong");
			sb.append("tgl lahir kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Pasien pasien)throws ValidationException{
		System.out.println("cek bk");
		if(pasienDao.isBkExist(pasien.getKodePasien())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Pasien pasien)throws ValidationException{
		String s=findById(pasien.getId()).getKodePasien();
		if(!pasien.getKodePasien().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Pasien pasien) throws ValidationException{
		
		if(pasien.getKodePasien()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Pasien pasien)throws ValidationException{
		
		valBkNotNull(pasien);
		valBkNotExist(pasien);
		System.out.println("bk not exist");
		valNonBk(pasien);
		pasienDao.save(pasien);
	}
	
	public void update(Pasien pasien)throws ValidationException{
		
		valIdNotNull(pasien);
		valIdExist(pasien.getId());
		valBkNotNull(pasien);
		valBkNotChange(pasien);
		valNonBk(pasien);
		pasienDao.save(pasien);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		pasienDao.delete(id);
	}
	
	public Pasien findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return pasienDao.findById(id);
	}
	
	public Pasien findByBk(Pasien pasien) {

		return pasienDao.findByBk(pasien.getKodePasien());
	}
	
	public List<Pasien> findByFilter(String nama,String alamat,String tglLahir )throws ValidationException{
		System.out.println(nama);
		return pasienDao.findByFilters(nama,alamat,tglLahir);
	}
	
	public List<Pasien> findAll()throws ValidationException{

		return pasienDao.findAll();
	}
	
	public Pasien findByKode(String kode) {

		return pasienDao.findByBk(kode);
	}
}