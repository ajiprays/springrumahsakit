package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.RekamMedis;

@Repository
public class RekamMedisDao extends ParentDao{

	@Transactional
	public void save(RekamMedis rekamMedis) {
		super.entityManager.merge(rekamMedis);
	}
	
	@Transactional
	public void delete(UUID id) {
		RekamMedis rekamMedis = findById(id);
		super.entityManager.remove(rekamMedis);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public RekamMedis findById(UUID id) {	
		
		List<RekamMedis> list = super.entityManager
                .createQuery("from RekamMedis where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new RekamMedis();
		}
		else {
			return (RekamMedis)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<RekamMedis> findAll() {	
		
		List<RekamMedis> list = super.entityManager
                .createQuery("from RekamMedis ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RekamMedis> findByFilter(String pasien,String dokter,String tanggal) {	

		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_pasien ");
		sb.append("FROM rekam_medis p ");
		sb.append(" JOIN pasien ps ON p.id_pasien=ps.id ");
		sb.append(" JOIN detail_rekam_medis det ON p.id=det.id_rekam_medis ");
		sb.append(" JOIN dokter d ON det.id_dokter=d.id ");
		sb.append(" WHERE 1=1 ");		
			
		if(!pasien.equals("null")) {
			sb.append(" AND ps.nama LIKE '%"+pasien+"%' ");
			System.out.println("not null nama");
		}
		if(!dokter.equals("null")) {
			sb.append(" AND d.nama LIKE '%"+dokter+"%' ");
			System.out.println("not null alamat");
		}
		if(!tanggal.equals("null")) {
			sb.append(" AND det.tanggal = '"+tanggal+"' ");
			System.out.println("not null tgl");
		}
			
		List<RekamMedis> list=super.entityManager.createNativeQuery(sb.toString(),RekamMedis.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<RekamMedis>();
		}else {
			return list;
		}
	}
	
	public boolean isExist(UUID id) {
		
		if(findById(id).getId()==null) {
			return false;
		}else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public RekamMedis findByBk(String kode) {	
		
		List<RekamMedis> list = super.entityManager
                .createQuery("from RekamMedis where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new RekamMedis();
		}
		else {
			return (RekamMedis)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKode()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
}