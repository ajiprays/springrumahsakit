package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.DetailTransaksiObatDao;
import com.aji.rumahsakit.rs.dao.TransaksiObatDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.DetailTransaksiObat;
import com.aji.rumahsakit.rs.model.transaksi.TransaksiObat;

@Service
public class TransaksiObatService {

	@Autowired
	private TransaksiObatDao obatDao;
	
	@Autowired
	private DetailTransaksiObatDao detDao;
	
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
	
	public void valIdNotNull(TransaksiObat tobat)throws ValidationException {
		
		if(tobat.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(TransaksiObat tobat)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(tobat.getTanggal()==null) {
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
	
	public void valBkNotExist(TransaksiObat tobat)throws ValidationException{
		System.out.println("cek bk");
		if(obatDao.isBkExist(tobat.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(TransaksiObat tobat)throws ValidationException{
		String s=findById(tobat.getId()).getKode();
		if(!tobat.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(TransaksiObat tobat) throws ValidationException{
		
		if(tobat.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(TransaksiObat tobat)throws ValidationException{
		
		valBkNotNull(tobat);
		valBkNotExist(tobat);
		System.out.println("bk not exist");
		valNonBk(tobat);
		obatDao.save(tobat);
	}
	
	public void saveTrxObatAndDetail(TransaksiObat tobat) throws Exception {


		List<DetailTransaksiObat> detList=tobat.getDetailTransaksiObat();
			
		tobat.setDetailTransaksiObat(null);
		
		save(tobat);
		TransaksiObat obatTemp=findByBk(tobat);

		
		try {

			for(DetailTransaksiObat d : detList) {
				
				d.setTransaksiObat(obatTemp);
				saveDetail(d);			
			}
			
		}catch(ValidationException e)  {

			delete(obatTemp.getId());
			
			throw new ValidationException(e.getMessage());
		}
		catch(Exception e)  {

			delete(obatTemp.getId());
			
			throw new Exception(e.getMessage());
		}
		
	}

	public void updateTrxObatAndDetail(TransaksiObat obat) throws ValidationException {
						
		try {

			for(DetailTransaksiObat d : obat.getDetailTransaksiObat()) {			
								
				updateDetail(d);
					
			}

			update(obat);

		}catch(ValidationException e)  {

			throw new ValidationException(e.getMessage());
		}
	}
	
	public void update(TransaksiObat obat)throws ValidationException{
		
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
	
	public TransaksiObat findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return obatDao.findById(id);
	}
	
	public TransaksiObat findByBk(TransaksiObat obat) {
		
		return obatDao.findByBk(obat.getKode());
	}
	
	public TransaksiObat findByKode(String kode) {
		
		return obatDao.findByBk(kode);
	}
	
	public List<TransaksiObat> findAll( )throws ValidationException{
		return obatDao.findAll();
	}
	
	public List<TransaksiObat> findByFilter(String tanggal )throws ValidationException{
		return obatDao.findByFilter(tanggal);
	}
	
///////////////////////////////////////////////////detail transaksi obat//////////////////////////////////////////////////////////////////////////////////////

	public void valDetailIdNotExist(UUID id)throws ValidationException{
		
		if(detDao.isExist(id)) {
			throw new ValidationException("detail sudah ada");
		}
	}
	
	public void valDetailIdExist(UUID id)throws ValidationException{
		
		if(!detDao.isExist(id)) {
			throw new ValidationException("Data detail tidak ada");
		}
	}
	
	public void valDetailIdNotNull(DetailTransaksiObat det)throws ValidationException {
		
		if(det.getId()==null) {
			throw new ValidationException("Id detail tidak boleh kosong");
		}
	}
	
	public void valDetailNonBk(DetailTransaksiObat det)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

//		if(det.getIdResep()==null||det.getIdResep().getId()==null) {
//			
//			if(det.getIdObat()==null||det.getIdObat().getId()==null) {
//				System.out.println("id obat kosong");
//				sb.append("id obat kosong \n");
//				error++;				
//			}else {
//				System.out.println("id resep kosong");
//				sb.append("id resep kosong \n");
//				error++;
//			}		
//
//		}
		if(det.getTransaksiObat()==null||det.getTransaksiObat().getId()==null) {
			System.out.println("id pembelian kosong");
			sb.append("id pembelian kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valDetailBkNotExist(DetailTransaksiObat det)throws ValidationException{
		
		System.out.println("cek bk");
		
		if(detDao.isBkExist(det.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valDetailBkNotChange(DetailTransaksiObat det)throws ValidationException{
		
		String s=findDetailById(det.getId()).getKode();
		
		if(!det.getKode().toString().equals(s.toString())) {
			System.out.println("kode berubah");
			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valDetailBkNotNull(DetailTransaksiObat det) throws ValidationException{
		
		if(det.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveDetail(DetailTransaksiObat det)throws ValidationException{
		
		valDetailBkNotNull(det);
		valDetailBkNotExist(det);
		System.out.println("bk not exist");
		valDetailNonBk(det);
		
		detDao.save(det);
		System.out.println("clear");
	}
	
	public void updateDetail(DetailTransaksiObat det)throws ValidationException{

		valDetailIdNotNull(det);
		valDetailIdExist(det.getId());
		valDetailBkNotNull(det);
		valDetailBkNotChange(det);
		valDetailNonBk(det);
		detDao.save(det);
		System.out.println("updated");
	}
	
	public void deleteDetail(UUID id)throws ValidationException{
	
		valDetailIdExist(id);
		detDao.delete(id);
	}
	
	public DetailTransaksiObat findDetailById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return detDao.findById(id);
	}
	
	public DetailTransaksiObat findDetailByBk(DetailTransaksiObat det) {

		return detDao.findByBk(det.getKode());
	}
	
	public List<DetailTransaksiObat> findDetailByFilter( )throws ValidationException{
		return detDao.findByFilter();
	}
	
	public List<DetailTransaksiObat> findByAll( )throws ValidationException{
		return detDao.findAll();
	}
	
	public DetailTransaksiObat findDetailByKode(String kode) {

		return detDao.findByBk(kode);
	}
}
