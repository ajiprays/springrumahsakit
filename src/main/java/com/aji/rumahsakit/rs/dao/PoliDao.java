package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.Poli;

@Repository
public class PoliDao extends ParentDao{

	@Transactional
	public void save(Poli poli) {
		super.entityManager.merge(poli);
	}
	
	@Transactional
	public void delete(UUID id) {
		Poli poli = findById(id);
		super.entityManager.remove(poli);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Poli findById(UUID id) {	
		
		List<Poli> list = super.entityManager
                .createQuery("from Poli where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Poli();
		}
		else {
			return (Poli)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Poli> findAll() {	
		
		List<Poli> list = super.entityManager
                .createQuery("from Poli ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Poli> findByFilter(String nama) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.nama,p.state ");
		sb.append("FROM poli p ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null")) {
			sb.append(" AND p.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
			
		List<Poli> list=super.entityManager.createNativeQuery(sb.toString(),Poli.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Poli>();
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
	public Poli findByBk(String kode) {	
		
		List<Poli> list = super.entityManager
                .createQuery("from Poli where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Poli();
		}
		else {
			return (Poli)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKodePoli()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
}
