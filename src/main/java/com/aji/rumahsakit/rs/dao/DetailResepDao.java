package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailResep;

@Repository
public class DetailResepDao extends ParentDao{

	@Transactional
	public void save(DetailResep detailResep) {
		super.entityManager.merge(detailResep);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailResep detailResep = findById(id);
		super.entityManager.remove(detailResep);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailResep findById(UUID id) {	
		
		List<DetailResep> list = super.entityManager
                .createQuery("from DetailResep where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailResep();
		}
		else {
			return (DetailResep)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailResep> findAll() {	
		
		List<DetailResep> list = super.entityManager
                .createQuery("from DetailResep ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailResep> findByFilter() {	
		
		List<DetailResep> list = super.entityManager
                .createQuery("from DetailResep ")
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
	public DetailResep findByBk(String kode) {	
		
		List<DetailResep> list = super.entityManager
                .createQuery("from DetailResep where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailResep();
		}
		else {
			return (DetailResep)list.get(0);
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
