package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.master.Pasien;

@Repository
public class PasienDao extends ParentDao{

	@Transactional
	public void save(Pasien pasien) {
		super.entityManager.merge(pasien);
	}
	
	@Transactional
	public void delete(UUID id) {
		Pasien pasien = findById(id);
		super.entityManager.remove(pasien);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Pasien findById(UUID id) {	
		
		List<Pasien> list = super.entityManager
                .createQuery("from Pasien where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Pasien();
		}
		else {
			return (Pasien)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pasien> findAll() {	
		
		List<Pasien> list = super.entityManager
                .createQuery("from Pasien ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pasien> findByFilters(String  nama,String alamat,String tglLahir){
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.nama , p.jenis_kelamin , p.alamat , p.telp , p.tgl_lahir ");
		sb.append("FROM pasien p ");
		sb.append(" WHERE 1=1 ");		
			System.out.println(nama);
		if(!nama.equals("null")) {
			sb.append(" AND p.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		if(!alamat.equals("null")) {
			sb.append(" AND p.alamat LIKE '%"+alamat+"%' ");
			System.out.println("not null alamat");
		}
		if(!tglLahir.equals("null")) {
			sb.append(" AND p.tgl_lahir = '"+tglLahir+"' ");
			System.out.println("not null tgl");
		}
			
		List<Pasien> list=super.entityManager.createNativeQuery(sb.toString(),Pasien.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Pasien>();
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
	public Pasien findByBk(String kode) {	
		
		List<Pasien> list = super.entityManager
                .createQuery("from Pasien where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Pasien();
		}
		else {
			return (Pasien)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKodePasien()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
}
