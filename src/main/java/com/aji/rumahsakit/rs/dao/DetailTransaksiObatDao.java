package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailTransaksiObat;

@Repository
public class DetailTransaksiObatDao extends ParentDao{

	@Transactional
	public void save(DetailTransaksiObat detailTransaksiObat) {
		super.entityManager.merge(detailTransaksiObat);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailTransaksiObat detailTransaksiObat = findById(id);
		super.entityManager.remove(detailTransaksiObat);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailTransaksiObat findById(UUID id) {	
		
		List<DetailTransaksiObat> list = super.entityManager
                .createQuery("from DetailTransaksiObat where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailTransaksiObat();
		}
		else {
			return (DetailTransaksiObat)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailTransaksiObat> findAll() {	
		
		List<DetailTransaksiObat> list = super.entityManager
                .createQuery("from DetailTransaksiObat ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailTransaksiObat> findByFilter() {	
		
		List<DetailTransaksiObat> list = super.entityManager
                .createQuery("from DetailTransaksiObat ")
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
	public DetailTransaksiObat findByBk(String kode) {	
		
		List<DetailTransaksiObat> list = super.entityManager
                .createQuery("from DetailTransaksiObat where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailTransaksiObat();
		}
		else {
			return (DetailTransaksiObat)list.get(0);
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
