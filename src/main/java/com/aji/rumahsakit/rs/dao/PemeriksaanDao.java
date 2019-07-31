package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.Pemeriksaan;

@Repository
public class PemeriksaanDao extends ParentDao{

	@Transactional
	public void save(Pemeriksaan pemeriksaan) {
		super.entityManager.merge(pemeriksaan);
	}
	
	@Transactional
	public void delete(UUID id) {
		Pemeriksaan pemeriksaan = findById(id);
		super.entityManager.remove(pemeriksaan);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Pemeriksaan findById(UUID id) {	
		
		List<Pemeriksaan> list = super.entityManager
                .createQuery("from Pemeriksaan where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Pemeriksaan();
		}
		else {
			return (Pemeriksaan)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pemeriksaan> findAll() {	
		
		List<Pemeriksaan> list = super.entityManager
                .createQuery("from Pemeriksaan ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pemeriksaan> findByFilter(String nama,String jenisPerawatan) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_registrasi , p.jenis_perawatan ");
		sb.append("FROM pemeriksaan p ");
		sb.append(" JOIN registrasi r ON p.id_registrasi=r.id ");
		sb.append(" JOIN pasien ps ON r.id_pasien=ps.id ");
		sb.append(" WHERE 1=1 ");		
			
		if(!nama.equals("null")) {
			sb.append(" AND ps.nama LIKE '%"+nama+"%' ");
			System.out.println("not null nama");
		}
		if(!jenisPerawatan.equals("null")) {
			sb.append(" AND p.jenis_perawatan LIKE '%"+jenisPerawatan+"%' ");
			System.out.println("not null alamat");
		}
			
		List<Pemeriksaan> list=super.entityManager.createNativeQuery(sb.toString(),Pemeriksaan.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<Pemeriksaan>();
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
	public Pemeriksaan findByBk(String kode) {	
		
		List<Pemeriksaan> list = super.entityManager
                .createQuery("from Pemeriksaan where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new Pemeriksaan();
		}
		else {
			return (Pemeriksaan)list.get(0);
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
