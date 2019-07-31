package com.aji.rumahsakit.rs.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.JenisTransaksi;

@Repository
public class JenisTransaksiDao extends ParentDao{

	@Transactional
	public void save(JenisTransaksi jenisTransaksi) {
		super.entityManager.merge(jenisTransaksi);
	}
	
	@Transactional
	public void delete(UUID id) {
		JenisTransaksi jenisTransaksi = findById(id);
		super.entityManager.remove(jenisTransaksi);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public JenisTransaksi findById(UUID id) {	
		
		List<JenisTransaksi> list = super.entityManager
                .createQuery("from JenisTransaksi where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new JenisTransaksi();
		}
		else {
			return (JenisTransaksi)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<JenisTransaksi> findAll() {	
		
		List<JenisTransaksi> list = super.entityManager
                .createQuery("from JenisTransaksi ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JenisTransaksi> findByFilter() {	
		
		List<JenisTransaksi> list = super.entityManager
                .createQuery("from JenisTransaksi ")
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
	public JenisTransaksi findByBk(String kode) {	
		
		List<JenisTransaksi> list = super.entityManager
                .createQuery("from JenisTransaksi where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new JenisTransaksi();
		}
		else {
			return (JenisTransaksi)list.get(0);
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
