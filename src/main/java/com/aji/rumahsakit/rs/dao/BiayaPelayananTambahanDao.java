package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.BiayaPelayananTambahan;

@Repository
public class BiayaPelayananTambahanDao extends ParentDao {

	@Transactional
	public void save(BiayaPelayananTambahan biayaPelayananTambahan) {
		super.entityManager.merge(biayaPelayananTambahan);
	}
	
	@Transactional
	public void delete(UUID id) {
		BiayaPelayananTambahan biayaPelayananTambahan = findById(id);
		super.entityManager.remove(biayaPelayananTambahan);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public BiayaPelayananTambahan findById(UUID id) {	
		
		List<BiayaPelayananTambahan> list = super.entityManager
                .createQuery("from BiayaPelayananTambahan where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new BiayaPelayananTambahan();
		}
		else {
			return (BiayaPelayananTambahan)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<BiayaPelayananTambahan> findAll() {	
		
		List<BiayaPelayananTambahan> list = super.entityManager
                .createQuery("from BiayaPelayananTambahan ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BiayaPelayananTambahan> findByFilters(String  nama,Long harga){
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.jenis_pelayanan , p.harga_pelayanan ");
		sb.append("FROM biaya_pelayanan_tambahan p ");
		sb.append(" WHERE 1=1 ");		
			
		if(nama!="null" || nama!=null) {
			sb.append(" AND p.jenis_pelayanan LIKE '%"+nama+"%' ");
			System.out.println("not null 1");
		}
		if(harga!=null) {
			sb.append(" AND p.harga_pelayanan = "+harga+" ");
			System.out.println("not null 2");
		}
			
		List<BiayaPelayananTambahan> list=super.entityManager.createNativeQuery(sb.toString(),BiayaPelayananTambahan.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<BiayaPelayananTambahan>();
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
	public BiayaPelayananTambahan findByBk(String kode) {	
		
		List<BiayaPelayananTambahan> list = super.entityManager
                .createQuery("from BiayaPelayananTambahan where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new BiayaPelayananTambahan();
		}
		else {
			return (BiayaPelayananTambahan)list.get(0);
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
