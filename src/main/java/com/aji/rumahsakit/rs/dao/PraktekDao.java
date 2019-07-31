package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.Praktek;

@Repository
public class PraktekDao extends ParentDao{

	@Transactional
	public void save(Praktek praktek) {
		super.entityManager.merge(praktek);
	}
	
	@Transactional
	public void delete(UUID id) {
		Praktek praktek = findById(id);
		super.entityManager.remove(praktek);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Praktek findById(UUID id) {	
		
		List<Praktek> list = super.entityManager
                .createQuery("from Praktek where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Praktek();
		}
		else {
			return (Praktek)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Praktek> findAll() {	
		
		List<Praktek> list = super.entityManager
                .createQuery("from Praktek ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Praktek> findByFilter() {	
		
		List<Praktek> list = super.entityManager
                .createQuery("from Praktek ")
                .getResultList();

		return list;
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
	public Praktek findByBk(String kode) {	
		
		List<Praktek> list = super.entityManager
                .createQuery("from Praktek where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Praktek();
		}
		else {
			return (Praktek)list.get(0);
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
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Praktek> findByFilters(String nama,String poli,String jam){
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_dokter , p.id_poli , p.jam , p.state ");
		sb.append("FROM praktek p ");
		sb.append(" JOIN dokter d ON p.id_dokter=d.id ");
		sb.append(" JOIN poli pl ON p.id_poli=pl.id ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null")) {
			sb.append(" AND d.nama LIKE '"+nama+"' ");
			System.out.println("not null dokter");
		}
		if(!poli.equals("null")) {
			sb.append(" AND pl.nama LIKE '"+poli+"' ");
			System.out.println("not null poli");
		}
		if(!jam.equals("null")) {
			sb.append(" AND p.jam = '"+jam+"' ");
			System.out.println("not null poli");
		}
			
		List<Praktek> list=super.entityManager.createNativeQuery(sb.toString(),Praktek.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Praktek>();
		}else {
			return list;
		}
		
	}
}
