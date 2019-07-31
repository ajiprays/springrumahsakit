package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.SubDetailTransaksiInap;

@Repository
public class SubDetailTransaksiInapDao extends ParentDao{

	@Transactional
	public void save(SubDetailTransaksiInap subDetailTransaksiInap) {
		super.entityManager.merge(subDetailTransaksiInap);
	}
	
	@Transactional
	public void delete(UUID id) {
		SubDetailTransaksiInap subDetailTransaksiInap = findById(id);
		super.entityManager.remove(subDetailTransaksiInap);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public SubDetailTransaksiInap findById(UUID id) {	
		
		List<SubDetailTransaksiInap> list = super.entityManager
                .createQuery("from SubDetailTransaksiInap where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new SubDetailTransaksiInap();
		}
		else {
			return (SubDetailTransaksiInap)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<SubDetailTransaksiInap> findAll() {	
		
		List<SubDetailTransaksiInap> list = super.entityManager
                .createQuery("from SubDetailTransaksiInap ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SubDetailTransaksiInap> findByFilter() {	
		
		List<SubDetailTransaksiInap> list = super.entityManager
                .createQuery("from SubDetailTransaksiInap ")
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
	public SubDetailTransaksiInap findByBk(String kode) {	
		
		List<SubDetailTransaksiInap> list = super.entityManager
                .createQuery("from SubDetailTransaksiInap where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new SubDetailTransaksiInap();
		}
		else {
			return (SubDetailTransaksiInap)list.get(0);
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
