package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.Resep;

@Repository
public class ResepDao extends ParentDao{

	@Transactional
	public void save(Resep resep) {
		super.entityManager.merge(resep);
	}
	
	@Transactional
	public void delete(UUID id) {
		Resep resep = findById(id);
		super.entityManager.remove(resep);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Resep findById(UUID id) {	
		
		List<Resep> list = super.entityManager
                .createQuery("from Resep where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Resep();
		}
		else {
			return (Resep)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Resep> findAll() {	
		
		List<Resep> list = super.entityManager
                .createQuery("from Resep ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Resep> findByFilter(String pasien) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_pemeriksaan ");
		sb.append(" FROM resep p ");
		sb.append(" JOIN pemeriksaan n ON p.id_pemeriksaan=n.id ");
		sb.append(" JOIN registrasi r ON n.id_registrasi=r.id ");
		sb.append(" JOIN pasien ps ON r.id_pasien=ps.id ");
		sb.append(" WHERE 1=1 ");		
			
		if(!pasien.equals("null")) {
			sb.append(" AND ps.nama LIKE '%"+pasien+"%' ");
			System.out.println("not null nama");
		}
			
		List<Resep> list=super.entityManager.createNativeQuery(sb.toString(),Resep.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Resep>();
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
	public Resep findByBk(String kode) {	
		
		List<Resep> list = super.entityManager
                .createQuery("from Resep where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Resep();
		}
		else {
			return (Resep)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getIdPemeriksaan()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
}
