package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.BiayaPelayananPoli;

@Repository
public class BiayaPelayananPoliDao extends ParentDao{

	@Transactional
	public void save(BiayaPelayananPoli biayaPelayananPoli) {
		System.out.println("save");
		super.entityManager.merge(biayaPelayananPoli);
	}
	
	@Transactional
	public void delete(UUID id) {
		BiayaPelayananPoli biayaPelayananPoli = findById(id);
		super.entityManager.remove(biayaPelayananPoli);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public BiayaPelayananPoli findById(UUID id) {	
		System.out.println("find id dao");
		List<BiayaPelayananPoli> list = super.entityManager
                .createQuery("from BiayaPelayananPoli where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new BiayaPelayananPoli();
		}
		else {
			return (BiayaPelayananPoli)list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public BiayaPelayananPoli findByBk(String kode) {	
		
		List<BiayaPelayananPoli> list = super.entityManager
                .createQuery("from BiayaPelayananPoli where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new BiayaPelayananPoli();
		}
		else {
			return (BiayaPelayananPoli)list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BiayaPelayananPoli> findAll() {	
		
		List<BiayaPelayananPoli> list = super.entityManager
                .createQuery("from BiayaPelayananPoli ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BiayaPelayananPoli> findByFilter() {	
		
		List<BiayaPelayananPoli> list = super.entityManager
                .createQuery("from BiayaPelayananPoli ")
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
	
	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKode()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}
		 
	}
}
