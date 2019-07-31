package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.Dokter;

@Repository
public class DokterDao extends ParentDao{

	@Transactional
	public void save(Dokter dokter) {
		super.entityManager.merge(dokter);
	}
	
	@Transactional
	public void delete(UUID id) {
		Dokter dokter = findById(id);
		super.entityManager.remove(dokter);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Dokter findById(UUID id) {	
		
		List<Dokter> list = super.entityManager
                .createQuery("from Dokter where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Dokter();
		}
		else {
			return (Dokter)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Dokter> findAll() {	
		
		List<Dokter> list = super.entityManager
                .createQuery("from Dokter ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Dokter> findByFilter() {	
		
		List<Dokter> list = super.entityManager
                .createQuery("from Dokter ")
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
	public Dokter findByBk(String kode) {	
		
		List<Dokter> list = super.entityManager
                .createQuery("from Dokter where sip=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Dokter();
		}
		else {
			return (Dokter)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getSip()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Dokter> findByFilters(String  nama,String alamat,String state){
		StringBuilder sb=new StringBuilder("SELECT p.id, p.sip , p.nama , p.jenis_kelamin , p.alamat , p.telp , p.state ");
		sb.append("FROM dokter p ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null")) {
			sb.append(" AND p.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		if(!alamat.equals("null")) {
			sb.append(" AND p.alamat LIKE '%"+alamat+"%' ");
			System.out.println("not null alamat");
		}
		if(!state.equals("null")) {
			sb.append(" AND p.state LIKE '%"+state+"%' ");
			System.out.println("not null tgl");
		}
			
		List<Dokter> list=super.entityManager.createNativeQuery(sb.toString(),Dokter.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Dokter>();
		}else {
			return list;
		}
		
	}
}
