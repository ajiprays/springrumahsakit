package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.Registrasi;

@Repository
public class RegistrasiDao extends ParentDao{

	@Transactional
	public void save(Registrasi registrasi) {
		super.entityManager.merge(registrasi);
	}
	
	@Transactional
	public void delete(UUID id) {
		Registrasi registrasi = findById(id);
		super.entityManager.remove(registrasi);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Registrasi findById(UUID id) {	
		
		List<Registrasi> list = super.entityManager
                .createQuery("from Registrasi where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Registrasi();
		}
		else {
			return (Registrasi)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Registrasi> findAll() {	
		
		List<Registrasi> list = super.entityManager
                .createQuery("from Registrasi ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Registrasi> findByFilter(String nama,String tanggal) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_pasien , p.id_praktek , p.tanggal  ");
		sb.append("FROM registrasi p ");
		sb.append(" JOIN pasien ps On p.id_pasien=ps.id ");
		sb.append(" WHERE 1=1 ");		

		if(!nama.equals("null")) {
			sb.append(" AND ps.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		
		if(!tanggal.equals("null")) {
			sb.append(" AND p.tanggal = '"+tanggal+"' ");
			System.out.println("not null tgl");
		}
			
		List<Registrasi> list=super.entityManager.createNativeQuery(sb.toString(),Registrasi.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Registrasi>();
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
	public Registrasi findByBk(String kode) {	
		
		List<Registrasi> list = super.entityManager
                .createQuery("from Registrasi where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Registrasi();
		}
		else {
			return (Registrasi)list.get(0);
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
