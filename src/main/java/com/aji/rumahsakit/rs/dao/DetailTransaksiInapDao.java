package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.DetailTransaksiInap;

@Repository
public class DetailTransaksiInapDao extends ParentDao{

	@Transactional
	public void save(DetailTransaksiInap detailTransaksiInap) {
		super.entityManager.merge(detailTransaksiInap);
	}
	
	@Transactional
	public void delete(UUID id) {
		DetailTransaksiInap detailTransaksiInap = findById(id);
		super.entityManager.remove(detailTransaksiInap);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailTransaksiInap findById(UUID id) {	
		
		List<DetailTransaksiInap> list = super.entityManager
                .createQuery("from DetailTransaksiInap where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new DetailTransaksiInap();
		}
		else {
			return (DetailTransaksiInap)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailTransaksiInap> findAll() {	
		
		List<DetailTransaksiInap> list = super.entityManager
                .createQuery("from DetailTransaksiInap ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailTransaksiInap> findByFilter() {	
		
		List<DetailTransaksiInap> list = super.entityManager
                .createQuery("from DetailTransaksiInap ")
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
	public DetailTransaksiInap findByBk(String kode) {	
		
		List<DetailTransaksiInap> list = super.entityManager
                .createQuery("from DetailTransaksiInap where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new DetailTransaksiInap();
		}
		else {
			return (DetailTransaksiInap)list.get(0);
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
