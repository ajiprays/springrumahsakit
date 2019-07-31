package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.JenisKamar;

@Repository
public class JenisKamarDao extends ParentDao{

	@Transactional
	public void save(JenisKamar jenisKamar) {
		super.entityManager.merge(jenisKamar);
	}
	
	@Transactional
	public void delete(UUID id) {
		JenisKamar jenisKamar = findById(id);
		super.entityManager.remove(jenisKamar);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public JenisKamar findById(UUID id) {	
		
		List<JenisKamar> list = super.entityManager
                .createQuery("from JenisKamar where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new JenisKamar();
		}
		else {
			return (JenisKamar)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<JenisKamar> findAll() {	
		
		List<JenisKamar> list = super.entityManager
                .createQuery("from JenisKamar ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JenisKamar> findByFilter(String nama) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.jenis_kamar , p.harga , p.kode ");
		sb.append("FROM jenis_kamar p ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null") ) {
			sb.append(" AND p.jenis_kamar LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		
		List<JenisKamar> list=super.entityManager.createNativeQuery(sb.toString(),JenisKamar.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<JenisKamar>();
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
	public JenisKamar findByBk(String kode) {	
		
		List<JenisKamar> list = super.entityManager
                .createQuery("from JenisKamar where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new JenisKamar();
		}
		else {
			return (JenisKamar)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKode()==null) {
			System.out.println("null");
			return false;
		}else {
			return true;
		}	 
	}
	
}
