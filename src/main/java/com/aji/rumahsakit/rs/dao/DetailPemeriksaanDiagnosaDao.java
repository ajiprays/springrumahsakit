package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailPemeriksaanDiagnosa;

@Repository
public class DetailPemeriksaanDiagnosaDao extends ParentDao{

	@Transactional
	public void save(DetailPemeriksaanDiagnosa detailPemeriksaan) {
		super.entityManager.merge(detailPemeriksaan);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailPemeriksaanDiagnosa detailPemeriksaan = findById(id);
		super.entityManager.remove(detailPemeriksaan);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailPemeriksaanDiagnosa findById(UUID id) {	
		
		List<DetailPemeriksaanDiagnosa> list = super.entityManager
                .createQuery("from DetailPemeriksaanDiagnosa where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailPemeriksaanDiagnosa();
		}
		else {
			return (DetailPemeriksaanDiagnosa)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailPemeriksaanDiagnosa> findAll() {	
		
		List<DetailPemeriksaanDiagnosa> list = super.entityManager
                .createQuery("from DetailPemeriksaanDiagnosa ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailPemeriksaanDiagnosa> findByFilter() {	
		
		List<DetailPemeriksaanDiagnosa> list = super.entityManager
                .createQuery("from DetailPemeriksaanDiagnosa ")
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
	public DetailPemeriksaanDiagnosa findByBk(String kode) {	
		
		List<DetailPemeriksaanDiagnosa> list = super.entityManager
                .createQuery("from DetailPemeriksaanDiagnosa where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailPemeriksaanDiagnosa();
		}
		else {
			return (DetailPemeriksaanDiagnosa)list.get(0);
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
