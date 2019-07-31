package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.DetailResepDao;
import com.aji.rumahsakit.rs.dao.ResepDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.DetailResep;
import com.aji.rumahsakit.rs.model.transaksi.Resep;

@Service
public class ResepService {

	@Autowired
	private ResepDao resepDao;
	
	@Autowired
	private DetailResepDao detResepDao;
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(resepDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!resepDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(Resep rm)throws ValidationException {
		
		if(rm.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(Resep r)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(r.getIdPemeriksaan()==null||r.getIdPemeriksaan().getId()==null) {
			System.out.println("id pasien kosong");
			sb.append("id pasien kosong \n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(Resep r)throws ValidationException{
		System.out.println("cek bk");
		if(resepDao.isBkExist(r.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(Resep r)throws ValidationException{
		String s=findById(r.getId()).getKode();
		if(!r.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(Resep r) throws ValidationException{
		
		if(r.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(Resep rs)throws ValidationException{
		
		valBkNotNull(rs);
		valBkNotExist(rs);
		System.out.println("bk not exist");
		valNonBk(rs);
		resepDao.save(rs);
	}
	
	public void saveResepAndDetail(Resep rs) throws Exception {


		List<DetailResep> detList=rs.getDetailReseps();
			
		rs.setDetailReseps(null);
		
		save(rs);
		Resep rsTemp=findByBk(rs);

		
		try {

			for(DetailResep d : detList) {
				
				d.setIdResep(rsTemp);
				saveDetail(d);			
			}
			
		}catch(ValidationException e)  {

			delete(rsTemp.getId());
			
			throw new ValidationException(e.getMessage());
		}
		catch(Exception e)  {

			delete(rsTemp.getId());
			
			throw new Exception(e.getMessage());
		}
		
	}

	public void updateResepAndDetail(Resep rs) throws ValidationException {
						
		try {

			for(DetailResep d : rs.getDetailReseps()) {			
								
				updateDetail(d);
					
			}

			update(rs);

		}catch(ValidationException e)  {

			throw new ValidationException(e.getMessage());
		}
	}
	
	public void update(Resep rs)throws ValidationException{
		
		valIdNotNull(rs);
		valIdExist(rs.getId());
		valBkNotNull(rs);
		valBkNotChange(rs);
		valNonBk(rs);
		resepDao.save(rs);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		resepDao.delete(id);
	}
	
	public Resep findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return resepDao.findById(id);
	}
	
	public Resep findByBk(Resep resep) {
		
		return resepDao.findByBk(resep.getKode());
	}
	
	public Resep findByKode(String kode) {
		
		return resepDao.findByBk(kode);
	}

	public List<Resep> findAll( )throws ValidationException{
		return resepDao.findAll();
	}

	public List<Resep> findByFilter(String pasien)throws ValidationException{
		return resepDao.findByFilter(pasien);
	}
	
///////////////////////////////////////////////////detail rekam medis//////////////////////////////////////////////////////////////////////////////////////

	public void valDetailIdNotExist(UUID id)throws ValidationException{
		
		if(detResepDao.isExist(id)) {
			throw new ValidationException("detail sudah ada");
		}
	}
	
	public void valDetailIdExist(UUID id)throws ValidationException{
		
		if(!detResepDao.isExist(id)) {
			throw new ValidationException("Data detail tidak ada");
		}
	}
	
	public void valDetailIdNotNull(DetailResep detResep)throws ValidationException {
		
		if(detResep.getId()==null) {
			throw new ValidationException("Id detail tidak boleh kosong");
		}
	}
	
	public void valDetailNonBk(DetailResep detResep)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(detResep.getIdResep()==null||detResep.getIdResep().getId()==null) {
			System.out.println("id resep kosong");
			sb.append("id resep kosong \n");
			error++;
		}
		if(detResep.getIdObat()==null||detResep.getIdObat().getId()==null) {
			System.out.println("id obat kosong");
			sb.append("id obat kosong\n");
			error++;
		}
		if(detResep.getKeterangan()==null) {
			System.out.println("keterangan kosong");
			sb.append("keterangan kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valDetailBkNotExist(DetailResep detResep)throws ValidationException{
		
		System.out.println("cek bk");
		
		if(detResepDao.isBkExist(detResep.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valDetailBkNotChange(DetailResep detResep)throws ValidationException{
		
		String s=findDetailById(detResep.getId()).getKode();
		
		if(!detResep.getKode().toString().equals(s.toString())) {
			System.out.println("kode berubah");
			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valDetailBkNotNull(DetailResep detResep) throws ValidationException{
		
		if(detResep.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveDetail(DetailResep detResep)throws ValidationException{
		
		valDetailBkNotNull(detResep);
		valDetailBkNotExist(detResep);
		System.out.println("bk not exist");
		valDetailNonBk(detResep);
		
		detResepDao.save(detResep);
		System.out.println("clear");
	}
	
	public void updateDetail(DetailResep detResep)throws ValidationException{

		valDetailIdNotNull(detResep);
		valDetailIdExist(detResep.getId());
		valDetailBkNotNull(detResep);
		valDetailBkNotChange(detResep);
		valDetailNonBk(detResep);
		detResepDao.save(detResep);
		System.out.println("updated");
	}
	
	public void deleteDetail(UUID id)throws ValidationException{
	
		valDetailIdExist(id);
		detResepDao.delete(id);
	}
	
	public DetailResep findDetailById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return detResepDao.findById(id);
	}
	
	public DetailResep findDetailByBk(DetailResep detResep) {

		return detResepDao.findByBk(detResep.getKode());
	}
	public List<DetailResep> findDetailByFilter( )throws ValidationException{
		return detResepDao.findAll();
	}
}
