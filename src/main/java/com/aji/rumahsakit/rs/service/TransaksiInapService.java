package com.aji.rumahsakit.rs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aji.rumahsakit.rs.dao.DetailTransaksiInapDao;
import com.aji.rumahsakit.rs.dao.SubDetailTransaksiInapDao;
import com.aji.rumahsakit.rs.dao.TransaksiInapDao;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.DetailTransaksiInap;
import com.aji.rumahsakit.rs.model.transaksi.SubDetailTransaksiInap;
import com.aji.rumahsakit.rs.model.transaksi.TransaksiInap;

@Service
public class TransaksiInapService {

	@Autowired
	private TransaksiInapDao inapDao;
	
	@Autowired
	private DetailTransaksiInapDao detInapDao;
	
	@Autowired
	private SubDetailTransaksiInapDao subInapDao;
	
	@Autowired
	private BiayaPelayananTambahanService tindakanService;
	
	@Autowired
	private ListKamarService kamarService;
	
	@Autowired
	private JenisKamarService jenisKamarService;
	
	
	public void valIdNotExist(UUID id)throws ValidationException{
		
		if(inapDao.isExist(id)) {
			throw new ValidationException("Data sudah ada");
		}
	}
	
	public void valIdExist(UUID id)throws ValidationException{
		
		if(!inapDao.isExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	public void valIdNotNull(TransaksiInap inap)throws ValidationException {
		
		if(inap.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	public void valNonBk(TransaksiInap inap)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(inap.getIdDokter()==null||inap.getIdDokter().getId()==null) {
			System.out.println("id dokter kosong");
			sb.append("id dokter kosong \n");
			error++;
		}
		if(inap.getIdPasien()==null||inap.getIdPasien().getId()==null) {
			System.out.println("id pasien kosong");
			sb.append("id pasien kosong\n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valBkNotExist(TransaksiInap inap)throws ValidationException{
		System.out.println("cek bk");
		if(inapDao.isBkExist(inap.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valBkNotChange(TransaksiInap inap)throws ValidationException{
		String s=findById(inap.getId()).getKode();
		if(!inap.getKode().toString().equals(s.toString())) {

			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valBkNotNull(TransaksiInap inap) throws ValidationException{
		
		if(inap.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void save(TransaksiInap inap)throws ValidationException{
		
		valBkNotNull(inap);
		valBkNotExist(inap);
		System.out.println("bk not exist");
		valNonBk(inap);
		inapDao.save(inap);
	}
	
	public void saveInapAndDetail(TransaksiInap inap) throws Exception,ValidationException {


		List<DetailTransaksiInap> det=inap.getDetailTransaksiInap();
			
		inap.setDetailTransaksiInap(null);

		save(inap);
		TransaksiInap inp=findByBk(inap);

		
		try {

			for(DetailTransaksiInap d : det) {
				
				d.setIdInap(inp);
				saveDetail(d);			
			}
			
		}catch(ValidationException e)  {

			delete(inp.getId());
			
			throw new ValidationException(e.getMessage());
		}
		catch(Exception e)  {

			delete(inp.getId());
			
			throw new Exception(e.getMessage());
		}
		
		
	}
	
	public void saveInapAndDetailAndSub(TransaksiInap inap) throws Exception, ValidationException {


		List<DetailTransaksiInap> det=inap.getDetailTransaksiInap();
			
		inap.setDetailTransaksiInap(null);

		save(inap);
		TransaksiInap inp=findByBk(inap);
		
		try {
			Long totalBiaya=0l;
			for(DetailTransaksiInap d : det) {
				List<SubDetailTransaksiInap> sub=d.getSubDetailTransaksiInap();
				System.out.println(sub.get(0).getIdTindakan().getKode());
				d.setSubDetailTransaksiInap(null);
				
				d.setIdInap(inp);
				saveDetail(d);			
				
				DetailTransaksiInap di=findDetailByBk(d);
				
				try {
					Long total=0l;
					for(SubDetailTransaksiInap s : sub) {
						s.setIdDetailTransaksiInap(di);
						saveSub(s);
						
						total+=findSubByBk(s).getTotal();
					}
					System.out.println("111111111111");
					totalBiaya=total+di.getBiaya();
					di.setBiaya(totalBiaya);
					updateDetail(di);
					
				}catch(ValidationException e) {
					System.out.println("error");
					deleteDetail(di.getId());
					delete(inp.getId());
					throw new ValidationException(e.getMessage());
				}
			}
			System.out.println("update in");
			inp.setTotalBiaya(totalBiaya);
			update(inp);
			System.out.println("clear");	
			
		}catch(ValidationException e)  {

			delete(inp.getId());
			
			throw new ValidationException(e.getMessage());
		}
		catch(Exception e)  {

			delete(inp.getId());
			
			throw new Exception(e.getMessage());
		}
		
	}

	public void updateInapAndDetailAndSub(TransaksiInap inap) throws ValidationException {
						
		try {

			for(DetailTransaksiInap d : inap.getDetailTransaksiInap()) {			
								
				try {
					
					for(SubDetailTransaksiInap s : d.getSubDetailTransaksiInap()) {
				
						updateSub(s);
					}
					
					updateDetail(d);
					
				}catch(ValidationException e) {
					
					throw new ValidationException(e.getMessage());
				}
			}

			update(inap);

		}catch(ValidationException e)  {

			throw new ValidationException(e.getMessage());
		}
	}
	
	public void update(TransaksiInap inap)throws ValidationException{
		
		valIdNotNull(inap);
		valIdExist(inap.getId());
		valBkNotNull(inap);
		valBkNotChange(inap);
		valNonBk(inap);
		inapDao.save(inap);
	}
	
	public void delete(UUID id)throws ValidationException{
	
		valIdExist(id);
		inapDao.delete(id);
	}
	
	public TransaksiInap findById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return inapDao.findById(id);
	}
	
	public TransaksiInap findByBk(TransaksiInap inap) {

		return inapDao.findByBk(inap.getKode());
	}

	public TransaksiInap findByKode(String kode) {

		return inapDao.findByBk(kode);
	}

	public List<TransaksiInap> findAll( )throws ValidationException{
		return inapDao.findAll();
	}

	public List<TransaksiInap> findByFilter(String pasien,String dokter )throws ValidationException{
		return inapDao.findByFilter(pasien, dokter);
	}
	
///////////////////////////////////////////////////detail inap//////////////////////////////////////////////////////////////////////////////////////

	public void valDetailIdNotExist(UUID id)throws ValidationException{
		
		if(detInapDao.isExist(id)) {
			throw new ValidationException("detail sudah ada");
		}
	}
	
	public void valDetailIdExist(UUID id)throws ValidationException{
		
		if(!detInapDao.isExist(id)) {
			throw new ValidationException("Data detail tidak ada");
		}
	}
	
	public void valDetailIdNotNull(DetailTransaksiInap detInap)throws ValidationException {
		
		if(detInap.getId()==null) {
			throw new ValidationException("Id detail tidak boleh kosong");
		}
	}
	
	public void valDetailNonBk(DetailTransaksiInap detInap)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(detInap.getIdKamar()==null||detInap.getIdKamar().getId()==null) {
			System.out.println("id kamar kosong");
			sb.append("id kamar kosong \n");
			error++;
		}
		if(detInap.getTglMasuk()==null) {
			System.out.println("tanggal masuk kosong");
			sb.append("tanggal masuk kosong\n");
			error++;
		}
		if(detInap.getTglKeluar()!=null) {
			if(detInap.getTglKeluar().before(detInap.getTglMasuk())) {
				System.out.println("tanggal keluar salah");
				sb.append("tanggal keluar salah\n");
				error++;
			}else {
				System.out.println("tanggal");

				Long harga=jenisKamarService.findById(kamarService.findById(detInap.getIdKamar().getId()).getIdJenisKamar().getId()).getHarga().longValue();
				Long total=Math.abs(detInap.getTglKeluar().getDate() - detInap.getTglMasuk().getDate())*harga;
				detInap.setBiaya(total);
			}
		}else {
			Long harga=jenisKamarService.findById(kamarService.findById(detInap.getIdKamar().getId()).getIdJenisKamar().getId()).getHarga().longValue();
			detInap.setBiaya(harga);			
		}

		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valDetailBkNotExist(DetailTransaksiInap detInap)throws ValidationException{
		System.out.println("cek bk");
		if(detInapDao.isBkExist(detInap.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valDetailBkNotChange(DetailTransaksiInap detInap)throws ValidationException{
		String s=findDetailById(detInap.getId()).getKode();
		if(!detInap.getKode().toString().equals(s.toString())) {
			System.out.println("kode berubah");
			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valDetailBkNotNull(DetailTransaksiInap detInap) throws ValidationException{
		
		if(detInap.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveDetail(DetailTransaksiInap detInap)throws ValidationException{
		
		valDetailBkNotNull(detInap);
		valDetailBkNotExist(detInap);
		System.out.println("bk not exist");
		valDetailNonBk(detInap);
		
//		Long harga=jenisKamarService.findById(kamarService.findById(detInap.getIdKamar().getId()).getIdJenisKamar().getId()).getHarga().longValue();
//		detInap.setBiaya(harga);
		detInapDao.save(detInap);
		System.out.println("clear");
	}
	
	public void updateDetail(DetailTransaksiInap detInap)throws ValidationException{
		System.out.println("update detail1");
		valDetailIdNotNull(detInap);
		System.out.println("update detail2");
		valDetailIdExist(detInap.getId());
		System.out.println("update detail3");
		valDetailBkNotNull(detInap);
		System.out.println("update detail4");
		valDetailBkNotChange(detInap);
		System.out.println("update detail5");
		valDetailNonBk(detInap);
		System.out.println("update detail6");
		detInapDao.save(detInap);
		System.out.println("updated");
	}
	
	public void deleteDetail(UUID id)throws ValidationException{
	
		valDetailIdExist(id);
		detInapDao.delete(id);
	}
	
	public DetailTransaksiInap findDetailById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return detInapDao.findById(id);
	}
	
	public DetailTransaksiInap findDetailByBk(DetailTransaksiInap detInap) {

		return detInapDao.findByBk(detInap.getKode());
	}
	public List<DetailTransaksiInap> findDetailByFilter( )throws ValidationException{
		return detInapDao.findAll();
	}
	
///////////////////////////////////////////////////sub detail inap///////////////////////////////////////////////////////////////////////////////////

	public void valSubIdNotExist(UUID id)throws ValidationException{
	
		if(subInapDao.isExist(id)) {
			throw new ValidationException("sub detail sudah ada");
		}
	}
	
	public void valSubIdExist(UUID id)throws ValidationException{
	
		if(!subInapDao.isExist(id)) {
			throw new ValidationException("Data sub detail tidak ada");
		}
	}
	
	public void valSubIdNotNull(SubDetailTransaksiInap subInap)throws ValidationException {
	
		if(subInap.getId()==null) {
				throw new ValidationException("Id sub detail tidak boleh kosong");
		}
	}
	
	public void valSubNonBk(SubDetailTransaksiInap subInap)throws ValidationException{
		
		StringBuilder sb=new StringBuilder();
		int error=0;
		
		if(subInap.getIdTindakan()==null||subInap.getIdTindakan().getId()==null) {
			System.out.println("id tindakan kosong");
			sb.append("id tindakan kosong \n");
			error++;
		}
		
		if(error>0) {
			System.out.println(sb);
			System.out.println("error : "+error);
			throw new ValidationException(sb.toString());
		}
		System.out.println("no error");
	}
	
	public void valSubBkNotExist(SubDetailTransaksiInap subInap)throws ValidationException{
		System.out.println("cek bk");
		if(subInapDao.isBkExist(subInap.getKode())) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	public void valSubBkNotChange(SubDetailTransaksiInap subInap)throws ValidationException{
		String s=findById(subInap.getId()).getKode();
		
		if(!subInap.getKode().toString().equals(s.toString())) {
		
			throw new ValidationException("Kode tidak boleh berubah");
		}
	}
	
	public void valSubBkNotNull(SubDetailTransaksiInap subInap) throws ValidationException{
		
		if(subInap.getKode()==null) {
			System.out.println("null");
			throw new ValidationException("Kode tidak boleh kosong");
		}
	}
	
	public void saveSub(SubDetailTransaksiInap subInap)throws ValidationException{
	
		valSubBkNotNull(subInap);
		valSubBkNotExist(subInap);
		System.out.println("bk not exist");
		valSubNonBk(subInap);
		
		Long harga=tindakanService.findById(subInap.getIdTindakan().getId()).getHarga().longValue();
		subInap.setTotal(harga);
		
		subInapDao.save(subInap);
	}
	
	public void updateSub(SubDetailTransaksiInap subInap)throws ValidationException{
	
		valSubIdNotNull(subInap);
		valSubIdExist(subInap.getId());
		valSubBkNotNull(subInap);
		valSubBkNotChange(subInap);
		valSubNonBk(subInap);
		subInapDao.save(subInap);
	}
	
	public void deleteSub(UUID id)throws ValidationException{
	
		valSubIdExist(id);
		subInapDao.delete(id);
	}
	
	public SubDetailTransaksiInap findSubById(UUID id)throws ValidationException{
		System.out.println("find by id");
		return subInapDao.findById(id);
	}
	
	public SubDetailTransaksiInap findSubByBk(SubDetailTransaksiInap subInap) {
	
		return subInapDao.findByBk(subInap.getKode());
	}
	
	public List<SubDetailTransaksiInap> findSubByFilter( )throws ValidationException{
		return subInapDao.findAll();
	}
}
