package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailPemeriksaanTindakan;

@Repository
public class DetailPemeriksaanTindakanDao extends ParentDao{

	@Transactional
	public void save(DetailPemeriksaanTindakan detailPemeriksaan) {
		super.entityManager.merge(detailPemeriksaan);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailPemeriksaanTindakan detailPemeriksaan = findById(id);
		super.entityManager.remove(detailPemeriksaan);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailPemeriksaanTindakan findById(UUID id) {	
		
		List<DetailPemeriksaanTindakan> list = super.entityManager
                .createQuery("from DetailPemeriksaanTindakan where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailPemeriksaanTindakan();
		}
		else {
			return (DetailPemeriksaanTindakan)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailPemeriksaanTindakan> findAll() {	
		
		List<DetailPemeriksaanTindakan> list = super.entityManager
                .createQuery("from DetailPemeriksaanTindakan ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailPemeriksaanTindakan> findByFilter() {	
		
		List<DetailPemeriksaanTindakan> list = super.entityManager
                .createQuery("from DetailPemeriksaanTindakan ")
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
	public DetailPemeriksaanTindakan findByBk(String kode) {	
		
		List<DetailPemeriksaanTindakan> list = super.entityManager
                .createQuery("from DetailPemeriksaanTindakan where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailPemeriksaanTindakan();
		}
		else {
			return (DetailPemeriksaanTindakan)list.get(0);
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
