package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailTotalPembayaran;

@Repository
public class DetailTotalPembayaranDao extends ParentDao{

	@Transactional
	public void save(DetailTotalPembayaran detailTotalPembayaran) {
		super.entityManager.merge(detailTotalPembayaran);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailTotalPembayaran detailTotalPembayaran = findById(id);
		super.entityManager.remove(detailTotalPembayaran);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailTotalPembayaran findById(UUID id) {	
		
		List<DetailTotalPembayaran> list = super.entityManager
                .createQuery("from DetailTotalPembayaran where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailTotalPembayaran();
		}
		else {
			return (DetailTotalPembayaran)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailTotalPembayaran> findAll() {	
		
		List<DetailTotalPembayaran> list = super.entityManager
                .createQuery("from DetailTotalPembayaran ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailTotalPembayaran> findByFilter() {	
		
		List<DetailTotalPembayaran> list = super.entityManager
                .createQuery("from DetailTotalPembayaran ")
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
	public DetailTotalPembayaran findByBk(String kode) {	
		
		List<DetailTotalPembayaran> list = super.entityManager
                .createQuery("from DetailTotalPembayaran where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailTotalPembayaran();
		}
		else {
			return (DetailTotalPembayaran)list.get(0);
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
