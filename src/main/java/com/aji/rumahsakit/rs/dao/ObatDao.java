package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.Obat;

@Repository
public class ObatDao extends ParentDao{

	@Transactional
	public void save(Obat obat) {
		super.entityManager.merge(obat);
	}
	
	@Transactional
	public void delete(UUID id) {
		Obat obat = findById(id);
		super.entityManager.remove(obat);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Obat findById(UUID id) {	
		
		List<Obat> list = super.entityManager
                .createQuery("from Obat where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Obat();
		}
		else {
			return (Obat)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Obat> findAll() {	
		
		List<Obat> list = super.entityManager
                .createQuery("from Obat ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Obat> findByFilter(String nama) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_jenis_obat , p.nama,p.harga,p.state ");
		sb.append("FROM obat p ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null") ) {
			sb.append(" AND p.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		
		List<Obat> list=super.entityManager.createNativeQuery(sb.toString(),Obat.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Obat>();
		}else {
			return list;
		}

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
	public Obat findByBk(String kode) {	
		
		List<Obat> list = super.entityManager
                .createQuery("from Obat where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Obat();
		}
		else {
			return (Obat)list.get(0);
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
