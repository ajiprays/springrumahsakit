package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.JenisObat;

@Repository
public class JenisObatDao extends ParentDao{

	@Transactional
	public void save(JenisObat jenisObat) {
		super.entityManager.merge(jenisObat);
	}
	
	@Transactional
	public void delete(UUID id) {
		JenisObat jenisObat = findById(id);
		super.entityManager.remove(jenisObat);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public JenisObat findById(UUID id) {	
		
		List<JenisObat> list = super.entityManager
                .createQuery("from JenisObat where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new JenisObat();
		}
		else {
			return (JenisObat)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<JenisObat> findAll() {	
		
		List<JenisObat> list = super.entityManager
                .createQuery("from JenisObat ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JenisObat> findByFilter() {	
		
		List<JenisObat> list = super.entityManager
                .createQuery("from JenisObat ")
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
	public JenisObat findByBk(String kode) {	
		
		List<JenisObat> list = super.entityManager
                .createQuery("from JenisObat where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new JenisObat();
		}
		else {
			return (JenisObat)list.get(0);
		}
	}
	
	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKode()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
