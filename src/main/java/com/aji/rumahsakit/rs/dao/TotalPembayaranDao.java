package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.TotalPembayaran;

@Repository
public class TotalPembayaranDao extends ParentDao{

	@Transactional
	public void save(TotalPembayaran totalPembayaran) {
		super.entityManager.merge(totalPembayaran);
	}
	
	@Transactional
	public void delete(UUID id) {
		TotalPembayaran totalPembayaran = findById(id);
		super.entityManager.remove(totalPembayaran);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public TotalPembayaran findById(UUID id) {	
		
		List<TotalPembayaran> list = super.entityManager
                .createQuery("from TotalPembayaran where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new TotalPembayaran();
		}
		else {
			return (TotalPembayaran)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<TotalPembayaran> findAll() {	
		
		List<TotalPembayaran> list = super.entityManager
                .createQuery("from TotalPembayaran ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TotalPembayaran> findByFilter() {	
		
		List<TotalPembayaran> list = super.entityManager
                .createQuery("from Category ")
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
	public TotalPembayaran findByBk(String kode) {	
		
		List<TotalPembayaran> list = super.entityManager
                .createQuery("from TotalPembayaran where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new TotalPembayaran();
		}
		else {
			return (TotalPembayaran)list.get(0);
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
