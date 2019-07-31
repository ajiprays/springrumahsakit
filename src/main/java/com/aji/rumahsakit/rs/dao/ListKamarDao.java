package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.ListKamar;

@Repository
public class ListKamarDao extends ParentDao{

	@Transactional
	public void save(ListKamar listKamar) {
		super.entityManager.merge(listKamar);
	}
	
	@Transactional
	public void delete(UUID id) {
		ListKamar listKamar = findById(id);
		super.entityManager.remove(listKamar);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ListKamar findById(UUID id) {	
		
		List<ListKamar> list = super.entityManager
                .createQuery("from ListKamar where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new ListKamar();
		}
		else {
			return (ListKamar)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<ListKamar> findAll() {	
		
		List<ListKamar> list = super.entityManager
                .createQuery("from ListKamar ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ListKamar> findByFilter(String nama,String lantai,String jenis) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode_kamar , p.id_jenis_kamar , p.nama,p.lantai,p.state ");
		sb.append("FROM daftar_kamar p ");
		sb.append(" JOIN jenis_kamar j ON p.id_jenis_kamar=j.id ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null")) {
			sb.append(" AND p.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		if(!lantai.equals("null")) {
			sb.append(" AND p.lantai = "+lantai+" ");
			System.out.println("not null nama");
		}
		if(!jenis.equals("null")) {
			sb.append(" AND j.jenis_kamar LIKE '%"+jenis+"%' ");
			System.out.println("not null nama");
		}
		
		List<ListKamar> list=super.entityManager.createNativeQuery(sb.toString(),ListKamar.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<ListKamar>();
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
	public ListKamar findByBk(String kode) {	
		
		List<ListKamar> list = super.entityManager
                .createQuery("from ListKamar where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new ListKamar();
		}
		else {
			return (ListKamar)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getIdJenisKamar()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
	
}
