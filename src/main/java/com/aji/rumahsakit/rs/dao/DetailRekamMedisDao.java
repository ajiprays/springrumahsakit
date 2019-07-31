package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailRekamMedis;

@Repository
public class DetailRekamMedisDao extends ParentDao{

	@Transactional
	public void save(DetailRekamMedis detailRekamMedis) {
		super.entityManager.merge(detailRekamMedis);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailRekamMedis detailRekamMedis = findById(id);
		super.entityManager.remove(detailRekamMedis);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailRekamMedis findById(UUID id) {	
		
		List<DetailRekamMedis> list = super.entityManager
                .createQuery("from DetailRekamMedis where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailRekamMedis();
		}
		else {
			return (DetailRekamMedis)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailRekamMedis> findAll() {	
		
		List<DetailRekamMedis> list = super.entityManager
                .createQuery("from DetailRekamMedis ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailRekamMedis> findByFilter() {	
		
		List<DetailRekamMedis> list = super.entityManager
                .createQuery("from DetailRekamMedis ")
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
	public DetailRekamMedis findByBk(String kode) {	
		
		List<DetailRekamMedis> list = super.entityManager
                .createQuery("from DetailRekamMedis where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailRekamMedis();
		}
		else {
			return (DetailRekamMedis)list.get(0);
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
